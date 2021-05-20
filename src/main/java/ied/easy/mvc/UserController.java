package ied.easy.mvc;

import ied.easy.bean.*;
import ied.easy.service.UserService;

import ied.easy.service.front_service.FNewsService;
import ied.easy.service.front_service.FProCategoryService;
import ied.easy.service.front_service.FProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.Date;
import java.util.List;

/**
 * Created by lyt on 2017/6/21.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FNewsService fNewsService;
    @Autowired
    private FProCategoryService fProCategoryService;
    @Autowired
    private FProductService fProductService;

    @RequestMapping("/login.html")
    public String loginhtml(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, Model model, @ModelAttribute("user")User user
            , @RequestParam("yanzhengma") String yanzhengma, HttpServletRequest request){
        User user2 =new User();
        user2.setEu_user_id(user.getEu_user_id());
        List<User> list = userService.findList(user2);
        if(yanzhengma == null || !yanzhengma.equals("7635")){
            model.addAttribute("demo","验证码错误");
            return "login";
        }
        if(list.size()>0 && list.get(0).getEu_password().equals(user.getEu_password())){
            // 判断是管理员登录还是会员登录。2是管理员登录，1是会员登录
            if(list.get(0).getEu_status()==2){
                session.setAttribute("user",list.get(0));
                // 分页时的绝对路径
                String path = request.getContextPath();
                session.setAttribute("ctx",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path);
                return "manage";
            }else{
                // 为了在登录过去的时候拿到新闻数据内容
                Page<News> page_news = new Page<News>();
                page_news=fNewsService.findPage(page_news);
                model.addAttribute("page_news",page_news);
                // 为了在登录过去的时候拿到分类数据内容
                Page<ProCategory> page_procate = new Page<ProCategory>();
                page_procate = fProCategoryService.findPage(page_procate);
                model.addAttribute("page_procate",page_procate);
                // 为了在登录过去的时候拿到商品详情数据内容
                Page<Product> page_product = new Page<Product>();
                page_product= fProductService.findPage(page_product);
                model.addAttribute("page_product",page_product);

                session.setAttribute("user",list.get(0).getEu_user_id());
                return "front_tai/index";
            }

        }else{
            model.addAttribute("message","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/register.html")
    public String registerHtml(){
        return "register";
    }
    @RequestMapping("register")
    public String register(@ModelAttribute("user")User user, @RequestParam("repassword")String repassword,
                           @RequestParam("yanzhengma") String yanzhengma,Model model){
        if (repassword==null || !repassword.equals(user.getEu_password())){
            model.addAttribute("message","两次输入密码不一致");
            return "register";
        }else {
            if(yanzhengma == null || !yanzhengma.equals("7635")){
                model.addAttribute("demo","验证码错误");
                return "register";
            }
            user.setEu_password(user.getEu_password());
            user.setEu_status(1);
            userService.save(user);
            model.addAttribute("message","注册成功");
            return "register_success";
        }
    }

    @RequestMapping(value = {"list",""})
    public String list(Model model, Page<User> page){
        page = userService.findPage(page);
        model.addAttribute("page",page);
        return "user_list";
    }

    @RequestMapping("/json/listAll")
    @ResponseBody
    public List<User> jsonfindList(Page<User> page){
        return userService.findListAll(page);
    }

    @RequestMapping("/update.html")
    public String update(User user,Model model){
        // 更新的时候要把id传到界面,需要以下两段代码
        user = userService.get(user.getId());
        model.addAttribute("user",user);
        return "user_update";
    }

    @RequestMapping("/update")
    public String update(User user,@RequestParam("repassword")String repassword,
                         Model model){
        if (repassword==null || !repassword.equals(user.getEu_password())){
            model.addAttribute("message","两次输入密码不一致");
            return "user_update";
        }
      // user.setEu_birthday(new Date());
        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        userService.delete(id);
        return "redirect:/user";
    }
}
