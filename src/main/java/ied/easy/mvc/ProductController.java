package ied.easy.mvc;

import com.sun.org.apache.xpath.internal.operations.Mod;
import ied.easy.bean.Page;
import ied.easy.bean.Product;
import ied.easy.mapper.ProductMapper;
import ied.easy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lyt on 2017/6/23.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"list",""})
    public String findList(Product product, Page<Product> page,Model model){
        //product.setParam("%"+product.getEpc_id()+",%");
        //product.setParam("%"+product.getEpc_id()+",%");
        //page.setO(product);
        page.setPageSize(4);
        page = productService.findPage(page);
        model.addAttribute("page",page);
        return "goods_list";
    }

    @RequestMapping("/json/listAll")
    public List<Product> jsonList(Page<Product> page){
        return productService.findList(page);
    }

    /**
     * 后台商品管理中的更新功能页面跳转
     * @param product
     * @param model
     * @return
     */
    @RequestMapping("/update.html")
    public String updateHtml(Product product,Model model){
        //获取需要更新的商品ID
        product=productService.get(product.getId());
        System.out.println("这是product的数据："+product.toString());
        //将数据存储起来，方便带入跳转到的页面里面
        model.addAttribute("product",product);
        return "goods_list_update";
    }
    //实现更新功能
    @RequestMapping("/update")
    public String update(Product product){
        product.getEp_file_name();
        product.getId();
        System.out.println("ID="+product.getId()+"的娃哈哈的更新："+product.toString());
        productService.save(product);
        return "redirect:/product";
    }

    /**
     * 后台商品管理中的添加功能页面跳转
     */
    @RequestMapping("/add.html")
    public String addHtml(){
        return "goods_list_add";
    }
    @RequestMapping("/add")
    public String add(HttpSession session,Product product){
        product.getEp_file_name();
        System.out.println("娃哈哈："+product.getEp_file_name());
        productService.save(product);
        return "redirect:/product";
    }

    /**
     * 处理商品删除功能
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        productService.delete(id);
        return "redirect:/product";
    }


    //处理图片上传
    @ResponseBody
    @RequestMapping("/upload/images")
    public Map uploadImage(MultipartRequest request,HttpSession session,Model model){
        Map map = new HashMap();
        try {
            MultipartFile file = request.getFile("file");
            if (file != null) {
                String errorLine = "";
                //获取输入流
                InputStream is = file.getInputStream();
                //获取绝对路径
                ServletContext sc=session.getServletContext();
                String rootPath=sc.getRealPath("/");
                String savaDir=rootPath+"uploads/";
                System.out.println("猜猜我在哪儿？\n答:"+savaDir);
                //如果上传目录不存在则新建
                File dir=new File(savaDir);
                if (!dir.isDirectory()){
                    dir.mkdirs();
                }
                //获取上传文件的后缀
                String uploadFileName=file.getOriginalFilename();
                String suffixName=uploadFileName.substring(uploadFileName.lastIndexOf("."));
                //上传的文件名
                String saveFileName= UUID.randomUUID().toString()+suffixName;
                //获取输出流，即存放到硬盘上
                FileOutputStream fos=new FileOutputStream(savaDir+saveFileName);
                //写入硬盘
                byte[] buf=new byte[4096];
                int len=-1;
                while ((len=is.read(buf))>0){
                    fos.write(buf,0,len);
                }
                fos.flush();
                fos.close();
                is.close();
                map.put("msg","上传成功");
                map.put("file","/uploads/"+saveFileName);
            }else {
                map.put("msg","没有上传文件");
            }
        }catch (IOException e){
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }


}
