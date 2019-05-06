package tech.aistar.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:订单实体类
 * @date 2019/5/6 0006
 */
@Data
public class Order implements Serializable{
    private Integer id;

    private String ordNo;

    private Double price;

    private Date createDate;

    private OrderStatus orderStatus;
}
