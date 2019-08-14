package com.pasilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String welcome(Model model) {
		boolean flag = false;
		if (false) {// 检查用户是否登录，如果登录 将标记置为true
			flag = true;
		}
		model.addAttribute("status", flag);
		return "main";
	}

}
