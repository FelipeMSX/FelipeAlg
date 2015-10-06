package exception;

/**
 * Created by 3tecnos999 on 10/08/2015.
 */
public class EmptyListException extends  RuntimeException {

    public EmptyListException(String message)
    {
        super(message);
    }

    //Default Message
    public EmptyListException()
    {
        super( "The list no contain element this element.");
    }
}
