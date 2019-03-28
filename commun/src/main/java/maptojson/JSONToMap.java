package maptojson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// genericité compliquée ici : il faut les classes réelles pour instancier la map...
public class JSONToMap extends StdDeserializer<Map<Point, Date>> {

    protected JSONToMap() {
        super(Map.class);
    }

    @Override
    public Map<Point, Date> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Map<Point, Date> result = new HashMap<>();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        for (JsonNode element : node) {
            result.put(
                    jsonParser.getCodec().treeToValue(element.get("key"), Point.class),
                    jsonParser.getCodec().treeToValue(element.get("value"), Date.class)
            );
        }
        return result;
    }
}
