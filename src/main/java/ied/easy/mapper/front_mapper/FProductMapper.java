package ied.easy.mapper.front_mapper;

import ied.easy.bean.Page;
import ied.easy.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tym on 2017/6/27.
 * 首页商品展示
 */
@Mapper
public interface FProductMapper {
    int count(Page<Product> page);
    Product get(Integer id);
    List<Product> findPage(Page<Product> page);
    //通过Cookie查询近期浏览
    public  List<Product> findRecentProducts(@Param("ids") String ids);
    //通过Cookie添加购物车
    public  List<Product> addCartProducts(@Param("ids") String ids);
}
