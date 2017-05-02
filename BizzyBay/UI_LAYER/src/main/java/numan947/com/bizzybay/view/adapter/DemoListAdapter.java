package numan947.com.bizzybay.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ProductModelSingle;

/**
 * Created by numan947 on 5/2/17.
 */

public class DemoListAdapter extends ArrayAdapter<ProductModelSingle> implements View.OnClickListener {
    private ArrayList<ProductModelSingle> productModelSingles;
    private Context context;

    private TextView productTitle;
    private TextView productshop;
    private TextView productPrice;
    private TextView productId;
    private ImageView productImage;
    private Button buttonAddToWishList;
    private Button buttonAddToCart;
    private Button buttonBuyNow;
    private Callback callback;

    private int layout;

    public DemoListAdapter(Context context,ArrayList<ProductModelSingle>productModelSingles,int layout,Callback callback) {
        super(context, layout,productModelSingles);
        this.productModelSingles = productModelSingles;
        this.context=context;
        this.layout = layout;
        this.callback=callback;

    }

    @Override
    public int getCount() {
        return productModelSingles.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            itemView = layoutInflater.inflate(R.layout.product_in_list,parent,false);

        }
        Log.d("TAG","HHHHHHHHHHHHHHHHHH");
        ProductModelSingle p = productModelSingles.get(position);

        if(p!=null){
            //bind the views
            productshop = (TextView) itemView.findViewById(R.id.browse_products_product_shop);
            productTitle = (TextView) itemView.findViewById(R.id.browse_products_product_title);
            productPrice = (TextView) itemView.findViewById(R.id.browse_products_product_price);
            productImage = (ImageView) itemView.findViewById(R.id.browse_products_image_view);
            productId = (TextView) itemView.findViewById(R.id.browse_products_product_id);
            buttonAddToWishList = (Button)itemView.findViewById(R.id.browse_products_button_add_to_wishlist);
            buttonAddToCart = (Button)itemView.findViewById(R.id.browse_products_button_add_to_cart);
            buttonBuyNow = (Button)itemView.findViewById(R.id.browse_products_button_buy_now);

            productshop.setText(p.getProductShop());
            productTitle.setText(p.getProductTitle());
            productPrice.setText(p.getProductPrice());
            productImage.setImageBitmap(p.getProductImage());
            productId.setText(p.getProductID());

            productTitle.setOnClickListener(this);
            productImage.setOnClickListener(this);

            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Product Added to Cart",Toast.LENGTH_SHORT).show();
                }
            });

            buttonAddToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Product Added to WishList",Toast.LENGTH_SHORT).show();
                }
            });

            buttonBuyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Will be Implemented Later",Toast.LENGTH_SHORT).show();
                }
            });

        }
        return itemView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Show Product Details",Toast.LENGTH_SHORT).show();
        callback.showNextActivity();
    }


    public interface  Callback{
        void showNextActivity();
    }
}
