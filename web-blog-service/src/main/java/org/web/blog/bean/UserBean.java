package org.web.blog.bean;

import java.io.Serializable;

/**
 * Title : 用户bean<br>
 * Project : web-blog-service <br>
 * Class : org.web.blog.bean.UserBean <br>
 * Description : <br>
 * Date : 2018年1月23日 <br>
 *
 * @author WB
 * @version 1.0
 */
public class UserBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6873459562093918887L;

    private String userId;
    private String userName;
    private String userPassWd;
    private String userPhone;
    private String userEmail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWd() {
        return userPassWd;
    }

    public void setUserPassWd(String userPassWd) {
        this.userPassWd = userPassWd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserBean [userId=" + userId + ", userName=" + userName
                + ", userPassWd=" + userPassWd + ", userPhone=" + userPhone
                + ", userEmail=" + userEmail + "]";
    }
    
    

}
