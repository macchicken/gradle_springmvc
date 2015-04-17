package main.java.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import main.java.model.User;
import main.java.util.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * can not use SessionAttributes annoation with ModelMap while has json methods,
 * it would cause ERR_INCOMPLETE_CHUNKED_ENCODING when try to create a new session
 */
@Controller
public class LoginController {

	@RequestMapping(value = "loginAction.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> AceAdminLogin(String username,String password,String registerEmail,HttpSession session){
		if (session.getAttribute(Constants.ACEUSER)==null){
			if (StringUtils.isEmpty(username)){username="Barry";}
			User my=new User(username,Calendar.getInstance().getTime());
			session.setAttribute(Constants.ACEUSER, my);
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}
}
