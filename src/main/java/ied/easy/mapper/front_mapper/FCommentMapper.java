package ied.easy.mapper.front_mapper;

import ied.easy.bean.Comment;
import ied.easy.bean.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by as on 2017/6/27.
 */
@Mapper
public interface FCommentMapper {
    int count(Page<Comment> page);
    List<Comment> findPage(Page<Comment> page);
    Comment get(Integer id);
    //添加
    int insert(Comment comment);
}
