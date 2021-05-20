package ied.easy.mapper;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lyt on 2017/6/23.
 */
@Mapper
public interface ProCategoryMapper {
    int count(Page<ProCategory> page);
    ProCategory get(Integer id);
    // 查询列表信息
    List<ProCategory> findPage(Page<ProCategory> page);
    //删除
    int delete(Integer id);
    //更新
    int update(ProCategory proCategory);
    //添加
    int insert(ProCategory proCategory);
}
