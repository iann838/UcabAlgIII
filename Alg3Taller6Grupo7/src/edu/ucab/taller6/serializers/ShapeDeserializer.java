package edu.ucab.taller6.serializers;

import edu.ucab.taller6.models.Circle;
import edu.ucab.taller6.models.Shape;
import edu.ucab.taller6.models.Square;
import edu.ucab.taller6.models.Triangle;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ShapeDeserializer implements JsonDeserializer<Shape> {

    @SuppressWarnings("serial")
    public static class ShapeNotFound extends JsonParseException {
        public ShapeNotFound(String errorMessage) {
            super(errorMessage);
        }
    }

    @Override
    public Shape deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement jsonType = jsonObject.get("type");
        Shape.Type type = Shape.Type.nameOf(jsonType.getAsString());

        if(type == Shape.Type.CIRCLE) {
            return context.deserialize(json, Circle.class);
        } else if(type == Shape.Type.SQUARE) {
            return context.deserialize(json, Square.class);
        } else if (type == Shape.Type.TRIANGLE) {
            return context.deserialize(json, Triangle.class);
        } else {
            throw new ShapeNotFound(jsonType.getAsString());
        }
    }

}
