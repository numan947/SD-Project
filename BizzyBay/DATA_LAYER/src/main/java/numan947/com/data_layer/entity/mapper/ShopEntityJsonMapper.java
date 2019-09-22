package numan947.com.data_layer.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

import numan947.com.data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 7/10/17.<br>
 **/

public class ShopEntityJsonMapper {
    private Gson gson;
    public ShopEntityJsonMapper()
    {
        gson = new Gson();
    }

    public Collection<ShopListEntity> transformShopEntityCollection(String responseUserList) {

        Collection<ShopListEntity> listShopEntities;

        try {
            Type listOfUserEntityType = new TypeToken<Collection<ShopListEntity>>() {}.getType();
            System.out.println(responseUserList);
            listShopEntities = this.gson.fromJson(responseUserList, listOfUserEntityType);
            return listShopEntities;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
