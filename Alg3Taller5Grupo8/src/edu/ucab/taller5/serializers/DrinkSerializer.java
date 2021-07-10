package edu.ucab.taller5.serializers;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.ucab.taller5.models.Drink;

public class DrinkSerializer implements JsonSerializer<Drink> {

    @Override
    public JsonElement serialize(Drink o, Type type, JsonSerializationContext context ) {
        return context.serialize((Object) o);
    }

}
