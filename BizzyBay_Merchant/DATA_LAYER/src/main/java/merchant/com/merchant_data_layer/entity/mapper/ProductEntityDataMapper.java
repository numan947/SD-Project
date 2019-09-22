package merchant.com.merchant_data_layer.entity.mapper;

import com.merchant_example.ProductDetails;
import com.merchant_example.ProductList;

import java.util.ArrayList;
import java.util.Collection;

import merchant.com.merchant_data_layer.entity.DetailsProductEntity;
import merchant.com.merchant_data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * This class maps the Product in the data layer to Product in the domain layer
 **/

public class ProductEntityDataMapper {

    /**
     * Converts {@link ListProductEntity } to {@link ProductList}
     * */
    public Collection<ProductList> transform(Collection<ListProductEntity> productEntities) {
        //todo transformCartList here if necessary

        Collection<ProductList>collection =new ArrayList<>();

        for(ListProductEntity a:productEntities){

            ProductList b = new ProductList(a.getShopID(),a.getProductID()
                    ,a.getProductTitle(),a.getProductPrice()+"",
                    a.getShopDetails(),a.isLiked(),a.getProductImage());
            collection.add(b);
        }
        return collection;
    }

    /**
     * Converts {@link DetailsProductEntity } to {@link ProductDetails}
     * */
    public ProductDetails transform(DetailsProductEntity detailProductEntity) {

        //todo transformCartList here

        return new ProductDetails(detailProductEntity.getProductId(),detailProductEntity.getShopId(),
                detailProductEntity.getProductTitle(),detailProductEntity.getShopName(),detailProductEntity.getProductPrice()+"",
                detailProductEntity.getShopLocation(),detailProductEntity.isCarted(),detailProductEntity.isLiked(),detailProductEntity.getProductDetails(),detailProductEntity.getProductCategory(),
                detailProductEntity.getProductImages());
    }
}
