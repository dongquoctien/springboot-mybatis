package springboot.mybatis.com.api.mapper.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserListVo {
    private Integer rowId;
    @JsonIgnore
    private Integer totalCount;
    private Integer id;
    private Integer age;
    private String password;
}
