package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.gnss.constellation.ouranos.services.OuranosExecutionService;

@Controller
@RequestMapping(value = "/display/*")
public class DisplayController {

	@Autowired
	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public String display(Model model) {

		System.out.println("salut : " + executionService.getResultat());

		return "display";
	}

	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}

}
