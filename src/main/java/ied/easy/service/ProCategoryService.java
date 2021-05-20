package ied.easy.service;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import ied.easy.mapper.ProCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/23.
 */
@Service
@Transactional(readOnly = true)
public class ProCategoryService {
    @Autowired
    private ProCategoryMapper proCategoryMapper;

    // 分页用
    public Page<ProCategory> findPage(Page<ProCategory> page){
        page.setTotalCount(proCategoryMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(proCategoryMapper.findPage(page));
        return page;
    }

    //查询所有新闻信息
    public List<ProCategory> findList(Page<ProCategory> page){
        return proCategoryMapper.findPage(page);
    }

    public ProCategory get(Integer id){
        return proCategoryMapper.get(id);
    }

    //删除
    @Transactional(readOnly = false)
    public int delete(Integer id){return proCategoryMapper.delete(id);}

    //更新
    @Transactional(readOnly = false)
    public void  save(ProCategory proCategory){
        if (proCategory.getId()==null){
            //增加
            proCategoryMapper.insert(proCategory);
        }else {
            //更新
            proCategoryMapper.update(proCategory);
        }
    }

}
