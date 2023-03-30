package springboot.mybatis.com.api.mapper.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductVo {
    private Integer id;
    private String name;
    private String type;
    private float price;
    private Date modidyDate;
}
