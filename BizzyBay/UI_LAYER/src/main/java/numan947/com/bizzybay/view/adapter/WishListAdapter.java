package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
import numan947.com.bizzybay.view.ViewHolder.LoadingViewHolder;

/**
 * @author numan947
 * @since 5/25/17.<br>
 **/

public class WishListAdapter extends RecyclerView.Adapter {

    public interface Callback{
        void onShopClicked(int shopId);
        void onProductClicked(int productId,int shopId);
        void onLikeButtonClicked(WishListModel wishListModel,int position);
    }

    private ArrayList<WishListModel> wishListModels;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback providedCallback;

    private final int VIEW_TYPE_NORMAL = 1;
    private final int VIEW_TYPE_LOADING = 2;


    public WishListAdapter(ArrayList<WishListModel> wishListModels, Context context, Callback providedCallback) {
        this.wishListModels = wishListModels;
        this.context = context;
        this.providedCallback = providedCallback;

        if(context!=null)
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh  = null;

        switch (viewType){
            case VIEW_TYPE_NORMAL:
                vh = createNormalViewHolder(parent);
                break;
            case VIEW_TYPE_LOADING:
                vh = createLoadingViewHolder(parent);
                break;
        }

        return vh;
    }

    private RecyclerView.ViewHolder createLoadingViewHolder(ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.generic_progress_view,parent,false);
        return new LoadingViewHolder(v);
    }

    private RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.wish_list_product,parent,false);
        return new WishListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position<wishListModels.size())
            ((WishListViewHolder)holder).renderModel(wishListModels.get(position),position);

    }

    @Override
    public int getItemCount() {
        return this.wishListModels.size()+1;//+1 for loading/end_of_list_view
    }

    @Override
    public int getItemViewType(int position) {

        return position==wishListModels.size() ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    }

    public void clearAll()
    {
        this.wishListModels.clear();
    }

    public void addAll(ArrayList<WishListModel>more)
    {
        this.wishListModels.addAll(more);
    }

    public WishListModel removeAt(int position)
    {
        return wishListModels.remove(position);
    }

    public int getModelSize()
    {
        return wishListModels.size();
    }



    private class WishListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private WishListModel wishListModel;

        private TextView productName;
        private TextView shopName;
        private TextView productPrice;
        private ImageView productImageView;
        private Button likeButton;
        private LinearLayout wishListLL;

        private TypedValue outValue;
        private int position;


        WishListViewHolder(View itemView) {
            super(itemView);
            this.getSelectableItemBackground();
            this.bindProduct(itemView);
            this.addListeners();
        }

        private void getSelectableItemBackground() {
            //getting the selectable item background from resources
            outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
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

            //giving the view a 'selectable' style
            wishListLL.setBackgroundResource(outValue.resourceId);
        }

        void renderModel(WishListModel wishListModel,int position)
        {
            this.position = position;
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
                    .crossFade()
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
                    providedCallback.onLikeButtonClicked(wishListModel,position);
                    break;
            }
        }
    }
}
