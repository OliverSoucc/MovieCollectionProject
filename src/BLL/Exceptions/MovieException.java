package BLL.Exceptions;

public class MovieException extends Throwable{
    String message;

    public MovieException(String message, Exception e){
        this.message = message;
        e.printStackTrace();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
