package springboot.mybatis.com.api.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> convertJsonNodeToMap(JsonNode jsonNode) {
        try {
            // Map<String, String> map = objectMapper.readValue(jsonNode, Map.class);
            return objectMapper.treeToValue(jsonNode, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> getConditionMap(JsonNode request) {
        Map<String, Object> conditionMap = new HashMap<>();

        //conditionMap = CommonJsonUtil.convertJsonNodeToMap(request);

         if (request.get("condition") != null) {
             JsonNode conditionNode = request.path("condition");
             conditionMap = CommonJsonUtil.convertJsonNodeToMap(conditionNode);

             if(conditionNode.get("limit") !=null) {
                 //get offset
                 Integer limit = Integer.parseInt( conditionNode.at("/limit").asText("0"));
                 Integer page = Integer.parseInt( conditionNode.at("/page").asText("0"));
                 page = page >0 ? page -1 : 0;
                 conditionMap.put("offset",  limit * page);
             }
         }

        return conditionMap;
    }

    public static Map<String, Object> getId(JsonNode request) {
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("id", request.at("/id").asText("0"));
        return conditionMap;
    }

    /**
     * @param resultDataObj
     * @return result > object
     */
    public static JsonNode getBaseTypeNode(JsonNode resultDataObj) {
        ObjectNode rsValue = JsonNodeFactory.instance.objectNode();
        rsValue.set("result", resultDataObj);
        return rsValue;
    }

    /**
     * @param jsonNode
     * @param listKeyNm
     * @return result > list
     */
    public static JsonNode getBaseTypeNode(JsonNode jsonNode, String listKeyNm) {
        ObjectNode rsValue = JsonNodeFactory.instance.objectNode();
        ObjectNode resultDataObj = JsonNodeFactory.instance.objectNode();
        resultDataObj.set(listKeyNm, jsonNode);
        rsValue.set("result", resultDataObj);
        return rsValue;
    }

    /**
     * @param rsValue
     * @param cnt
     * @param jsonNode
     * @return result > totalCount and list
     */
    public static JsonNode getBaseTypeNodePage(ObjectNode rsValue, int cnt, JsonNode jsonNode) {
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        result.put("totalCount", cnt);
        result.set("list", jsonNode);
        rsValue.set("result", result);
        return rsValue;
    }
}
