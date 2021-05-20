package ied.easy.mapper;

import ied.easy.bean.Comment;
import ied.easy.bean.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言功能
 */
@Mapper
public interface CommentMapper {
    //统计留言的总数(每页二十条)
    int count(Page<Comment> page);
    //查询所有新闻信息
    List<Comment> findPage(Page<Comment> page);
    //通过ID获得对应的用户留言
    Comment get(Integer id);

    /**
     * 根据主键删除留言信息
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据用户ID更新对应的留言信息
     */
    int update(Comment comment);

}
