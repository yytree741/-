package ied.easy.mapper;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import ied.easy.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lyt on 2017/6/23.
 */
@Mapper
public interface ProductMapper {
    int count(Page<Product> page);
    Product get(Integer id);
    // 查询列表信息
    List<Product> findPage(Page<Product> page);


    //修改商品信息
    int update(Product product);
    //添加商品信息
    int add(Product product);
    //根据主键删除对应ID的商品信息
    int delete(Integer id);

}
