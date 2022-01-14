package BLL.Exceptions;

public class CategoryException extends Throwable{
    private String message;

    public CategoryException(String message, Exception e){
        this.message = message;
        e.printStackTrace();
    }
    @Override
    public String getMessage(){
        return message;
    }
}
