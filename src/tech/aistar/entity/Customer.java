package tech.aistar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:客户实体类
 * @date 2019/5/6 0006
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "orders")
public class Customer implements Serializable{

    @EqualsAndHashCode.Include
    private Integer id;

    private String cname;

    //一个客户对应多个订单
    private Set<Order> orders;

}
