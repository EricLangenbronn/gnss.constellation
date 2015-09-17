package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import fr.gnss.constellation.ouranos.bean.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.wrapper.WrapperResultats;

@Controller
@RequestMapping(value = "/display")
public class DisplayController {

	@Autowired
	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public ModelAndView display(Model model) {
		ModelAndView mav = new ModelAndView("display");
		
		return mav;
	}

	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}

	@RequestMapping(value = "listDateTime", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Resultats listDateTime() {
		Resultats res = WrapperResultats.wrapperResultats(executionService.getResultats());

		return res;
	}

}
