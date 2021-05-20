package ied.easy.service;

import ied.easy.bean.Page;
import ied.easy.bean.Product;
import ied.easy.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/23.
 */
@Service
@Transactional(readOnly = true)
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 分页用
    public Page<Product> findPage(Page<Product> page){
        page.setTotalCount(productMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(productMapper.findPage(page));
        return page;
    }

    //查询所有新闻信息
    public List<Product> findList(Page<Product> page){
        return productMapper.findPage(page);
    }

    public Product get(Integer id){
        return productMapper.get(id);
    }

    //根据ID是否存在来判断是更新还是添加商品信息
    @Transactional(readOnly = false)
    public int save(Product product){
        if (product.getId()==null){
            System.out.println("已经新增了哦");
            //添加
            return productMapper.add(product);
        } else {
            //修改
            System.out.println("已经更新了哦");
            int t=productMapper.update(product);
            return t;

        }
    }

    //根据主键删除对应ID的商品信息
    @Transactional(readOnly = false)
    public int delete(Integer id){
        return productMapper.delete(id);
    }



}
