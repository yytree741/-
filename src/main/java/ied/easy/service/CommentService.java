package ied.easy.service;
/**
 * 留言功能
 */
import ied.easy.bean.Comment;
import ied.easy.bean.Page;
import ied.easy.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tym on 2017/6/22.
 */
@Service
@Transactional(readOnly = true)
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    // 留言分页
    public Page<Comment> findPage(Page<Comment> page){
        page.setTotalCount(commentMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(commentMapper.findPage(page));
        return page;
    }

    /**
     * 查询所有的留言
     * @param page
     * @return
     */
    public List<Comment> findList(Page<Comment> page){
        return commentMapper.findPage(page);
    }

    /**
     *  据用户ID查询对应的留言信息
     * @param id
     * @return
     */
    public Comment get(Integer id){
        return commentMapper.get(id);
    }

    /**
     * 根据用户ID更新对应的留言信息
     * @param comment
     * @return
     */
    @Transactional(readOnly = false)
    public void  save(Comment comment){
        //如果ID存在
        if (comment.getId()==null){
            //添加
        }else{
            //修改
           commentMapper.update(comment);
        }

}

    /**
     * 根据主键删除留言信息
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(Integer id){
        return commentMapper.delete(id);
    }

}
