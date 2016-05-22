package exception;

/**
 * Created by 3tecnos999 on 10/08/2015.
 */
public class EmptyCollectionException extends  RuntimeException {


    //Default Message
    public EmptyCollectionException()
    {
        super( "The collection no contain any element!");
    }

    public EmptyCollectionException(String message)
    {
        super(message);
    }

}
