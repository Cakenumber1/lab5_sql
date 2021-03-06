package sql.demo.repository;

import sql.demo.StockExchangeDB;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements Closeable {
    protected Connection connection;  // JDBC-соединение для работы с таблицей
    protected String tableName;       // Имя таблицы

    BaseTable(String tableName) throws SQLException { // Для реальной таблицы передадим в конструктор её имя
        this.tableName = tableName;
        this.connection = StockExchangeDB.getConnection(); // Установим соединение с СУБД для дальнейшей работы
    }
    @Override
    public void finalize() throws  SQLException{
        try {
            connection.close();
        }
        catch (SQLException e){
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    void executeSqlStatement(String sql, String description) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            if (description != null) {
                System.out.println(description);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getCause());
        }

    }

    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    }

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = StockExchangeDB.getConnection();
        }
    }
}
