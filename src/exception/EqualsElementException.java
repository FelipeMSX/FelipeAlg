package exception;

/**
 * Created by Felipe on 09/08/2015.
 */
public class EqualsElementException extends  RuntimeException {

    //Default Message
    public EqualsElementException()
    {
        super("The collection does not accept the same elements!");
    }

    public EqualsElementException(String message)
    {
        super(message);
    }
}
