package numan947.com.bizzybay.view.component;

import android.text.SpannableStringBuilder;
import android.view.View;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 *
 * Simple class that has some Static methods to provide string decoration.
 **/

public class StringDecorator {

    /**
     * To use addClickablePart method, this inteface implementation must be provided,it's used to pass
     * the clicked string back.
     * */
    public interface ClickableSpanDecoratorCallback{
        void onDecoratedItemClicked(String decoratedString);
    }





    /**
     * Adds Clickable part in the String
     * The Method expects String's words separated
     * by SINGLE Space //todo extend it to support multiple space
     * */
    public static SpannableStringBuilder addClickablePart(final String str, final ClickableSpanDecoratorCallback callback) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);

        int idx1 =0;
        int idx2 = str.indexOf(" ");

        while (idx2!=-1){

            final String clicked = str.substring(idx1,idx2);
            ssb.setSpan(new CustomClickableSpan() {
                @Override
                public void onClick(View widget) {
                    callback.onDecoratedItemClicked(clicked);
                }
            },idx1,idx2,0);

            idx1 = idx2;
            idx2 = str.indexOf(" ",idx2+1);
            System.out.println(idx1+"    "+idx2);
        }

        final String lastItem  = str.substring(idx1,str.length());

        ssb.setSpan(new CustomClickableSpan() {
            @Override
            public void onClick(View widget) {
                callback.onDecoratedItemClicked(lastItem);
            }
        },idx1,str.length(),0);

        return ssb;
    }
}
