package springboot.mybatis.com.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.mybatis.com.api.mapper.vo.UserListVo;
import springboot.mybatis.com.api.mapper.vo.UserVo;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    UserVo getUser(Map map);

    List<UserListVo> getAll(Map map);

}
