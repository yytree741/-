package ied.easy.bean;

/**
 * Created by lyt on 2017/6/22.
 */
public class Product {
    private Integer id;
    private String ep_name;
    private String ep_description;
    private Double ep_price;
    private Integer ep_stock;
    private ProCategory proCategory;
    private Integer epc_child_id;
    private String ep_file_name;
    // 分类时存放String
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEp_name() {
        return ep_name;
    }

    public void setEp_name(String ep_name) {
        this.ep_name = ep_name;
    }

    public String getEp_description() {
        return ep_description;
    }

    public void setEp_description(String ep_description) {
        this.ep_description = ep_description;
    }

    public Double getEp_price() {
        return ep_price;
    }

    public void setEp_price(Double ep_price) {
        this.ep_price = ep_price;
    }

    public Integer getEp_stock() {
        return ep_stock;
    }

    public void setEp_stock(Integer ep_stock) {
        this.ep_stock = ep_stock;
    }

    public ProCategory getProCategory() {
        return proCategory;
    }

    public void setProCategory(ProCategory proCategory) {
        this.proCategory = proCategory;
    }

    public Integer getEpc_child_id() {
        return epc_child_id;
    }

    public void setEpc_child_id(Integer epc_child_id) {
        this.epc_child_id = epc_child_id;
    }

    public String getEp_file_name() {
        return ep_file_name;
    }

    public void setEp_file_name(String ep_file_name) {
        this.ep_file_name = ep_file_name;
    }
}
