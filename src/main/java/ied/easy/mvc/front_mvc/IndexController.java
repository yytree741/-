package ied.easy.mvc.front_mvc;

import ied.easy.bean.*;
import ied.easy.mapper.front_mapper.FProCategoryMapper;
import ied.easy.service.front_service.FCommentService;
import ied.easy.service.front_service.FNewsService;
import ied.easy.service.front_service.FProCategoryService;
import ied.easy.service.front_service.FProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lyt on 2017/6/26.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private FNewsService fNewsService;
    @Autowired
    private FProCategoryService fProCategoryService;
    @Autowired
    private FCommentService fCommentService;
    @Autowired
    private FProductService fProductService;

    @RequestMapping(value = {"","index"})
    public String index(Model model, HttpServletRequest request){
        // 列表显示所有新闻信息
        Page<News> page_news = new Page<News>();
        page_news=fNewsService.findPage(page_news);
        model.addAttribute("page_news",page_news);

        // 列表显示所有分类信息
        Page<ProCategory> page_procate = new Page<ProCategory>();
        page_procate = fProCategoryService.findPage(page_procate);
        model.addAttribute("page_procate",page_procate);



        //近期浏览产品列表展示  从Cookie中取值
        Cookie[] cookies=request.getCookies();
        //将取取出来的值放入字符集合temp中
        List<String> temp=new ArrayList<String>();
        //遍历取出的Cookie的值
        if(cookies!=null){
            for(Cookie c:cookies){
                //获取取出的对应值的名字
                String name=c.getName();
                //分辨所取是否是所需的Cookie值，之前添加了一个识别标志，就是在每个ID前面添加“lastViewProduct_”标志
                if(name.contains("lastViewProduct_")){
                    //去掉识别标志 split将所取的值截取为两段，将下划线作为一个截取的识别标志，
                    // 分为“lastViewProduct”和产品“ID”两段，其中“1”表示获取后面的“ID”字段
                    temp.add(name.split("_")[1]);
                }
            }
            //将获取的ID保存在一个字符串中，方便后面的sql语句查询
            String tmp="";
            //遍历所取的ID值，并在每个ID后面添加一个逗号，例如：1,2,3，
            for(String s:temp){
                tmp+=s+",";
            }
            //截取字段，去掉最后ID后面的那个逗号，例如：1，2,3
            if (tmp.length()>0){
                tmp=tmp.substring(0,tmp.length()-1);
                //新建sql查询语句(多ID查询)，用集合接收查询出来的对应产品ID的具体值
                List<Product> recentList=fProductService.findRecentProducts(tmp);
                //保存通过Cookie查询出来的值，方便index页面进行查询使用
                model.addAttribute("recentList",recentList);
            }

        }
        // 列表显示所有商品信息
        Page<Product> page_product = new Page<Product>();
        page_product= fProductService.findPage(page_product);
        model.addAttribute("page_product",page_product);
        return "front_tai/index";
    }

    // 查询新闻详细信息
    @RequestMapping("/find/news")
    public String findnews(Page<News> page_news, Model model, @RequestParam("id") Integer id){
        /**
         * 方法一：
         * Param("id") Integer id
         * News news = fNewsService.get(Integer.parseInt(id));
         * Integer.parseInt(id) ：转换为整数型
         *
         * 方法二：
         * News news
         * news = fNewsService.get(news.getId());
         */
        News news = fNewsService.get(id);
        // 传值给news-view.html界面显示右边的相信内容
        model.addAttribute("findnews",news);
        page_news=fNewsService.findPage(page_news);
        model.addAttribute("page_news",page_news);
        return "front_tai/news-view";
    }

    /**
     * 用户留言
     * @return
     */
    @RequestMapping("/add.html")
    public String addCommenthtml(Page<Comment> page_comment, Model model){
        // 查询所有的留言
        page_comment = fCommentService.findPage(page_comment);
        model.addAttribute("page_comment",page_comment);
        return "front_tai/guestbook";
    }

    /**
     * 提交留言（添加）
     * @param comment
     * @return
     */
    @RequestMapping("/add")
    public String addComment(Comment comment){
        comment.setEc_create_time(new Date());
        comment.setEc_state("未回复");
        fCommentService.save(comment);
        return "redirect:/add.html";
    }

    /**
     * 跳转到商品详情界面，通过传ID显示商品信息
     * HttpServletRequest  : 请求
     * HttpServletResponse ：响应
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/view.html")
    public String productView(@Param("id") Integer id, Model model, HttpServletRequest request, HttpServletResponse response){
        //获取Cookie
        Cookie[] cookies=request.getCookies();
        //用一个字符串集合来装截取出来对应商品的值
        List<String> productList=new ArrayList<String>();
        //浏览商品的时间
        long time=0;
        //最先浏览的商品
        String minProduct="";
        if(cookies!=null){
            for (Cookie cookie:cookies){
                //判断从cookie中取出的值是否是“lastViewProduct_”开头
                if (cookie.getName().startsWith("lastViewProduct_")){
                    //在列表中加入在cookie中获取到的对应商品的名称(加了“lastViewProduct_”前缀)
                    productList.add(cookie.getName());
                    //得到最早加入的商品
                    if (time==0){
                        minProduct=cookie.getName();
                        time=Long.parseLong(cookie.getValue().equals("")?"0":cookie.getValue());
                        //加?及其后面的是为了防止返回的值为空返回0，0是整数，而值是字符串，所以要将0转换为字符串"0"
                    }else if (time<Long.parseLong(cookie.getValue().equals("")?"0":cookie.getValue())){
                        minProduct=cookie.getName();
                        //更新浏览商品的时间
                        time=Long.parseLong(cookie.getValue());
                    }
                }
            }
        }else{
            Cookie cookie=new Cookie("lastViewProduct_"+id,new Date().getTime()+"");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }

        /**
         * 判断当前浏览商品是否已经在最近浏览列表里
         */
        if (productList.contains("lastViewProduct_"+id)){
            for(Cookie cc:cookies){
                if(cc.getName().equals("lastViewProduct_"+id)){
                    cc.setMaxAge(3600);
                    response.addCookie(cc);
                }
            }
            // Cookie cookie=new Cookie("lastViewProduct_"+id,new Date().getTime()+"");
            // cookie.setMaxAge(3600);//3600毫秒后删除cookie
            // response.addCookie(cookie);
        }else {
            if (productList.size()>=4){//假如最近浏览显示五条记录
                Cookie cookie=new Cookie(minProduct,null);
                cookie.setMaxAge(-1);
                response.addCookie(cookie);
            }
            Cookie cookie=new Cookie("lastViewProduct_"+id,new Date().getTime()+"");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }


        Product product=fProductService.get(id);
        //存储前一个页面的product数据在product中，方便下一个页面的获取
        model.addAttribute("product",product);
        return "front_tai/product-view";
    }

    /**
     * 在商品详情界面里传ID进行跳转，跳转到购物车页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/shopping.html")
    public String ShoppingHtml(@Param("id") String id, Model model, HttpServletRequest request, HttpServletResponse response){
        //购物车
        Cookie[] cookies=request.getCookies();
        //集合存放购物车Cookie的值
        List<String> cartProductList=new ArrayList<String>();
        //查询新获取的cookie是否存在，不存在则新建名为：“cartProduct_”+id
        if (cookies==null){
            Cookie cookie=new Cookie("CartProduct_"+id,new Date().getTime()+"");
            cookie.setMaxAge(36000);
            response.addCookie(cookie); //将获取的"cartProduct_"+id  添加到cookie中
            //将cookie中对应的购物车"cartProduct_"+id 的cookie拿出来进行截取，获取商品的id，最后添加到集合中去
            cartProductList.add(cookie.getName().split("_")[1]);
        }else{
            //如果存在则遍历全部Cookie，然后取出包含“cartProduct_”的Cookie的值，并将其截取
            boolean current=false;//表示当前商品在cookie中是否存在，默认值为不存在
            for(Cookie c:cookies){
                String name=c.getName();
                if(name.contains("CartProduct_")){
                    System.out.println("ssss");
                    if(name.equals("CartProduct_"+id)){ //如果cookie中存在
                        //153-156行的注释代码是对的，但是不管有没有都添加，太浪费资源
                        //Cookie cookie=new Cookie("cartProduct_"+id,new Date().getTime()+"");
                        // cookie.setMaxAge(36000);
                        //response.addCookie(cookie);
                        // cartProductList.add(name.split("_")[1]);
                        current=true;   //返回不添加
                    }
                    String s = name.split("_")[1];
                    System.out.println(s);
                    cartProductList.add(s);
                }
            }
            if(!current){   //如果不存在，则添加
                Cookie cookie=new Cookie("CartProduct_"+id,new Date().getTime()+"");
                cookie.setMaxAge(36000);   //设置过期时间
                response.addCookie(cookie);
                cartProductList.add(cookie.getName().split("_")[1]);
            }
        }
        //读取购物车Cookie
        for(Cookie c:cookies){
            //获取取出的对应值的名字
            String name=c.getName();
            if(name.contains("CartProduct_")){
                cartProductList.add(name.split("_")[1]);
        }
    }
         String tmp="";
        for (String s:cartProductList){ //遍历集合中的元素 例如由123，改为1,2，3，  方便后面的多ID查询数据库
            tmp+=s+",";
        }
        if (tmp.length()>0){  //如果获取的ID集合的字符串的长度大于零   防止第一次出现显示为空的错误，
            tmp=tmp.substring(0,tmp.length()-1);
            List<Product> cartList=fProductService.addCartProducts(tmp);     //多ID查询数据库
            model.addAttribute("cartList",cartList);
        }


        Product product=fProductService.get(Integer.parseInt(id));
        //存储前一个页面的product数据在product中，方便下一个页面的获取
        model.addAttribute("product",product);
        return "front_tai/shopping";
    }

    /**
     * 点击分类信息跳转到商品界面
     */
    @RequestMapping("/products/list")
    public String Product_shopping(Model model){
        // 列表显示所有商品信息
        Page<Product> page_products = new Page<Product>();
        page_products= fProductService.findPage(page_products);
        model.addAttribute("page_products",page_products);

        // 列表显示所有分类信息
        Page<ProCategory> page_procate = new Page<ProCategory>();
        page_procate = fProCategoryService.findPage(page_procate);
        model.addAttribute("page_procate",page_procate);
        return "front_tai/product-list";
    }

    /**
     * 用户登录注销
     * @param
     */
    @RequestMapping("/logout.html")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("user");
        return "redirect:/";

    }

}
