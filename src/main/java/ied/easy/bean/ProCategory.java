package ied.easy.bean;

/**
 * Created by lyt on 2017/6/22.
 */
public class ProCategory {
    private Integer id;
    private String epc_name;
    private Integer epc_parent_id;
    private Integer level;
    private String path;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEpc_name() {
        return epc_name;
    }

    public void setEpc_name(String epc_name) {
        this.epc_name = epc_name;
    }

    public Integer getEpc_parent_id() {
        return epc_parent_id;
    }

    public void setEpc_parent_id(Integer epc_parent_id) {
        this.epc_parent_id = epc_parent_id;
    }

    @Override
    public String toString() {
        return "ProCategory{" +
                "id=" + id +
                ", epc_name='" + epc_name + '\'' +
                ", epc_parent_id=" + epc_parent_id +
                ", level=" + level +
                ", path='" + path + '\'' +
                '}';
    }
}
