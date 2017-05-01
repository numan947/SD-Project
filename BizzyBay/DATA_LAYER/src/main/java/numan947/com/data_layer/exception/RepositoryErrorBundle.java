package numan947.com.data_layer.exception;

import com.example.exception.ErrorBundle;

/**
 * Created by numan947 on 5/1/17.
 */

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
