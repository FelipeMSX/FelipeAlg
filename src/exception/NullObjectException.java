package exception;

/**
 * Created by Felipe on 21/05/2016.
 */
public class NullObjectException extends RuntimeException {

    //Default Message
    public NullObjectException()
    {
        super("The element can not be null!");
    }

    public NullObjectException(String message)
    {
        super(message);
    }
}
