package springboot.mybatis.com.api.generated;

import springboot.mybatis.com.api.generated.entity.UserEntity;

public interface UserEntityMapper {
    int insertUser(UserEntity user);
    int updateUser(UserEntity user);
    int deleteUser(Integer userId);
}
