package edu.ucab.taller5.serializers;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import edu.ucab.taller5.models.Coke;
import edu.ucab.taller5.models.Drink;
import edu.ucab.taller5.models.Lemon;
import edu.ucab.taller5.models.Orange;

public class DrinkDeserializer implements JsonDeserializer<Drink> {
    
    @SuppressWarnings("serial")
    public static class DrinkNotFound extends JsonParseException {
        public DrinkNotFound(String errorMessage) {
            super(errorMessage);
        }
    }

    @Override
    public Drink deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement jsonType = jsonObject.get("type");
        Drink.Type type = Drink.Type.nameOf(jsonType.getAsString());

        if(type == Drink.Type.COKE) {
            return context.deserialize(json, Coke.class);
        } else if(type == Drink.Type.LEMON) {
            return context.deserialize(json, Lemon.class);
        } else if (type == Drink.Type.ORANGE) {
            return context.deserialize(json, Orange.class);
        } else {
            throw new DrinkNotFound(jsonType.getAsString());
        }
    }
    
}
