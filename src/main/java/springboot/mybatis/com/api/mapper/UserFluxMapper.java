package springboot.mybatis.com.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import springboot.mybatis.com.api.mapper.vo.UserVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserFluxMapper {

    private final UserMapper userMapper;
    //@Autowired
    //@Qualifier("omotaScheduler")
    //private Scheduler scheduler;

    public Mono<UserVo> getUser(Map map) {

        return Mono.fromCallable(() -> {
            UserVo data = userMapper.getUser(map);
            return data == null ? new UserVo() : data;
        });//.subscribeOn(scheduler);
    }

    public Mono<List<UserVo>> getAll(){

        return Mono.fromCallable(() -> {
            List<UserVo> dataList = userMapper.getAll();
            return dataList == null ? new ArrayList<UserVo>() : dataList;
        });//.subscribeOn(scheduler);
    }

}
