package merchant.com.bizzybay_merchant.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.model.ShopListModel;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopListAdapter extends BaseAdapter {
    private static final int LOADING_VIEW = 0;
    private static final int NORMAL_VIEW =1;

    public void clear() {
        this.shopList.clear();
    }

    public interface Callback{
        void onListItemClicked(int shopId);
    }

    private ArrayList<ShopListModel>shopList;

    private Context context;
    private Callback callback;


    public ShopListAdapter(Callback callback,ArrayList<ShopListModel> shopList, Context context) {
        this.shopList = shopList;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public int getModelSize(){
        return shopList.size();
    }
    public void addAll(ArrayList<ShopListModel>extra){
        this.shopList.addAll(extra);
    }

    public ArrayList<ShopListModel> getShopList() {
        return shopList;
    }

    @Override
    public int getCount() {
        return shopList.size()+1;//+1 for loading view
    }

    @Override
    public Object getItem(int position) {
        if(position==shopList.size())return null; //for the loading view
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==shopList.size())return LOADING_VIEW;
        else return NORMAL_VIEW;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ShopListViewHolder shopListViewHolder=null;
        LoadingViewHolder loadingViewHolder =null;

        if(convertView==null){
            int type = getItemViewType(position);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(position==shopList.size()){
                convertView = layoutInflater.inflate(R.layout.generic_progress_view,parent,false);
                loadingViewHolder = new LoadingViewHolder(convertView);
                convertView.setTag(loadingViewHolder);
            }
            else{
                convertView = layoutInflater.inflate(R.layout.shop_list_view,parent,false);
                shopListViewHolder = new ShopListViewHolder(convertView);
                convertView.setTag(shopListViewHolder);
            }
        }

        else{
            if(position==shopList.size()){
                loadingViewHolder = (LoadingViewHolder) convertView.getTag();
            }
            else{
                shopListViewHolder = (ShopListViewHolder) convertView.getTag();
            }
        }


        if(position<shopList.size()){

            if(shopListViewHolder!=null)

                shopListViewHolder.bindModel(shopList.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onListItemClicked(shopList.get(position).getShopId());
                }
            });

        }

        return convertView;
    }


    private class ShopListViewHolder{
        //todo add the binding fields here
        private PorterShapeImageView porterShapeImageView;
        private TextView shopName;
        private TextView shopDistance;
        private TextView shopLocation;
        private TextView shopType;
        private TextView shopDetails;

        public ShopListViewHolder(View view) {
            this.bindAll(view);
        }

        private void bindAll(View view) {
            porterShapeImageView = (PorterShapeImageView)view.findViewById(R.id.shop_list_view_image);
            shopName = (TextView)view.findViewById(R.id.shop_list_view_shop_name);
            shopDistance = (TextView)view.findViewById(R.id.shop_list_view_shop_distance);
            shopLocation = (TextView)view.findViewById(R.id.shop_list_view_shop_location);
            shopType = (TextView)view.findViewById(R.id.shop_list_view_shop_type);
            shopDetails = (TextView)view.findViewById(R.id.shop_list_view_shop_details);
        }


        public void bindModel(ShopListModel shopList){
            this.bindImage(shopList.getShopImage());
            this.bindTextualData(shopList);
        }

        private void bindTextualData(ShopListModel shopList) {
            this.shopDetails.setText(shopList.getShopDetails());
            this.shopType.setText(shopList.getShopType());
            this.shopLocation.setText(shopList.getShopLocation());
            this.shopDistance.setText(shopList.getShopDistance());
            this.shopName.setText(shopList.getShopName());
        }

        private void bindImage(String shopImage) {
            Glide.with(context).load(shopImage)
                    .error(R.drawable.error)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .crossFade().fitCenter()
                    .into(porterShapeImageView);
        }

    }

    private class LoadingViewHolder{
        public LoadingViewHolder(View view) {
            view.findViewById(R.id.rl_progress).setVisibility(View.VISIBLE);
        }
    }




}
