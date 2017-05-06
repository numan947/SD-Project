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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;

import java.net.URISyntaxException;
import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ListProductModel;

/**
 * Created by numan947 on 5/6/17.
 */

public class ListProductAdapter extends RecyclerView.Adapter {

    //should be implemented by the class using this Adapter
    public interface Callback{
        void OnLikedButtonClicked(final ListProductModel model,final int position);
        void OnCardViewClicked(final ListProductModel model,final int position);
    }


    private ArrayList<ListProductModel>items;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback callback;
    private final int VIEW_TYPE_LOADING=0;
    private final int VIEW_TYPE_NORMAL=1;

    public ListProductAdapter(Context context,Callback callback, ArrayList<ListProductModel> items) {
        this.items = items;
        this.context = context;
        this.callback = callback;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder vh;

        if(viewType==VIEW_TYPE_NORMAL){
            view=layoutInflater.inflate(R.layout.list_product_view,parent,false);
            vh = new ListProductViewHolder(view);
        }
        else{
            view = layoutInflater.inflate(R.layout.generic_progress_view,parent,false);
            vh = new LoadingViewHolder(view);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ListProductViewHolder)
            ((ListProductViewHolder)holder).bindModel(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size()+1; //+1 for loading views
    }

    public void addAll(ArrayList<ListProductModel>extraData)
    {
        this.items.addAll(extraData);

    }

    public void clearAll()
    {
        this.items.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return position >= items.size() ? VIEW_TYPE_LOADING:VIEW_TYPE_NORMAL;
    }






    //view holder class for the view
    class ListProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productShop;
        private TextView productPrice;
        private Button likeButton;
        private CardView cardView;
        private ListProductModel model;


        public ListProductViewHolder(View itemView) {
            super(itemView);

            //bind the views
            productImage = (ImageView) itemView.findViewById(R.id.list_product_image_view);
            productTitle = (TextView) itemView.findViewById(R.id.list_product_product_title);
            productShop = (TextView) itemView.findViewById(R.id.list_product_shop_details);
            productPrice = (TextView) itemView.findViewById(R.id.list_product_product_price);
            likeButton = (Button) itemView.findViewById(R.id.list_product_button_like);
            cardView = (CardView) itemView.findViewById(R.id.list_product_cardView);


            //setup user events
            cardView.setOnClickListener(this);
            likeButton.setOnClickListener(this);


        }

        public void bindModel(ListProductModel model)
        {
            this.model = model;

            //setup image
            try {
                Glide.with(context).load(model.getProductImage().toURI())
                        .error(R.drawable.error)
                        .placeholder(R.drawable.place_holder)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                        .into(productImage);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            //set up product
            productTitle.setText(model.getProductTitle());
            productPrice.setText(model.getProductPrice());
            productShop.setText(model.getShopDetails());


            //setup button
            Drawable buttonBackgroundImage=null;
            if(model.isLiked()){
               buttonBackgroundImage = ContextCompat.getDrawable(context,R.drawable.liked);
            }
            else{
                buttonBackgroundImage = ContextCompat.getDrawable(context,R.drawable.not_liked);
            }
            if(Build.VERSION.SDK_INT>=16)
                likeButton.setBackground(buttonBackgroundImage);
            else
                likeButton.setBackgroundDrawable(buttonBackgroundImage);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.list_product_cardView:
                    Toast.makeText(context,"SHOULD GO TO DETAILS VIEW",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.list_product_button_like:
                    Toast.makeText(context,"SHOULD ADD TO LIKED LIST",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    //view holder for the last item, which is the last view
    class LoadingViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout layout;

        public LoadingViewHolder(View itemView) {
            super(itemView);

            layout = (RelativeLayout) itemView.findViewById(R.id.rl_progress);
            layout.setVisibility(View.VISIBLE);

        }
    }





}
