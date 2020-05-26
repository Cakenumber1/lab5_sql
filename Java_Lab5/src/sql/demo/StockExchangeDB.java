package sql.demo;

import org.h2.tools.DeleteDbFiles;
import sql.demo.model.Music;
import sql.demo.repository.MusicTable;

import java.sql.*;


public class StockExchangeDB {
    // Блок объявления констант
    public static final String DB_DIR = "c:/JavaPrj/SQLDemo/db";
    public static final String DB_FILE = "stockExchange";
    public static final String DB_URL = "jdbc:h2:/" + DB_DIR + "/" + DB_FILE;
    public static final String DB_DRIVER = "org.h2.Driver";

    // Таблицы СУБД
    MusicTable musicTable;


    public StockExchangeDB() throws SQLException, ClassNotFoundException {
        this(false);
    }


    public StockExchangeDB(boolean renew) throws SQLException, ClassNotFoundException {
        if (renew) {
            DeleteDbFiles.execute(DB_DIR, DB_FILE, false);
        }
        Class.forName(DB_DRIVER);
        // Инициализируем таблицы
        musicTable = new MusicTable();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }


    public void createTablesAndForeignKeys() throws SQLException {
        musicTable.createTable();
    }

    public static void main(String[] args) {
        try {
            //Создаем новую таблицу
            StockExchangeDB stockExchangeDB = new StockExchangeDB(true);
            stockExchangeDB.createTablesAndForeignKeys();

            //Создаем экземпляры класс Music
            Music firstMusic = new Music("Rock", "Metallica", "2010-10-02", 300, 500, 1);
            Music secondMusic = new Music("Rock", "Iron Maiden", "2000-02-10", 500, 900, 2);

            //Добавляем экземпляры в таблицу
            stockExchangeDB.musicTable.insertIntoTable(firstMusic);
            stockExchangeDB.musicTable.insertIntoTable(secondMusic);
            //Выводим всю информацию из таблицы
            stockExchangeDB.musicTable.printAll();

            //Ищем музыку по ID
            Music searchMusic = stockExchangeDB.musicTable.searchByID(1);
            searchMusic.printAll();

            //Удаляем музыку из таблицы по ID
            stockExchangeDB.musicTable.deleteByID(1);
            stockExchangeDB.musicTable.printAll();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
        }
    }
}
