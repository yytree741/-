package ied.easy.mvc;
/**
 * 留言功能
 */

import ied.easy.bean.Comment;
import ied.easy.bean.Page;
import ied.easy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by tym on 2017/6/22.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"list",""})
    public String findList(Page<Comment> page, Model model){
        page=commentService.findPage(page);
        model.addAttribute("page",page);
        return "message_list";
    }
    @RequestMapping("/json/list")
    @ResponseBody
    public List<Comment> jsonList(Page<Comment> page){
        return commentService.findList(page);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        commentService.delete(id);
        return "redirect:/comment";
    }

    //更新，跳转页面
    @RequestMapping("/update.html")
    public String update(Comment comment,Model model){
        comment=commentService.get(comment.getId());
        //存储查询出来值，方便传入下一个跳转页面
        model.addAttribute("comment",comment);
        return "message_list_update";
    }
    //更新，执行代码
    @RequestMapping("/update")
    public String updateInfo(Comment comment,Model model){
        comment.setEc_reply_time(new Date());
        comment.setEc_state("已回复");
        commentService.save(comment);
        return "redirect:/comment";
    }


}
