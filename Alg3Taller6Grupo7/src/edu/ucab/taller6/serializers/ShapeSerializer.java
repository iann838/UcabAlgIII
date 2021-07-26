package edu.ucab.taller6.serializers;

import edu.ucab.taller6.models.Shape;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ShapeSerializer implements JsonSerializer<Shape> {

    @Override
    public JsonElement serialize(Shape o, Type type, JsonSerializationContext context ) {
        return context.serialize((Object) o);
    }

}
