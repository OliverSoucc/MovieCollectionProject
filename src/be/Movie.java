package be;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private int lastView;
    private int duration;

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
