package techin.lt.Tour.exception;

public class UsernameAlreadyExistsException extends AlreadyExistsException{
    public UsernameAlreadyExistsException (String message){
        super(message);
    }
}
