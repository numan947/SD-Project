package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ListProductModel;
import numan947.com.bizzybay.view.component.SquareImageView;

/**
 *
 * @author numan947
 * @since 5/6/17.<br>
 *
 * Adapter for the {@link RecyclerView} in the {@link numan947.com.bizzybay.view.fragment.ProductListFragment}
 *
 **/

@SuppressWarnings("unused")
public class ProductListAdapter extends RecyclerView.Adapter {

    //should be implemented by the class using this Adapter

    /**
     * Must be implemented by the View containing the {@link RecyclerView} so that it can receive click events.
     */
    public interface Callback {
        /**
         * Called by the adapter when a list item's like button is clicked.
         */
        void OnLikedButtonClicked(final ListProductModel model, final int position);

        /**
         * Called by the adapter when any other part of the view is clicked.
         */
        void OnCardViewClicked(final ListProductModel model, final int position);
    }

    //model parameters
    private ArrayList<ListProductModel> items;

    private Context context;
    private LayoutInflater layoutInflater;

    private Callback callback;

    //view types
    private final int VIEW_TYPE_LOADING = 0;
    private final int VIEW_TYPE_NORMAL = 1;

    public ProductListAdapter(Context context, Callback callback, ArrayList<ListProductModel> items) {
        this.items = items;
        this.context = context;
        this.callback = callback;
        if (context != null)
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Creates each row of the {@link RecyclerView}
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh = null;
        if(layoutInflater!=null)
            switch (viewType){
                case VIEW_TYPE_NORMAL:
                    vh = this.normalListItem(parent);
                    break;
                case VIEW_TYPE_LOADING:
                    vh = this.loadingListItem(parent);
                    break;
            }

        return vh;
    }

    /**
     * Method for creating a loading list item.
     * */
    private RecyclerView.ViewHolder loadingListItem(ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.generic_progress_view, parent, false);
         return new LoadingViewHolder(view);
    }

    /**
     * Method for creating a normal list item.
     * */
    private RecyclerView.ViewHolder  normalListItem(ViewGroup parent){
        View view=layoutInflater.inflate(R.layout.list_product_view,parent,false);
        return new ListProductViewHolder(view);
    }

    /**
     * Binds model data with each of the Row of the {@link RecyclerView}
     * */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (context != null) {
            if (holder instanceof ListProductViewHolder)
                ((ListProductViewHolder) holder).bindModel(items.get(position), position);

        }
    }

    @Override
    public int getItemCount() {
        return items.size()+1; //+1 for loading views
    }

    /**
     * Add items to the {@link RecyclerView}
     * */
    public void addAll(ArrayList<ListProductModel>extraData)
    {
        this.items.addAll(extraData);

    }

    /**
     * Clear the {@link RecyclerView} items.
     * */
    public void clearAll()
    {
        this.items.clear();
    }

    public ArrayList<ListProductModel> getItems(){
        return items;
    }

    @Override
    public int getItemViewType(int position) {
        return position == items.size() ? VIEW_TYPE_LOADING:VIEW_TYPE_NORMAL;
    }



    //view holder class for the view

    /**
     * View Holder class for each of the normal list items.
     * */
    private class ListProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SquareImageView productImage;
        private TextView productTitle;
        private TextView productShop;
        private TextView productPrice;
        private Button likeButton;
        private CardView cardView;
        private ListProductModel model;
        private int position;


        ListProductViewHolder(View itemView) {
            super(itemView);

            //bind the views
            this.bindAll();

            //setup user events
            this.addListenersToViews();
        }

        /**
         * Adds listeners to the views.
         * */
        private void addListenersToViews() {
            cardView.setOnClickListener(this);
            likeButton.setOnClickListener(this);
        }

        /**
         * Binds the UI elements with the java counter parts.
         * */
        private void bindAll() {
            productImage = (SquareImageView) itemView.findViewById(R.id.list_product_image_view);
            productTitle = (TextView) itemView.findViewById(R.id.list_product_product_title);
            productShop = (TextView) itemView.findViewById(R.id.list_product_shop_details);
            productPrice = (TextView) itemView.findViewById(R.id.list_product_product_price);
            likeButton = (Button) itemView.findViewById(R.id.list_product_button_like);
            cardView = (CardView) itemView.findViewById(R.id.list_product_cardView);

        }


        void bindModel(ListProductModel model, int position)
        {
            this.model = model;
            this.position = position;
            //setup image
            this.loadImage(model.getProductImage());


            //set up product
            this.setupTextualResources();

            //setup button
            this.setupButton();

        }

        private void setupButton() {
            Drawable buttonBackgroundImage;
            if(model.isLiked()){
                buttonBackgroundImage = ContextCompat.getDrawable(context,R.drawable.liked);
            }
            else{
                buttonBackgroundImage = ContextCompat.getDrawable(context,R.drawable.not_liked);
            }
            this.setupButtonBackground(likeButton,buttonBackgroundImage);
        }

        private void setupTextualResources() {
            productTitle.setText(model.getProductTitle());
            productPrice.setText(model.getProductPrice());
            productShop.setText(model.getShopDetails());

        }

        private void setupButtonBackground(Button likeButton, Drawable buttonBackgroundImage) {
            if(Build.VERSION.SDK_INT>=16)
                likeButton.setBackground(buttonBackgroundImage);
            else
                //noinspection deprecation
                likeButton.setBackgroundDrawable(buttonBackgroundImage);

        }

        private void loadImage(String imageUrl) {

            Glide.with(context).load(imageUrl.toString())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade().fitCenter()
                    .into(productImage);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.list_product_cardView:

                    callback.OnCardViewClicked(model,position);

                    break;
                case R.id.list_product_button_like:

                    callback.OnLikedButtonClicked(model,position);

                    break;
            }
        }
    }


    //view holder for the last item, which is the last view
    /**
     * View Holder for the loading view.
     * */
    private class LoadingViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout layout;

        LoadingViewHolder(View itemView) {
            super(itemView);

            layout = (RelativeLayout) itemView.findViewById(R.id.rl_progress);
            layout.setVisibility(View.VISIBLE);

        }
    }





}
