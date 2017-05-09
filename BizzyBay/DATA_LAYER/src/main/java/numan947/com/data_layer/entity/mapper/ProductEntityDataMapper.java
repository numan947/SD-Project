package numan947.com.data_layer.entity.mapper;

import com.example.DetailsProduct;
import com.example.ListProduct;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * This class maps the Product in the data layer to Product in the domain layer
 **/

public class ProductEntityDataMapper {

    /**
     * Converts {@link ListProductEntity } to {@link ListProduct}
     * */
    public Collection<ListProduct> transform(Collection<ListProductEntity> productEntities) {
        //todo transform here if necessary

        Collection<ListProduct>collection =new ArrayList<>();

        for(ListProductEntity a:productEntities){

            ListProduct b = new ListProduct(a.getShopID(),a.getProductID()
                    ,a.getProductTitle(),a.getProductPrice()+"",
                    a.getShopDetails(),a.isLiked(),a.getProductImage());
            collection.add(b);
        }
        return collection;
    }

    /**
     * Converts {@link DetailsProductEntity } to {@link DetailsProduct}
     * */
    public DetailsProduct transform(DetailsProductEntity detailProductEntity) {

        //todo transform here

        return new DetailsProduct(detailProductEntity.getProductId(),detailProductEntity.getShopId(),
                detailProductEntity.getProductTitle(),detailProductEntity.getShopName(),detailProductEntity.getProductPrice()+"",
                detailProductEntity.getShopLocation(),detailProductEntity.isCarted(),detailProductEntity.isLiked(),detailProductEntity.getProductDetails(),detailProductEntity.getProductCategory(),
                detailProductEntity.getProductImages());
    }
}
