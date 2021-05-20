package ied.easy.bean;

import java.util.Date;

/**
 * 留言实体类
 * Created by tym on 2017/6/22.
 */
public class Comment {
    public Integer id;
    //针对留言的回复
    private String ec_reply;
    //发表的留言内容
    private String ec_content;
    //创建时间
    private Date ec_create_time;
    //回复时间
    private Date ec_reply_time;
    //留言用户昵称
    private String ec_nick_name;
    //是否回复的状态
    private String ec_state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEc_reply() {
        return ec_reply;
    }

    public void setEc_reply(String ec_reply) {
        this.ec_reply = ec_reply;
    }

    public String getEc_content() {
        return ec_content;
    }

    public void setEc_content(String ec_content) {
        this.ec_content = ec_content;
    }

    public Date getEc_create_time() {
        return ec_create_time;
    }

    public void setEc_create_time(Date ec_create_time) {
        this.ec_create_time = ec_create_time;
    }

    public Date getEc_reply_time() {
        return ec_reply_time;
    }

    public void setEc_reply_time(Date ec_reply_time) {
        this.ec_reply_time = ec_reply_time;
    }

    public String getEc_nick_name() {
        return ec_nick_name;
    }

    public void setEc_nick_name(String ec_nick_name) {
        this.ec_nick_name = ec_nick_name;
    }

    public String getEc_state() {
        return ec_state;
    }

    public void setEc_state(String ec_state) {
        this.ec_state = ec_state;
    }
}
