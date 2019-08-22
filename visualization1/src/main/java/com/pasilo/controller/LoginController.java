package com.pasilo.controller;

import com.pasilo.bean.User;
import com.pasilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		return "admin_login";
	}

	@RequestMapping(value = "/login/admin", method = RequestMethod.POST)
	public String adminLogin(HttpServletRequest request, String username, String password, Model model) {
		HttpSession session = request.getSession();

		boolean flag = userService.ifAdmin(new User(username, password));

		if (flag) {
			model.addAttribute("status", flag);
			session.setAttribute("username", username);
			session.setAttribute("status", flag);

			return "redirect:/admin";
		}else{
			model.addAttribute("code", -1);
			session.setAttribute("status", flag);
			return "redirect:/login/admin";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login_page(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("code") != null) {
			if ("deletesuccess".equals(session.getAttribute("code"))) {
				model.addAttribute("code", "delete_success");
			}
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, @RequestParam(name = "passwd") String passwrod, HttpServletRequest request, Model model) {
		boolean flag = userService.passwordCorrect(new User(username, passwrod));
		System.out.println(flag);

		HttpSession session = request.getSession();
		if (flag) {
			model.addAttribute("status", flag);
			model.addAttribute("code", "safe");
			session.setAttribute("status", flag);
			session.setAttribute("username", username);
			return "redirect:main";
		} else {
			model.addAttribute("code", -1);
			session.setAttribute("status", flag);
			return "login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String createAccount(String username, String password, String rePassword, HttpServletRequest request) {
		System.out.println(rePassword);
		if (username == null || "".equals(password) || "".equals(rePassword)) {
			return "FATAL";// 没输入
		}

		int status = userService.addUser(new User(username, password));
		System.out.println(status);

		if (status == 0) {
			return "EXISTED";// 用户名已经存在
		} else if (status < 0) {
			return "ILLEGAL";// 用户命名不合法
		}

		request.getSession().setAttribute("status", true);
		return "SUCCESS";// 插入成功
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("status", false);

		return "redirect:login";
	}

}
