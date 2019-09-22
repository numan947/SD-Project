package merchant.com.merchant_data_layer.entity.mapper;

import com.merchant_example.CartList;
import com.merchant_example.CartProduct;
import com.merchant_example.WishList;

import java.util.ArrayList;

import merchant.com.merchant_data_layer.entity.CartListEntity;
import merchant.com.merchant_data_layer.entity.CartProductEntity;
import merchant.com.merchant_data_layer.entity.WishListEntity;

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
        ArrayList<WishListEntity>ww = new ArrayList<>(wishListEntities);

        ArrayList<WishList>lst = new ArrayList<>();

        for(WishListEntity w: ww){
            WishList newWish = new WishList(w.getProductId(),w.getShopId(),w.getProductName(),w.getShopName(),
                    w.getProductPrice()+"",w.getProductImage());

            lst.add(newWish);
        }

        return lst;
    }
}
