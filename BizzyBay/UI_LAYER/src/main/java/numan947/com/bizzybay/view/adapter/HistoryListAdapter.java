package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.HistoryListModel;
import numan947.com.bizzybay.model.HistoryPerProductModel;
import numan947.com.bizzybay.model.HistoryPerShopModel;
import numan947.com.bizzybay.view.ViewHolder.LoadingViewHolder;
import numan947.com.bizzybay.view.component.SquareImageView;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * Adapter for {@link RecyclerView} in the {@link numan947.com.bizzybay.view.fragment.HistoryListFragment}
 **/

public class HistoryListAdapter extends RecyclerView.Adapter {

    public interface Callback{
        void onItemClicked(int orderId,int shopId,int productId);
    }

    //model parameters
    private ArrayList<HistoryListModel> items;

    private Context context;

    private LayoutInflater layoutInflater;

    private HistoryListAdapter.Callback callback;

    //view types
    private final int VIEW_TYPE_LOADING = 0;
    private final int VIEW_TYPE_NORMAL = 1;


    public HistoryListAdapter(Context context, ArrayList<HistoryListModel> items, Callback callback) {
        this.items = items;
        this.context = context;
        this.callback = callback;
        if(context!=null)layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;

        if(layoutInflater!=null){
            switch (viewType){
                case VIEW_TYPE_NORMAL:
                    vh = normalListItem(parent);
                    break;
                case VIEW_TYPE_LOADING:
                    vh = loadingListItem(parent);
                    break;
            }
        }

        return vh;
    }


    /**
     * Method for creating a loading list item.
     */
    private RecyclerView.ViewHolder loadingListItem(ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.generic_progress_view, parent, false);
        return new LoadingViewHolder(view);
    }

    /**
     * Method for creating a normal list item.
     */
    private RecyclerView.ViewHolder normalListItem(ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.history_list_single_date, parent, false);
        return new HistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HistoryListViewHolder)
            ((HistoryListViewHolder)holder).bindModel(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size()+1; //+1 for the loading view
    }


    @Override
    public int getItemViewType(int position) {
        if(position==items.size())return VIEW_TYPE_LOADING;
        return VIEW_TYPE_NORMAL;
    }

    /**
     * Add items to the {@link RecyclerView}
     */
    public void addAll(ArrayList<HistoryListModel> extraData) {
        this.items.addAll(extraData);

    }

    /**
     * Clear the {@link RecyclerView} items.
     */
    public void clearAll() {
        this.items.clear();
    }

    /**
     * returns the actual model size of the adapter
     * */
    public int getModelSize()
    {
        return items.size();
    }


    private class HistoryListViewHolder extends RecyclerView.ViewHolder{


        private HistoryListModel model;

        private TextView historyDate;
        private LinearLayout historyPageShopHolder;
        LinearLayout.LayoutParams historyPageShopHolderParams;

        private TextView shopName;
        private LinearLayout historyPageProductHolder;
        LinearLayout.LayoutParams historyPageProductHolderParams;


        private TextView productName;
        private TextView productQuantity;
        private TextView productStatus;
        private TextView productPrice;
        private SquareImageView productImage;
        private TypedValue outValue;
        HistoryListViewHolder(View itemView) {
            super(itemView);

            this.bindSingleDateView(itemView);

            historyPageShopHolderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            historyPageShopHolderParams.setMargins(10,10,10,10);

            historyPageProductHolderParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            historyPageProductHolderParams.setMargins(10,10,10,10);


            outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);

        }

        private void bindSingleDateView(View itemView) {
            historyDate = (TextView)itemView.findViewById(R.id.history_page_date);
            historyPageShopHolder = (LinearLayout) itemView.findViewById(R.id.history_page_shop_holder);

            //clear the holder
            historyPageShopHolder.removeAllViews();
        }

        public void bindModel(HistoryListModel model)
        {
            this.model = model;
            //setting up date
            historyDate.setText(model.getDate());

            //setting up the shops
            ArrayList<HistoryPerShopModel> historyPerShopModel = model.getHistoryPerShopModel();
            int size = historyPerShopModel.size();

            View v;
            for(int i=0 ; i<size ; i++){

                v = this.bindHistoryPerShop(historyPerShopModel.get(i));

                if (v!=null){
                    historyPageShopHolder.addView(v,historyPageShopHolderParams);
                }
            }
        }

        private View bindHistoryPerShop(HistoryPerShopModel historyPerShopModel) {
            View v= null;
            if(layoutInflater!=null){
                v = layoutInflater.inflate(R.layout.history_list_single_shop,null);
                this.bindSingleShopView(v);
                this.renderSingleShop(historyPerShopModel);
            }
            return v;
        }

        private void bindSingleShopView(View v) {
            shopName = (TextView) v.findViewById(R.id.history_page_shop_name);
            historyPageProductHolder = (LinearLayout) v.findViewById(R.id.history_page_product_holder);

            //clear the holder
            historyPageProductHolder.removeAllViews();

        }


        private void renderSingleShop(HistoryPerShopModel historyPerShopModel) {
            //set the shop name
            shopName.setText(historyPerShopModel.getShopName());

            ArrayList<HistoryPerProductModel> historyPerProductModel = historyPerShopModel.getHistoryPerProductModel();
            int size = historyPerProductModel.size();

            View v;
            for(int i = 0 ; i<size ; i++) {
                v = this.bindHistoryPerProduct(historyPerProductModel.get(i));

                if (v != null) {
                    historyPageProductHolder.addView(v,historyPageProductHolderParams);
                }
            }
        }

        private View bindHistoryPerProduct(final HistoryPerProductModel historyPerProductModel) {
            View v = null;
            if(layoutInflater!=null) {
                v = layoutInflater.inflate(R.layout.history_list_single_product, null);

                this.bindSingleProductView(v);

                this.renderSingleProduct(historyPerProductModel);

            }
            assert v != null;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onItemClicked(historyPerProductModel.getOrderId(), historyPerProductModel.getShopId(), historyPerProductModel.getProductId());
                }
            });

            v.setBackgroundResource(outValue.resourceId);

            return v;
        }

        private void renderSingleProduct(HistoryPerProductModel historyPerProductModel) {
            this.loadProductDetails(historyPerProductModel);
            this.loadProductImageView(historyPerProductModel.getProductImage());

        }


        private void loadProductDetails(HistoryPerProductModel historyPerProductModel) {
            productQuantity.setText(historyPerProductModel.getProductQuantity());
            productName.setText(historyPerProductModel.getProductName());
            productPrice.setText(historyPerProductModel.getProductPrice());
            productStatus.setText(historyPerProductModel.getProductStatus());
        }

        private void loadProductImageView(String productImage) {
            Glide.with(context).load(productImage)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade().fitCenter()
                    .into(this.productImage);
        }


        private void bindSingleProductView(View v) {
            productImage = (SquareImageView) v.findViewById(R.id.history_page_image_view);
            productName = (TextView) v.findViewById(R.id.history_page_product_name);
            productPrice = (TextView) v.findViewById(R.id.history_page_product_price);
            productQuantity = (TextView)v.findViewById(R.id.history_page_product_quantity);
            productStatus = (TextView)v.findViewById(R.id.history_page_product_status);
        }


    }








}
