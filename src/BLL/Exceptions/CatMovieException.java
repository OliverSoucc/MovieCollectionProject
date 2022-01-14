package BLL.Exceptions;

public class CatMovieException extends Throwable{
    private String message;

    public CatMovieException(String message, Exception e){
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
