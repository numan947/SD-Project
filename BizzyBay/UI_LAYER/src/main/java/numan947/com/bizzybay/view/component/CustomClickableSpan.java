package numan947.com.bizzybay.view.component;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * Customized Clickable Span for disabling the default underlining.
 **/

public abstract class CustomClickableSpan extends ClickableSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
