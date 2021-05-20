package ied.easy.service.front_service;

import ied.easy.bean.Page;
import ied.easy.bean.Product;
import ied.easy.mapper.front_mapper.FProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tym on 2017/6/27.
 */
@Service
@Transactional(readOnly = true)
public class FProductService {
    @Autowired
    private FProductMapper fProductMapper;

    // 分页用
    public Page<Product> findPage(Page<Product> page){
        page.setTotalCount(fProductMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(fProductMapper.findPage(page));
        return page;
    }

    //查询所有产品信息
    public List<Product> findList(Page<Product> page){
        return fProductMapper.findPage(page);
    }

    public Product get(Integer id){
        return fProductMapper.get(id);
    }

    //通过Cookie查询近期浏览
    public  List<Product> findRecentProducts(String ids){
        return fProductMapper.findRecentProducts(ids);
    }
    //通过Cookie添加购物车
    public  List<Product> addCartProducts(String ids){
        return fProductMapper.addCartProducts(ids);
    }


}
