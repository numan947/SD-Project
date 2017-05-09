package numan947.com.bizzybay.mapper;

import com.example.DetailsProduct;
import com.example.ListProduct;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.bizzybay.model.DetailsProductModel;
import numan947.com.bizzybay.model.ListProductModel;

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
     * Maps {@link ListProduct} to {@link ListProductModel}
     * */
    public ArrayList<ListProductModel> transform(Collection<ListProduct> listProducts) {
        ArrayList<ListProductModel>listProductModels = new ArrayList<>();
        for(ListProduct product:listProducts){
            //todo do conversion here, like html tag adding and what not

            ListProductModel listProductModel = new ListProductModel(product.getShopID(),product.getProductID(),product.getProductTitle(),
                    product.getShopDetails(),product.getProductPrice(),product.isLiked(),product.getProductImage());

            listProductModels.add(listProductModel);
        }

        return listProductModels;
    }

    /**
     * Maps {@link DetailsProduct} to {@link DetailsProductModel}
     * */
    public DetailsProductModel transform(DetailsProduct product) {
        //todo do conversion here, like html tag adding and what not

        DetailsProductModel model = new DetailsProductModel(product.getProductId(), product.getShopId(), product.getProductTitle(),
                product.getShopName(),
                product.getProductPrice(),product.getShopLocation(),
                product.isCarted(),product.isLiked(),product.getProductDetails(),
                product.getProductCategory(),product.getProductImages());
        return model;
    }
}
