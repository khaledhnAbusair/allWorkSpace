package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@RequestMapping(path = "/hello", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String hello(@RequestParam(name = "name", required = false, defaultValue = "Anonymous") String name) {
		return "Hello " + name;
	}

}
