package ied.easy.service;

import ied.easy.bean.News;
import ied.easy.bean.Page;

import ied.easy.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by as on 2017/6/22.
 */
@Service
@Transactional(readOnly = true)
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    // 分页用
    public Page<News> findPage(Page<News> page){
        page.setTotalCount(newsMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(newsMapper.findPage(page));
        return page;
    }

    //查询所有新闻信息
    public List<News> findList(Page<News> page){
        return newsMapper.findPage(page);
    }

    public News get(Integer id){
        return newsMapper.get(id);
    }
    @Transactional(readOnly = false)
    //删除新闻信息
    public int delete(Integer id){
       return newsMapper.delete(id);
    }

    @Transactional(readOnly = false)
    public void save(News news){
        if(news.getId() == null){
            // 添加
            newsMapper.insert(news);
        }else{
            // 更新
            newsMapper.update(news);
        }
    }

}
