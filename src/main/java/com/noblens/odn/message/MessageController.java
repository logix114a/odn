package com.noblens.odn.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/message")
public class MessageController {

	@GetMapping("")
	public ModelAndView home() {
		return new ModelAndView("message/home");
	}
}
