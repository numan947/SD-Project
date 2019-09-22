package numan947.com.bizzybay.view.component;

import android.support.v4.view.ViewPager;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 *
 * A Circular ViewPager Handler for making the viewpager wrap around the end.
 **/

public class CircularViewPagerHandler implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private int currentPosition;
    private int currentScrollState;

    public CircularViewPagerHandler(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        this.changeScrollState(state);
        currentScrollState = state;
    }

    private void changeScrollState(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                getNextItem();
                break;
        }

    }

    private void getNextItem() {
        if(!isScrollStateSettling())
            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CircularViewPagerHandler.this.handleNextItem();
                }
            },200);
    }

    private void handleNextItem() {
        final int last = viewPager.getAdapter().getCount() - 1;
        if(currentPosition==0)
            viewPager.setCurrentItem(last,false);
        else if(currentPosition==last)
            viewPager.setCurrentItem(0,false);
    }

    private boolean isScrollStateSettling(){
        return currentScrollState == ViewPager.SCROLL_STATE_SETTLING;
    }
}
