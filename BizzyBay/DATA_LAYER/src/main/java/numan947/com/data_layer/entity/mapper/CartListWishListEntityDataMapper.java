package numan947.com.data_layer.entity.mapper;

import com.example.CartList;
import com.example.CartProduct;
import com.example.WishList;

import java.util.ArrayList;

import numan947.com.data_layer.entity.CartListEntity;
import numan947.com.data_layer.entity.CartProductEntity;
import numan947.com.data_layer.entity.WishListEntity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListWishListEntityDataMapper {
    public ArrayList<CartList> transformCartList(ArrayList<CartListEntity> cartListEntities1) {
        ArrayList<CartList> cartLists = new ArrayList<>();
        ArrayList<CartListEntity>cartListEntities = new ArrayList<>(cartListEntities1);

        for(CartListEntity cartListEntity:cartListEntities){
            ArrayList<CartProduct> cartProducts = new ArrayList<>();

            for(CartProductEntity cartProductEntity: cartListEntity.getCartProductEntities()){
                CartProduct cartProduct = new CartProduct(cartProductEntity.getProductName(),
                        cartProductEntity.getProductQuantity()+"",
                        cartProductEntity.getProductPrice()+"",
                        cartProductEntity.getProductAvailability()+"",
                        cartProductEntity.getProductImage(), cartProductEntity.getProductId());
                cartProducts.add(cartProduct);
            }

            CartList cartList = new CartList(cartListEntity.getShopName(),cartProducts,
                    cartListEntity.getTotalPrice()+"",cartListEntity.getSomeID(), cartListEntity.getShopId());

            cartLists.add(cartList);
        }

        return cartLists;
    }

    public ArrayList<WishList> transformWishList(ArrayList<WishListEntity> wishListEntities) {
        return null;
    }
}
