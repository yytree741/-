package ied.easy.mapper.front_mapper;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lyt on 2017/6/28.
 */
@Mapper
public interface FProCategoryMapper {
    List<ProCategory> findPage(Page<ProCategory> page);
    int count(Page<ProCategory> page);
    ProCategory get(Integer id);
}
