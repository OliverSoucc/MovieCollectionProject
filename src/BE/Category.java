package BE;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Movie> allMoviesInCategory;


    public Category(int id, String name, List<Movie> allMoviesInCategory) {
        this.id = id;
        this.name = name;
        this.allMoviesInCategory = allMoviesInCategory;
    }
    public Category(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getAllMoviesInCategory() {
        return allMoviesInCategory;
    }

    public void setAllMoviesInCategory(List<Movie> allMoviesInCategory) {
        this.allMoviesInCategory = allMoviesInCategory;
    }
}
