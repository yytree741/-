package ied.easy.mvc;

import ied.easy.bean.News;
import ied.easy.bean.Page;
import ied.easy.bean.User;
import ied.easy.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by as on 2017/6/22.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping(value = {"list",""})
    public String findList(Page<News> page, Model model){
        page = newsService.findPage(page);
        model.addAttribute("page",page);
        return "new_list";
    }
    @RequestMapping("/json/list")
    @ResponseBody
    public List<News> jsonList(Page<News> page){
        return newsService.findList(page);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        newsService.delete(id);
        return "redirect:/news";
    }

    @RequestMapping("/add.html")
    public String addPage(){
        return "new_list_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session,News news){
        news.setEn_create_time(new Date());
        newsService.save(news);
        return "redirect:/news";
    }

    // 更新
    @RequestMapping("/update.html")
    public String updatePage(News news,Model model){
        news = newsService.get(news.getId());
        model.addAttribute("news",news);
        return "new_list_update";
    }

    @RequestMapping("/update")
    public String update(HttpSession session,News news){
        news.setEn_create_time(new Date());
        newsService.save(news);
        return "redirect:/news";
    }

}
