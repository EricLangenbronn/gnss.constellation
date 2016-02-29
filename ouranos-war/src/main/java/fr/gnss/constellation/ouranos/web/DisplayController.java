package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.gnss.constellation.ouranos.bean.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.wrapper.WrapperResultats;

@Controller
@RequestMapping(value = "/display")
public class DisplayController {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(DisplayController.class);

	@Autowired
	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public String showGraphForm(Model model) {
		return "display";
	}

	@RequestMapping(value = "visibility", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Resultats visibilitySatelite() {

		Resultats res = null;
		if (executionService.isProcessComplet()) {
			res = WrapperResultats.wrapperResultatsVisibility(executionService.getResultats());
		} else {
			res = new Resultats();
		}

		return res;
	}
	
	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}

}
