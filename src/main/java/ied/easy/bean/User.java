package ied.easy.bean;

import java.util.Date;

/**
 * Created by lyt on 2017/6/21.
 */
public class User {
    //用户ID
    private Integer id;
    //用户名
    private String eu_user_id;
    //真实姓名
    private String eu_user_name;
    //密码
    private String eu_password;
    //性别,0为女，1为男
    private String eu_sex;
    //出生日期
    private Date eu_birthday;
    //身份证号
    private String eu_identity_code;
    //email
    private String eu_email;
    //手机号码
    private String eu_mobile;
    //地址
    private String eu_address;
    // 类型，1普通用户，2管理员
    private Integer eu_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEu_user_id() {
        return eu_user_id;
    }

    public void setEu_user_id(String eu_user_id) {
        this.eu_user_id = eu_user_id;
    }

    public String getEu_user_name() {
        return eu_user_name;
    }

    public void setEu_user_name(String eu_user_name) {
        this.eu_user_name = eu_user_name;
    }

    public String getEu_password() {
        return eu_password;
    }

    public void setEu_password(String eu_password) {
        this.eu_password = eu_password;
    }

    public String getEu_sex() {
        return eu_sex;
    }

    public void setEu_sex(String eu_sex) {
        this.eu_sex = eu_sex;
    }

    public Date getEu_birthday() {
        return eu_birthday;
    }

    public void setEu_birthday(Date eu_birthday) {
        this.eu_birthday = eu_birthday;
    }

    public String getEu_identity_code() {
        return eu_identity_code;
    }

    public void setEu_identity_code(String eu_identity_code) {
        this.eu_identity_code = eu_identity_code;
    }

    public String getEu_email() {
        return eu_email;
    }

    public void setEu_email(String eu_email) {
        this.eu_email = eu_email;
    }

    public String getEu_mobile() {
        return eu_mobile;
    }

    public void setEu_mobile(String eu_mobile) {
        this.eu_mobile = eu_mobile;
    }

    public String getEu_address() {
        return eu_address;
    }

    public void setEu_address(String eu_address) {
        this.eu_address = eu_address;
    }

    public Integer getEu_status() {
        return eu_status;
    }

    public void setEu_status(Integer eu_status) {
        this.eu_status = eu_status;
    }

    @Override
    public String toString() {
        return  eu_user_id ;
    }
}
