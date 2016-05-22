package exception;

/**
 * Created by 3tecnos999 on 10/08/2015.
 */
public class FullCollectionException extends  RuntimeException {


    //Default Message
    public FullCollectionException()
    {
        super( "The collection is completely full!");
    }

    public FullCollectionException(String message)
    {
        super(message);
    }

}
