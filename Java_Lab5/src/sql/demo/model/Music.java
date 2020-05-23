package sql.demo.model;

public class Music extends BaseModel {
    private String title;
    private String artist;
    private String date;
    private int listPrice;
    private int price;
    private int version;


    public Music(String title, String artist, String date, int listPrice, int price, int version) {
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.listPrice = listPrice;
        this.price = price;
        this.version = version;
    }

    public Music() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getListPrice() {
        return listPrice;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void printAll() {
        System.out.println(title + '\t' + artist + '\t' + date + '\t' + listPrice + '\t' + price + '\t' + version);
    }
}
