package com.pasilo.controller;

import com.pasilo.bean.User;
import com.pasilo.dao.UserMapper;
import com.pasilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/settings")
	public String editSettings(String id, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			return "settings";
		}

		return "redirect:login";
	}

	@RequestMapping(value="/user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserByName(@PathVariable("username") String username){
		System.out.println("user controller:"+username);
		User target = userService.getUserByName(username);
		System.out.println(target);
		return target;
	}

	@RequestMapping(value="/delete/{username}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("username") String username, HttpServletRequest request){
		int code = -3;
		if(username != null){
			code = userService.deleteUserByname(username);
		}

		if(code == 0){
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("status");
			request.getSession().setAttribute("code","deletesuccess");
			return "redirect:/login";
		}

		return "redirect:/login";
	}

	@RequestMapping(value = "/chpasswd", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(String newPassword, HttpServletRequest request){
		if("".equals(newPassword)){
			return "FATAL";
		}
		HttpSession session = request.getSession();

		if(session.getAttribute("username") == null){
			return "OFFLINE";
		}
		String name = (String) session.getAttribute("username");
		userService.updateUser(new User(name, newPassword));
		return "SUCCESS";
	}

	@RequestMapping(value = "/chUsername", method = RequestMethod.POST)
	@ResponseBody
	public String changeUsername(String newUsername){
		return null;
	}
}
