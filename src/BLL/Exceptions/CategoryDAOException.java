package BLL.Exceptions;

public class CategoryDAOException extends Throwable{
    String message;

    public CategoryDAOException(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }
    @Override
    public String getMessage() {
        return message;
    }
}
