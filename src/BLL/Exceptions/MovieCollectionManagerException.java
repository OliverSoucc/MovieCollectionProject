package BLL.Exceptions;

public class MovieCollectionManagerException extends Throwable{
    private String message;

    public MovieCollectionManagerException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
