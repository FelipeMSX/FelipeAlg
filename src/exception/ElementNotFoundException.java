package exception;

/**
 * Created by 3tecnos999 on 10/08/2015.
 */
public class ElementNotFoundException extends  RuntimeException {


    //Default Message
    public ElementNotFoundException()
    {
        super( "The collection no contain this element!");
    }

    public ElementNotFoundException(String message)
    {
        super(message);
    }
}
