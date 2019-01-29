/*package org.web.blog.security;

import org.apache.shiro.authc.UsernamePasswordToken;

*//**
 * tokenBean
 * @author xin
 *
 *//*
public class AuthenticationToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 9058345379045556588L;

	private String captchaId;
	
	private String captcha;
	
	*//**
	 * token参数
	 * @param username 用户名
	 * @param password 密码
	 * @param captchaId 拓展(验证码ID)
	 * @param captcha 拓展(验证码)
	 * @param rememberMe 是否记住我
	 * @param host ip
	 *//*
	public AuthenticationToken(String username, String password,String captchaId, String captcha, boolean rememberMe,
			String host) {
		super(username, password, rememberMe, host);
		this.captchaId = captchaId;
		this.captcha = captcha;
	}

	public String getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
*/