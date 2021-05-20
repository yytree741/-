package ied.easy.mvc;

import ied.easy.bean.Page;
import ied.easy.bean.ProCategory;
import ied.easy.service.ProCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
/**
 * 分类管理
 */

/**
 * Created by lyt on 2017/6/23.
 */
@Controller
@RequestMapping("/procate")
public class ProCategoryController {
    @Autowired
    private ProCategoryService proCategoryService;

    @RequestMapping(value = {"list",""})
    public String findList(Model model, Page<ProCategory> page){
        // 设置每一页的页数
        page.setPageSize(6);
        page = proCategoryService.findPage(page);
        model.addAttribute("page",page);
        return "classification_list";
    }

    @RequestMapping("/json/listAll")
    @ResponseBody
    public List<ProCategory> jsonList(Page<ProCategory> page){
        return proCategoryService.findList(page);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        proCategoryService.delete(id);
        return "redirect:/procate";
    }
    @RequestMapping("/update.html")
    public String update(ProCategory proCategory,Model model){
        proCategory = proCategoryService.get(proCategory.getId());
        model.addAttribute("proCategory",proCategory);
        return "classification_list_update";
    }

    @RequestMapping("/update")
    public String update(ProCategory proCategory){
        proCategoryService.save(proCategory);
        return "redirect:/procate";
    }

    @RequestMapping("/add.html")
    public String add(){
        return "classification_list_add";
    }
    @RequestMapping("/add")
    public String add(ProCategory proCategory){
        proCategoryService.save(proCategory);
        return "redirect:/procate";
    }
}
