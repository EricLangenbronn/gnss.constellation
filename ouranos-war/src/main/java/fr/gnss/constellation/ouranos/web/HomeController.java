package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/homepage"})
public class HomeController {
	
	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(HomeController.class);
	
	@RequestMapping(method = GET)
	public String showHomeForm() {
		return "home";
	}
}