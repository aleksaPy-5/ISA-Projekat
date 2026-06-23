package rs.singidunum.projekat.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {
	
	@GetMapping({"/","/web"})
	public String index() {
		return "index";
	}
}
