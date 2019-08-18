package com.pasilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


	@RequestMapping(value = "/settings")
	public String editSettings(String id, Model model){

		model.addAttribute("username", "");

		return "settings";
	}

}
