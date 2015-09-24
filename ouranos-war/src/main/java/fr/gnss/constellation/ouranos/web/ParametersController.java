package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.model.Resultats;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.wrapper.WrapperParameters;

@Controller
@RequestMapping(value = "/parameters")
public class ParametersController {

	@Autowired
	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public String showRegistrationForm() {
		return "parameters";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(Parameters parameters) {
		try {
			executionService.setParameters(WrapperParameters.wrapperParameter(parameters));
			executionService.setProcessComplet(false);
			executionService.launchExecution();
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return "redirect:/display";
	}

	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}
}
