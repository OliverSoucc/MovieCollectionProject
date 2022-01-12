package BE;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private int lastView;
    private int duration;
    private String category1;
    private String category2;
    private String category3;


    public Movie(int id, String name, float rating, String fileLink, int lastView, int duration) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.duration = duration;
    }
    public Movie(String name, float rating, String fileLink, int duration) {
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.duration = duration;
    }


    public Movie(String name, float rating, String category1, String category2, String category3){
        this.name = name;
        this.rating = rating;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public int getLastView() {
        return lastView;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }
}
