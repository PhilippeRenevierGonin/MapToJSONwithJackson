package maptojson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class MapToJSON extends StdSerializer<Map<Point, Date>> {


    protected MapToJSON( ) {
        super(Map.class, true);
    }

    @Override
    public void serialize(Map<Point, Date> pointDateMapMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartArray();
        for (Map.Entry<Point,Date> element: pointDateMapMap.entrySet()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("key", element.getKey());
            jsonGenerator.writeObjectField("value", element.getValue());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
