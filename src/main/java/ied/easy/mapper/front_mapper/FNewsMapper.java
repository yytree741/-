package ied.easy.mapper.front_mapper;

import ied.easy.bean.News;
import ied.easy.bean.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lyt on 2017/6/27.
 */
@Mapper
public interface FNewsMapper {
    List<News> findPage(Page<News> page);
    News get(Integer id);
    int count(Page<News> page);
}
