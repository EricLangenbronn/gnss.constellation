package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/display/*")
public class DisplayController {

	/* private DisplayRepository displayRepository; */

	@RequestMapping(method = GET)
	public String display(Model model) {

		return "display";
	}
}
