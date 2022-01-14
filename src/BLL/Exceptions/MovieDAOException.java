package BLL.Exceptions;

public class MovieDAOException extends Throwable{
    String message;
    public MovieDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
