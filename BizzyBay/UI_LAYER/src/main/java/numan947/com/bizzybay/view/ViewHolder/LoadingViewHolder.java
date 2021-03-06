package numan947.com.bizzybay.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import numan947.com.bizzybay.R;

/**
 * View Holder for the loading view.
 * */
public class LoadingViewHolder extends RecyclerView.ViewHolder{
    private RelativeLayout layout;

    public LoadingViewHolder(View itemView) {
        super(itemView);

        layout = (RelativeLayout) itemView.findViewById(R.id.rl_progress);
        layout.setVisibility(View.VISIBLE);

    }
}