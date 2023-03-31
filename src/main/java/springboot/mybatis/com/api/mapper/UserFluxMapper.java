package springboot.mybatis.com.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import springboot.mybatis.com.api.mapper.vo.UserListVo;
import springboot.mybatis.com.api.mapper.vo.UserVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserFluxMapper {

    private final UserMapper userMapper;

    public Mono<UserVo> getUser(Map map) {

        return Mono.fromCallable(() -> {
            UserVo data = userMapper.getUser(map);
            return data == null ? new UserVo() : data;
        });
    }

    public Mono<List<UserListVo>> getAll(Map map){
        return Mono.fromCallable(() -> {
            List<UserListVo> dataList = userMapper.getAll(map);
            return dataList == null ? new ArrayList<UserListVo>() : dataList;
        });
    }

}
