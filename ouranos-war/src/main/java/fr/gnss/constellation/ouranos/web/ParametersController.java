package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.junit.runner.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.services.bean.Resultats;

@Controller
@RequestMapping(value = "/parameters")
public class ParametersController {

	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public String showRegistrationForm() {
		return "parameters";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(Parameters parameters) {
		Resultats res = new Resultats();
		try {
			executionService.setParameters(null);
			executionService.launchExecution(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/display/" + parameters.getElevationMask();
	}

	public void setConfigurationService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}

}
