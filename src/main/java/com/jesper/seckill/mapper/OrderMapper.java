package com.jesper.seckill.mapper;

import com.jesper.seckill.bean.OrderInfo;
import com.jesper.seckill.bean.SeckillOrder;
import org.apache.ibatis.annotations.*;

/**
 * Created by jiangyunxiong on 2018/5/23.
 */
@Mapper
public interface OrderMapper {


    @Select("select * from sk_order where user_id = #{userId} and goods_id = #{goodsId}")
    public SeckillOrder getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);


    /**
     * 通过@SelectKey使insert成功后返回主键id，也就是订单id
     * @param orderInfo
     * @return
     */
    @Insert("insert into sk_order_info(id, user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{id}, #{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    //@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = long.class)
    //⭐如果插入语句中显式传递了 id 值（如使用应用层生成的ID，如Snowflake算法），且数据库自增未启用，@SelectKey 会覆盖应用层赋值的 id，强行从数据库获取无效的0值
    public long insert(OrderInfo orderInfo);


    @Insert("insert into sk_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertSeckillOrder(SeckillOrder seckillOrder);

    @Select("select * from sk_order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId")long orderId);

}
