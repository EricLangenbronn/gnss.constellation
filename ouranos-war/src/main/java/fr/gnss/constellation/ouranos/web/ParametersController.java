package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.services.OuranosConfigurationService;

@Controller
@RequestMapping(value = "/parameters")
public class ParametersController {

	private OuranosConfigurationService configurationService;

	@RequestMapping(method = GET)
	public String showRegistrationForm() {
		return "parameters";
	}

	@RequestMapping(value="/register",method = POST)
	public String processRegistration(Parameters parameters) {
		// TODO
		System.out.println("param : " + parameters);
		return "redirect:/display/" + parameters.getElevationMask();
	}

	public void setConfigurationService(OuranosConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

}
