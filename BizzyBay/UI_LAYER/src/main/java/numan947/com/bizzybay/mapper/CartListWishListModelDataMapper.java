package numan947.com.bizzybay.mapper;

import com.example.CartList;
import com.example.CartProduct;
import com.example.WishList;

import java.util.ArrayList;

import numan947.com.bizzybay.model.CartListModel;
import numan947.com.bizzybay.model.CartProductModel;
import numan947.com.bizzybay.model.WishListModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListWishListModelDataMapper {
    public ArrayList<CartListModel> transformCartList(ArrayList<CartList> cartList) {
        ArrayList<CartList>carts = new ArrayList<>(cartList);

        ArrayList<CartListModel>cartListModels = new ArrayList<>();

        for(CartList c: carts){

            ArrayList<CartProductModel>cartProductModel =new ArrayList<>();
            for(CartProduct cc:c.getCartProducts()){
                CartProductModel model = new CartProductModel(cc.getProductName(),cc.getProductQuantity(),
                        cc.getProductPrice(),cc.getProductAvailability(),cc.getProductImage(),cc.getProductId());

                cartProductModel.add(model);
            }

            CartListModel cartListModel = new CartListModel(c.getShopId(),c.getShopName(),
                    cartProductModel,c.getTotalPrice(),c.getSomeID());

            cartListModels.add(cartListModel);
        }

        return cartListModels;
    }

    public ArrayList<WishListModel> transformWishList(ArrayList<WishList> wishLists) {
        ArrayList<WishList>wishListsCopy = new ArrayList<>(wishLists);

        ArrayList<WishListModel>models = new ArrayList<>();

        for(WishList w: wishListsCopy){

            WishListModel singleModel = new WishListModel(w.getProductId(),w.getShopId(),w.getProductName()
            ,w.getShopName(),w.getProductPrice(),w.getProductImage());

            models.add(singleModel);
        }

        return models;
    }
}
