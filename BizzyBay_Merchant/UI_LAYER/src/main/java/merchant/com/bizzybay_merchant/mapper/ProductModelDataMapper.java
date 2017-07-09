package merchant.com.bizzybay_merchant.mapper;

import com.merchant_example.ProductDetails;
import com.merchant_example.ProductList;

import java.util.ArrayList;
import java.util.Collection;

import merchant.com.bizzybay_merchant.model.ProductDetailsModel;
import merchant.com.bizzybay_merchant.model.ProductListModel;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 *
 * This class provides mapping from Domain Layer models to UI Layer models for the Products.
 **/

public class ProductModelDataMapper {

    /**
     * Maps {@link ProductList} to {@link ProductListModel}
     * */
    public ArrayList<ProductListModel> transform(Collection<ProductList> productLists1) {

        ArrayList<ProductList>productLists = new ArrayList<>(productLists1);
        ArrayList<ProductListModel> productListModels = new ArrayList<>();
        for(ProductList product: productLists){
            //todo do conversion here, like html tag adding and what not

            ProductListModel productListModel = new ProductListModel(product.getShopID(),product.getProductID(),product.getProductTitle(),
                    product.getShopDetails(),product.getProductPrice(),product.isLiked(),product.getProductImage());

            productListModels.add(productListModel);
        }

        return productListModels;
    }

    /**
     * Maps {@link ProductDetails} to {@link ProductDetailsModel}
     * */
    public ProductDetailsModel transform(ProductDetails product) {
        //todo do conversion here, like html tag adding and what not

        ProductDetailsModel model = new ProductDetailsModel(product.getProductId(), product.getShopId(), product.getProductTitle(),
                product.getShopName(),
                product.getProductPrice(),product.getShopLocation(),
                product.isCarted(),product.isLiked(),product.getProductDetails(),
                product.getProductCategory(),product.getProductImages());
        return model;
    }
}
