package BE;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private float rating;
    private String fileLink;
    private int lastView;
    private float imdb;


   public Movie(int id, String name, float rating, String fileLink, int lastView, float imdb){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.imdb = imdb;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }
    public Movie(String name, float rating, String fileLink, int lastView, float imdb){
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

    public int getLastView() {
        return lastView;
    }

    public float getImdb() {return imdb;}

    public void setImdb(float imdb) {this.imdb = imdb;}
}
