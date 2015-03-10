package org.friendlytutor.inv01.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Inv01Controller {

	@RequestMapping("/")
	public String showHome() {
		return "inv01";
	}
}
