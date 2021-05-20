package ied.easy.mvc;

import ied.easy.bean.Order;
import ied.easy.bean.Page;
import ied.easy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by lyt on 2017/6/25.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // 根据ID、用户名模糊查询列表和全表查询
    @RequestMapping(value = {"list",""})
    public String findPage(Order order,Page<Order> page, Model model){
        // 模糊查询时用的语句
        page.setO(order);
        page = orderService.findPage(page);
        model.addAttribute("page",page);
        return "order_list";
    }

    @RequestMapping("/json/listAll")
    public List<Order> jsonList(Page<Order> page){
        return orderService.findList(page);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Integer id,Model model){
        orderService.delete(id);
        return "redirect:/order";
    }

    @RequestMapping("/update.html")
    public String updatePage(Order order,Model model){
        order = orderService.get(order.getId());
        model.addAttribute("order",order);
        return "order_list_update";
    }

    @RequestMapping("/update")
    public String update(Order order, HttpSession session){
            order.setEo_create_time(new Date());
            orderService.save(order);
            return "redirect:/order";
    }
}
