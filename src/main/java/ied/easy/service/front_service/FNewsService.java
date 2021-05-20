package ied.easy.service.front_service;

import ied.easy.bean.News;
import ied.easy.bean.Page;
import ied.easy.mapper.front_mapper.FNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/27.
 */
@Service
@Transactional(readOnly = true)
public class FNewsService {
    @Autowired
    private FNewsMapper fNewsMapper;

    // 分页用
    public Page<News> findPage(Page<News> page){
        page.setTotalCount(fNewsMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(fNewsMapper.findPage(page));
        return page;
    }

    public List<News> findList(Page<News> page){
        return fNewsMapper.findPage(page);
    }

    public News get(Integer id){
        return fNewsMapper.get(id);
    }

}
