package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.WishListModel;

/**
 * @author numan947
 * @since 5/25/17.<br>
 **/

public class WishListAdapter extends RecyclerView.Adapter {

    public interface Callback{
        void onShopClicked(int shopId);
        void onProductClicked(int productId,int shopId);
        void onLikeButtonClicked(WishListModel wishListModel);
    }

    private ArrayList<WishListModel> wishListModels;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback providedCallback;

    public WishListAdapter(ArrayList<WishListModel> wishListModels, Context context, Callback providedCallback) {
        this.wishListModels = wishListModels;
        this.context = context;
        this.providedCallback = providedCallback;

        if(context!=null)
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    private class WishListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private WishListModel wishListModel;

        private TextView productName;
        private TextView shopName;
        private TextView productPrice;
        private ImageView productImageView;
        private Button likeButton;
        private LinearLayout wishListLL;



        public WishListViewHolder(View itemView) {
            super(itemView);
            this.bindProduct(itemView);
            this.addListeners();
        }

        private void addListeners() {
            wishListLL.setOnClickListener(this);
            likeButton.setOnClickListener(this);
            shopName.setOnClickListener(this);
        }

        private void bindProduct(View itemView) {
            this.productName = (TextView) itemView.findViewById(R.id.wish_list_product_name);
            this.productPrice = (TextView) itemView.findViewById(R.id.wish_list_product_price);
            this.productImageView = (ImageView)itemView.findViewById(R.id.wish_list_product_image);

            this.shopName = (TextView) itemView.findViewById(R.id.wish_list_shop_name);

            this.likeButton = (Button) itemView.findViewById(R.id.wish_list_button);

            this.wishListLL = (LinearLayout)itemView.findViewById(R.id.wish_list_LL);
        }

        public void renderModel(WishListModel wishListModel)
        {
            this.wishListModel = wishListModel;
            this.renderImage(wishListModel.getProductImage());
            this.bindAllTexts(wishListModel);
        }
        
        private void bindAllTexts(WishListModel wishListModel) {
            this.productName.setText(wishListModel.getProductName());
            this.shopName.setText(wishListModel.getShopName());
            this.productPrice.setText(wishListModel.getProductPrice());
        }

        private void renderImage(String productImage) {
            Glide.with(context).load(productImage)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade().fitCenter()
                    .into(productImageView);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.wish_list_LL:
                    providedCallback.onProductClicked(wishListModel.getProductId(),wishListModel.getShopId());
                    break;
                case R.id.wish_list_shop_name:
                    providedCallback.onShopClicked(wishListModel.getShopId());
                    break;
                case R.id.wish_list_button:
                    providedCallback.onLikeButtonClicked(wishListModel);
                    break;
            }
        }
    }
}
