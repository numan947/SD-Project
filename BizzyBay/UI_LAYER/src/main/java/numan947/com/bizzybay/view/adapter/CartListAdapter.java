package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.CartProduct;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.CartListModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListAdapter extends RecyclerView.Adapter {

    public interface Callback{
        void onProductItemClicked(int productId);
        void onShopNameClicked(int shopId);

        void onShopDeleteButtonClicked(CartListModel cartListModel);

        void onProductDeleteButtonClicked(CartListModel cartListModel, CartProduct cartProduct);
    }

    private ArrayList<CartListModel>cartListModels;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback providedCallback;

    //view types
    private final int VIEW_TYPE_LOADING = 0;
    private final int VIEW_TYPE_NORMAL = 1;

    public CartListAdapter(Context context, ArrayList<CartListModel> cartListModels, Callback providedCallback) {
        this.cartListModels = cartListModels;
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

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private class CartListViewHolder extends RecyclerView.ViewHolder{
        private CartListModel cartListModel;

        //shop resources
        private TextView shopName;
        private Button shopDeleteButton;
        private CardView productContainer;
        private TextView totalPrice;
        private Button checkOutButton;

        public CartListViewHolder(View itemView) {
            super(itemView);

            this.bindShop(itemView);

        }

        private void bindShop(View itemView) {
            this.checkOutButton = (Button)itemView.findViewById(R.id.shopping_bag_checkout_button);
        }
    }


}
