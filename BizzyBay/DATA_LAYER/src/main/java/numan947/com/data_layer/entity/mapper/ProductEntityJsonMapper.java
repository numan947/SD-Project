package numan947.com.data_layer.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 **/

public class ProductEntityJsonMapper {
    private final Gson gson;

    public ProductEntityJsonMapper() {
        this.gson = new Gson();
    }


    public DetailsProductEntity transformDetailsProductEntity(String details) throws JsonSyntaxException {
        try {

            Type detailsEntityType = new TypeToken<DetailsProductEntity>() {}.getType();
            DetailsProductEntity detailsProductEntity = this.gson.fromJson(details, detailsEntityType);


            return detailsProductEntity;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }


    public Collection<ListProductEntity> transformProductEntityCollection(String userListJsonResponse)
            throws JsonSyntaxException {

        Collection<ListProductEntity> listProductEntities;

        try {
            Type listOfUserEntityType = new TypeToken<Collection<ListProductEntity>>() {}.getType();
            listProductEntities = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);

            return listProductEntities;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
