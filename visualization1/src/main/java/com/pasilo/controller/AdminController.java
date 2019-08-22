package com.pasilo.controller;

import com.pasilo.bean.User;
import com.pasilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/admin/pg{page:\\d+}", "/admin"}, method = RequestMethod.GET)
	public String getAdminPage(@PathVariable(value = "page", required = false) Integer pageIndex, Model model, HttpServletResponse response) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		int maxPage = userService.getPageCount(20);
		System.out.println("maxium page size:" + maxPage);
		if (pageIndex - 1 > maxPage) {
			response.setStatus(404);
			return "admin";
		}
		List<User> users = userService.getUsersInRange((pageIndex - 1) * 20, 20);
		model.addAttribute("page_size", maxPage);
		model.addAttribute("current_page", pageIndex);
		System.out.println(Arrays.toString(users.toArray()));
		model.addAttribute("items", users);
		return "admin";
	}

	@RequestMapping(value = "/delete/{username}", method = RequestMethod.POST)
	@ResponseBody
	public String deleteuserByName(@PathVariable(value = "username", required = false) String username) {

		System.out.println(username);
		userService.deleteUserByname(username);

		return "SUCCESS";
	}

	@RequestMapping(value = "/admin/change", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(String username, String password, @RequestParam(required = false) String type) {
		User user = new User(username, password);
		if("on".equals(type)){
			System.out.println("equals to on....");
			user.setType(0);
		}else{
			user.setType(1);
		}
		userService.updateUserAdmin(user);

		return "SUCCESS";
	}
}
