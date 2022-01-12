package be;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private int lastView;

    public Movie(int id, String name, float rating, String fileLink, int lastView) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }
    public Movie(String name, float rating, String fileLink) {
        this.name = name;
        this.rating = rating;
        this.fileLink = fileLink;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public int getLastView() {
        return lastView;
    }
}
