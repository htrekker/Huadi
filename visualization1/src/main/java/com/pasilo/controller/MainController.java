package com.pasilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("status") != null){
			boolean flag = (boolean) session.getAttribute("status");
			System.out.println(flag);
			if (flag) {// 检查用户是否登录，如果登录 将标记置为true
				model.addAttribute("status", flag);
				model.addAttribute("taget","career");
				return "main";
			}
			return "redirect:login";
		}else{
			model.addAttribute("status",false);
			return "redirect:login";
		}
	}

	@RequestMapping(value = "/house", method = RequestMethod.GET)
	public String houseAnalysis(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();

		model.addAttribute("status", true);

		return "house";
	}

}
