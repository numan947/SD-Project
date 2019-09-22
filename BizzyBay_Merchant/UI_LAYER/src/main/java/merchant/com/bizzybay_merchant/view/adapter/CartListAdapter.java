package merchant.com.bizzybay_merchant.view.adapter;

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

import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.model.CartListModel;
import merchant.com.bizzybay_merchant.model.CartProductModel;
import merchant.com.bizzybay_merchant.view.ViewHolder.LoadingViewHolder;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListAdapter extends RecyclerView.Adapter {

    public interface Callback{
        void onProductItemClicked(int productId,int shopId);
        void onShopNameClicked(int shopId);

        void onShopDeleteButtonClicked(int position);

        void onCheckOutButtonClicked(CartListModel cartListModel);

        void onProductDeleteButtonClicked(CartListModel cartListModel, CartProductModel cartProduct,int position);
    }




    private ArrayList<CartListModel>cartListModels;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback providedCallback;

    private LinearLayout.LayoutParams layoutParams;

    //view types
    private final int VIEW_TYPE_LOADING = 0;
    private final int VIEW_TYPE_NORMAL = 1;

    public CartListAdapter(Context context, ArrayList<CartListModel> cartListModels, Callback providedCallback) {
        this.cartListModels = cartListModels;
        this.context = context;
        this.providedCallback = providedCallback;

        this.layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.layoutParams.setMargins(10,10,10,10);


        if(context!=null)
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh=null;

        switch (viewType){
            case VIEW_TYPE_LOADING:
                vh = createLoadingViewHolder(parent);
                break;

            case VIEW_TYPE_NORMAL:
                vh = createNormalViewHolder(parent);
                break;
        }

        return vh;
    }

    private RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent) {
        View view= layoutInflater.inflate(R.layout.shopping_bag_shop,parent,false);
        return new CartListViewHolder(view);
    }

    private RecyclerView.ViewHolder createLoadingViewHolder(ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.generic_progress_view,parent,false);
        return new LoadingViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CartListViewHolder)
            ((CartListViewHolder)holder).renderModel(this.cartListModels.get(position),position);

    }

    @Override
    public int getItemCount() {
        return cartListModels.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position < cartListModels.size() ? VIEW_TYPE_NORMAL : VIEW_TYPE_LOADING;
    }


    public void clearAll()
    {
        this.cartListModels.clear();
    }

    public void addAll(ArrayList<CartListModel>newItems)
    {
        this.cartListModels.addAll(newItems);
    }

    public CartListModel removeAt(int position)
    {
        CartListModel model= null;
        if(position<getModelSize()){
            model = this.cartListModels.remove(position);
        }
        return model;
    }

    public int getModelSize()
    {
        return cartListModels.size();
    }





    private class CartListViewHolder extends RecyclerView.ViewHolder{
        private CartListModel cartListModel;
        private int position;

        //shop resources
        private TextView shopName;
        private Button shopDeleteButton;
        private LinearLayout productContainer;
        private TextView totalPrice;
        private Button checkOutButton;
        private TypedValue outValue;

        CartListViewHolder(View itemView) {
            super(itemView);

            this.bindShop(itemView);
            outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);


        }

        private void bindShop(View itemView) {
            this.checkOutButton = (Button)itemView.findViewById(R.id.shopping_bag_checkout_button);
            this.shopName = (TextView) itemView.findViewById(R.id.shopping_bag_shopName);
            this.shopDeleteButton = (Button)itemView.findViewById(R.id.shopping_bag_shop_delete_button);
            this.productContainer = (LinearLayout) itemView.findViewById(R.id.shopping_bag_product_container);
            this.totalPrice = (TextView)itemView.findViewById(R.id.shopping_bag_total_price);
        }


        void renderModel(CartListModel cartListModel, int position){
            this.position = position;
            this.cartListModel = cartListModel;
            this.setupShop(cartListModel);
            this.setupProducts(cartListModel.getCartProductModels());
        }


        private void setupShop(final CartListModel cartListModel) {
            this.totalPrice.setText(cartListModel.getTotalPrice());
            this.shopName.setText(cartListModel.getShopName());

            this.checkOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //chain
                    providedCallback.onCheckOutButtonClicked(cartListModel);
                }
            });

            this.shopDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //need to chain the position of the item
                    providedCallback.onShopDeleteButtonClicked(position);
                }
            });

            this.shopName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //need to chain the shopId
                    providedCallback.onShopNameClicked(cartListModel.getShopId());
                }
            });

        }

        private void setupProducts(ArrayList<CartProductModel> cartProductModels) {

            productContainer.removeAllViews();
            for(CartProductModel cartProductModel : cartProductModels){
                View view = layoutInflater.inflate(R.layout.shopping_bag_product,null);


                view.setBackgroundResource(outValue.resourceId);

                new CartProductViewHolder(view,cartProductModel,productContainer,cartListModel,position);
            }
        }
    }


    private class CartProductViewHolder implements View.OnClickListener {
        private LinearLayout container;
        private View view;
        private CartProductModel cartProductModel;
        private CartListModel cartListModel;
        private int position;

        private TextView productName;
        private TextView productQuantity;
        private TextView productPrice;
        private TextView productAvailability;
        private ImageView productImage;
        private Button productDeleteButton;
        private LinearLayout productLL;

        private int cnt=0; //debug



        CartProductViewHolder(View view, CartProductModel cartProductModel, LinearLayout container, CartListModel cartListModel, int position) {
            this.view = view;
            this.cartProductModel = cartProductModel;
            this.container = container;
            this.cartListModel = cartListModel;
            this.position = position;


            this.bindProduct(view);
            this.renderModel(cartProductModel);
        }

        private void bindProduct(View view) {
            this.productName = (TextView)view.findViewById(R.id.shopping_bag_product_name);
            this.productQuantity = (TextView)view.findViewById(R.id.shopping_bag_product_quantity);
            this.productAvailability = (TextView)view.findViewById(R.id.shopping_bag_product_available);
            this.productPrice = (TextView)view.findViewById(R.id.shopping_bag_product_price);
            this.productImage = (ImageView) view.findViewById(R.id.shopping_bag_product_image);
            this.productDeleteButton = (Button)view.findViewById(R.id.shopping_bag_product_delete_button);
            this.productLL=(LinearLayout)view.findViewById(R.id.shopping_bag_product_linear_layout);
        }

        private void renderModel(final CartProductModel cartProductModel) {
            this.productName.setText(cartProductModel.getProductName());
            this.productPrice.setText(cartProductModel.getProductPrice());
            this.productAvailability.setText(cartProductModel.getProductAvailability());
            this.productQuantity.setText(cartProductModel.getProductQuantity());

            this.setupProductImage(cartProductModel.getProductImage());



            this.productDeleteButton.setOnClickListener(this);
            this.productLL.setOnClickListener(this);


            this.container.addView(view,layoutParams);
        }

        private void setupProductImage(String productImage) {
            Glide.with(context).load(productImage)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade().fitCenter()
                    .into(this.productImage);
        }


        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.shopping_bag_product_linear_layout:
                    //chain the shopId,productId
                    providedCallback.onProductItemClicked(cartProductModel.getProductId(),cartListModel.getShopId());
                    break;
                case R.id.shopping_bag_product_delete_button:
/*
                    //remove from the view
//                    container.post(new Runnable() {
//                        @Override
//                        public void run() {
//                           // container.removeView(view);
//                        }
//                    });

                    //check if the parent have any more children, if not, disable the container visibility,
                    //so that no accidental click happens to checkout button
//
//                    if(container.getChildCount()==0)
//                        callback.disableWholeContainer();
*/
                    //chain the information, the rest will be handled by adapter itself, as it recreates view
                    providedCallback.onProductDeleteButtonClicked(cartListModel,cartProductModel,position);
                    break;
            }


        }
    }


}
