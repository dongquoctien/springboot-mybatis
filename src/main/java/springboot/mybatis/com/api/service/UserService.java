package springboot.mybatis.com.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import springboot.mybatis.com.api.common.CommonJsonUtil;
import springboot.mybatis.com.api.common.CommonUtil;
import springboot.mybatis.com.api.mapper.UserMapper;
import springboot.mybatis.com.api.mapper.vo.UserListVo;
import springboot.mybatis.com.api.mapper.vo.UserVo;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public Mono<JsonNode> getAll(JsonNode request){
        Map conditionMap = CommonUtil.getConditionMap(request);


        List<UserListVo> datas =  userMapper.getAll(conditionMap);
        Integer totalCount = 0;
        if (datas.size() > 0) {
            totalCount = datas.get(0).getTotalCount();
        }

        return Mono.just(CommonUtil.getBaseTypeNodePage(
                    JsonNodeFactory.instance.objectNode(),
                    totalCount,
                    CommonJsonUtil.convertVoToArrayNode(datas)
                ));
    }

    public Mono<JsonNode> getUserById(JsonNode request){
        Map conditionMap = CommonUtil.getId(request);
        UserVo data = userMapper.getUser(conditionMap);

        return Mono.just(CommonUtil.getBaseTypeNode(CommonJsonUtil.convertVoToJsonNode(data)));
    }
}
