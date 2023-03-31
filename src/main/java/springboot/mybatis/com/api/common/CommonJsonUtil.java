package springboot.mybatis.com.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CommonJsonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode convertStringToJsonNode(String str) {
        try {
            JsonNode rs = objectMapper.readTree(str);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject convertStringToJSONObject(String str) {
        try {
            JSONObject rs = new JSONObject(str);
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject convertStringToJSONObjectWithoutError(String str) {
        try {
            JSONObject rs = new JSONObject(str);
            return rs;
        } catch(Exception e) {
            return new JSONObject("{\"message\" :\"" + str.replace("\"","").replace("\n","")  + "\"}");
        }
    }

    public static Map<String, Object> convertJsonNodeToMap(JsonNode jsonNode) {
        try {
//             Map<String, String> map = objectMapper.readValue(jsonNode, Map.class);
            return objectMapper.treeToValue(jsonNode, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode convertObjectToJsonNode(Object fromValue) {
        return objectMapper.convertValue(fromValue, JsonNode.class);
    }

    public static Map convertObjectToMap(Object fromValue) {
        return objectMapper.convertValue(fromValue, Map.class);
    }

    public static JsonNode convertVoToJsonNode(Object fromValue) {
        return objectMapper.convertValue(fromValue, JsonNode.class);
    }

    public static ArrayNode convertVoToArrayNode(List fromValue) {
        return objectMapper.convertValue(fromValue, ArrayNode.class);
    }

    public static ObjectNode convertMapToObjectNode(Map map) {
        try {
            return objectMapper.valueToTree(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayNode convertMapsToArrayNode(List maps) {
        try {
            return objectMapper.valueToTree(maps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Schema readSchema(String schemaString) {
        JSONObject schemaJson = new JSONObject(new JSONTokener(schemaString));
        return SchemaLoader.builder()
                .useDefaults(true)
                .schemaJson(schemaJson)
                .build().load().build();

    }

    public static <T> Iterable<T> iterableCasting(final JsonNode jsonNode, Class<T> cls) {

        return () -> new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (jsonNode == null)
                    return false;
                return index < jsonNode.size();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return (T) jsonNode.get(index++);
            }
        };
    }

    public static void copyProperties(JsonNode sourceJsonNode, ObjectNode destJsonNode, String... names) {
        for (String name : names) {
            if (sourceJsonNode.get(name) == null) {
                continue;
            }
            destJsonNode.set(name, sourceJsonNode.get(name));
        }
    }

    /**
     * 예외 발생시 null 반환
     *
     * @param obj
     * @return
     */
    public static String convertObjectToString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //log.error(e.getMessage(), e);
            return null;
        }
    }


    public static <T> T convertStringToObject(String str, Class<T> valueType) {
        try {
            return objectMapper.readValue(str, valueType);
        } catch (JsonProcessingException e) {
            //log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T convertStringToObject(String str, TypeReference<T> valueType) {
        try {
            return objectMapper.readValue(str, valueType);
        } catch (JsonProcessingException e) {
           //log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T convertMapToObject(Map map, Class<T> valueType) {
        return objectMapper.convertValue(map, valueType);
    }

}
