package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String welcome() {
		return "redirect:/users";
	}

	@GetMapping(value = "users")
	public String allUsers(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}

	@GetMapping(value = "users/add")
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}

	@PostMapping(value = "users/add")
	public String addUser(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "users/editUser")
	public String editUser(ModelMap model, @RequestParam("id") Long id) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@GetMapping(value = "users/edit")
	public String edit(@ModelAttribute("user") User user) {
		userService.editUser(user);
		return "redirect:/";
	}

	@GetMapping("users/delete")
	public String deleteUserById(@RequestParam("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}
}