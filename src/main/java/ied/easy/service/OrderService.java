package ied.easy.service;

import ied.easy.bean.Order;
import ied.easy.bean.Page;
import ied.easy.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/24.
 */
@Service
@Transactional(readOnly = true)
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    // 分页用
    public Page<Order> findPage(Page<Order> page){
        page.setTotalCount(orderMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(orderMapper.findPage(page));
        return page;
    }

    public List<Order> findList(Page<Order> page){
        return orderMapper.findPage(page);
    }

    public Order get(Integer id){
        return orderMapper.get(id);
    }

    // 修改添加
    @Transactional(readOnly = false)
    public void save(Order order){
        if(order.getId() == null){

        }else{
            // 更新
            orderMapper.update(order);
        }
    }

    // 删除
    @Transactional(readOnly = false)
    public int delete(Integer id){
        return orderMapper.delete(id);
    }

}
