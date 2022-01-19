package BE;

import java.util.Date;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private int lastView;
    private float imdb;
    private Date lastview;


   public Movie(int id, String name, float rating, String fileLink, int lastView, float imdb){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imdb = imdb;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }

    public Movie(int id, String name, float rating, String fileLink, Date lastview, float imdb){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imdb = imdb;
        this.fileLink = fileLink;
        this.lastview = lastview;
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

    public float getImdb() {return imdb;}

    public void setRating(float rating) {this.rating = rating;}

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastview() {
        return lastview;
    }

    public void setLastview(Date lastview) {
        this.lastview = lastview;
    }

    @Override
    public String toString() {
        return name;
    }


}
