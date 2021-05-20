package ied.easy.bean;

import java.util.Date;

/**
 * Created by as on 2017/6/22.
 */
public class News {
    private Integer id;
    private String en_title;
    private String en_content;
    private Date en_create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEn_title() {
        return en_title;
    }

    public void setEn_title(String en_title) {
        this.en_title = en_title;
    }

    public String getEn_content() {
        return en_content;
    }

    public void setEn_content(String en_content) {
        this.en_content = en_content;
    }

    public Date getEn_create_time() {
        return en_create_time;
    }

    public void setEn_create_time(Date en_create_time) {
        this.en_create_time = en_create_time;
    }
}
