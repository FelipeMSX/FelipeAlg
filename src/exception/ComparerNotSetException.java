package exception;

/**
 * Created by 3tecnos999 on 10/08/2015.
 */
public class ComparerNotSetException extends  RuntimeException {


    //Default Message
    public ComparerNotSetException()
    {
        super( "The comparer is not seted in the collection!");
    }

    public ComparerNotSetException(String message)
    {
        super(message);
    }
}
