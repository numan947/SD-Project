package numan947.com.data_layer.entity.mapper;

import com.example.DetailsProduct;
import com.example.ListProduct;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductEntityDataMapper {
    public Collection<ListProduct> transform(Collection<ListProductEntity> productEntities) {
        //todo transform here if necessary

        Collection<ListProduct>collection =new ArrayList<>();

        for(ListProductEntity a:productEntities){

            try {
                ListProduct b = new ListProduct(a.getShopID()+"",a.getProductID()+""
                        ,a.getProductTitle(),a.getProductPrice()+"",a.getShopDetails(),a.isLiked(),new URL(a.getProductImage()));


                collection.add(b);


            } catch (MalformedURLException e) {
                e.printStackTrace();

            }
        }
        return collection;
    }

    public DetailsProduct transform(DetailsProductEntity detailProductEntity) {

        //todo transform here

        DetailsProduct detailsProduct = new DetailsProduct(detailProductEntity.getProductId(),detailProductEntity.getShopId(),
                detailProductEntity.getProductTitle(),detailProductEntity.getShopName(),detailProductEntity.getProductPrice()+"",
                detailProductEntity.getShopLocation(),detailProductEntity.isCarted(),detailProductEntity.isLiked(),detailProductEntity.getProductDetails(),detailProductEntity.getProductCategory(),
                detailProductEntity.getProductImages());

        return detailsProduct;
    }
}
