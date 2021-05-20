package ied.easy.service;

import ied.easy.bean.Page;
import ied.easy.bean.User;
import ied.easy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyt on 2017/6/21.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // 分页用
    public Page<User> findPage(Page<User> page){
        page.setTotalCount(userMapper.count(page));
        page.setTotalPageCount((page.getTotalCount()-1)/page.getPageSize()+1);
        if(page.getCurrentPage()<1){
            page.setCurrentPage(1);
        }else if(page.getCurrentPage()>page.getTotalPageCount()){
            page.setCurrentPage(page.getTotalPageCount());
        }
        page.setOffset((page.getCurrentPage()-1)*page.getPageSize());
        page.setList(userMapper.findPage(page));
        return page;
    }

    // 查询所有信息（manage首页列表用）
    public List<User> findListAll(Page<User> page){
        return userMapper.findPage(page);
    }

    // 查询账号密码（注册功能）
    public List<User> findList(User user){
        return userMapper.findList(user);
    }

    public User get(Integer id){
        return userMapper.get(id);
    }

    @Transactional(readOnly = false)
    public void save(User user){
        if (user.getId() == null){
            // 注册（添加）
            userMapper.insert(user);
        }else{
            // 修改更新
            userMapper.update(user);
        }
    }
    @Transactional(readOnly = false)
   //删除用户信息
    public int delete(Integer id){return userMapper.delete(id);}

}
