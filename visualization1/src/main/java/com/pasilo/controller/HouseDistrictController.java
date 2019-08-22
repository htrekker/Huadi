package com.pasilo.controller;

import com.pasilo.utils.CityNameUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/city/district")
public class HouseDistrictController {

	@RequestMapping(value = "/{city}")
	public String getCityPage(@PathVariable(name = "city") String city, Model model, HttpServletRequest request) {
		String code = CityNameUtils.getCityCode(city);
		HttpSession session = request.getSession();

		if(session.getAttribute("status") != null){
			boolean flag = (boolean) session.getAttribute("status");
			System.out.println(flag);
			if (flag) {// 检查用户是否登录，如果登录 将标记置为true
				System.out.println(city);
				model.addAttribute("city", code.toUpperCase());
				return "district";
			}
			return "redirect:/login";
		}else{
			return "redirect:/login";
		}
	}
}
