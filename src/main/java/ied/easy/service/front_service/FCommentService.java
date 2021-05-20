package ied.easy.service.front_service;

import ied.easy.bean.Comment;
import ied.easy.bean.Page;
import ied.easy.mapper.front_mapper.FCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by as on 2017/6/27.
 */
@Service
@Transactional(readOnly = true)
public class FCommentService {
    @Autowired
    private FCommentMapper fGuestbookMapper;
    // 留言分页
    public Page<Comment> findPage(Page<Comment> page){
        page.setTotalCount(fGuestbookMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(fGuestbookMapper.findPage(page));
        return page;
    }
    public List<Comment> findList(Page<Comment> page){
        return fGuestbookMapper.findPage(page);
    }
    public Comment get(Integer id){
        return fGuestbookMapper.get(id);
    }
    //增加
    @Transactional(readOnly = false)
    public void save(Comment comment){
        fGuestbookMapper.insert(comment);
    }
}
