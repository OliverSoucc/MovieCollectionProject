package BE;

import java.sql.Date;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private java.sql.Date lastView;
    private float imdb;


   public Movie(int id, String name, float rating, String fileLink, java.sql.Date lastView, float imdb){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imdb = imdb;
        this.fileLink = fileLink;
        this.lastView = lastView;
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

    public java.sql.Date getLastView() {
        return lastView;
    }

    public float getImdb() {return imdb;}

    public void setRating(float rating) {this.rating = rating;}

    public void setId(int id) {
        this.id = id;
    }

    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }

    @Override
    public String toString() {
        return name;
    }


}
