package ied.easy.service.front_service;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import ied.easy.mapper.front_mapper.FProCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/28.
 */
@Service
@Transactional(readOnly = true)
public class FProCategoryService {
    @Autowired
    private FProCategoryMapper fProCategoryMapper;

    // 分页用
    public Page<ProCategory> findPage(Page<ProCategory> page){
        page.setTotalCount(fProCategoryMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(fProCategoryMapper.findPage(page));
        return page;
    }

    //查询所有新闻信息
    public List<ProCategory> findList(Page<ProCategory> page){
        return fProCategoryMapper.findPage(page);
    }

    public ProCategory get(Integer id){
        return fProCategoryMapper.get(id);
    }

}
