package numan947.com.bizzybay.view.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ProductModelMultiple;


public class BrowseProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    private TextView productTitle;
    private TextView productshop;
    private TextView productPrice;
    private TextView productId;
    private ImageView productImage;
    private Button buttonAddToWishList;
    private Button buttonAddToCart;
    private Button buttonBuyNow;
    private ProductModelMultiple productModel;


    public BrowseProductViewHolder(Context context,View itemView) {
        super(itemView);
        this.context = context;

        //bind the views
        productshop = (TextView) itemView.findViewById(R.id.browse_products_product_shop);
        productTitle = (TextView) itemView.findViewById(R.id.browse_products_product_title);
        productPrice = (TextView) itemView.findViewById(R.id.browse_products_product_price);
        productImage = (ImageView) itemView.findViewById(R.id.browse_products_image_view);
        productId = (TextView) itemView.findViewById(R.id.browse_products_product_id);
        buttonAddToWishList = (Button)itemView.findViewById(R.id.browse_products_button_add_to_wishlist);
        buttonAddToCart = (Button)itemView.findViewById(R.id.browse_products_button_add_to_cart);
        buttonBuyNow = (Button)itemView.findViewById(R.id.browse_products_button_buy_now);


        this.addListeners();

    }

    public void bindModel(ProductModelMultiple productModel)
    {
        //todo complete binding model
        this.productModel = productModel;


        this.productId.setText(productModel.getProductID());
        this.productImage.setImageBitmap(productModel.getProductImage());//todo GLIDE
        this.productPrice.setText(productModel.getProductPrice());
        this.productTitle.setText(productModel.getProductTitle());
        this.productshop.setText(productModel.getProductShop());

    }


    private void addListeners()
    {
        productId.setOnClickListener(this);
        productTitle.setOnClickListener(this);
        productImage.setOnClickListener(this);

        productshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo go to product shop page
            }
        });
        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show add to cart


            }
        });

        buttonAddToWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show add to wishlist
            }
        });

        buttonBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show buy now
            }
        });

    }


    //works for productDescription,productTitle,productImage
    @Override
    public void onClick(View v) {

        Toast.makeText(context,"Showing Product Details",Toast.LENGTH_SHORT).show();

        //todo show product details from here
    }
}
