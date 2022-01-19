package BLL.Exceptions;

public class CatMovieDAOException extends Throwable{
    String message;

    public CatMovieDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
