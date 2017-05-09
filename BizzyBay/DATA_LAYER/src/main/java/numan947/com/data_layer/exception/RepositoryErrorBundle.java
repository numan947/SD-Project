package numan947.com.data_layer.exception;

import com.example.exception.ErrorBundle;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Class implementing {@link ErrorBundle} defined in the domain layer.
 * The errors happened in the data layer are passed as an instance of this
 * to the domain layer.
 **/

public class RepositoryErrorBundle implements ErrorBundle {

    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String msg="";
        if(exception!=null)msg+=exception.getMessage();
        else msg+="Exception Object is NULL";
        return msg;
    }
}
