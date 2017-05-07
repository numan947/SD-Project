package numan947.com.bizzybay.mapper;

import com.example.ListProduct;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.bizzybay.model.ListProductModel;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductModelDataMapper {
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
}
