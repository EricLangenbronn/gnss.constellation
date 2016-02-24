package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.gnss.constellation.ouranos.bean.Parameters;
import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.services.OuranosExecutionService;
import fr.gnss.constellation.ouranos.services.impl.OuranosExecutionServiceImpl;
import fr.gnss.constellation.ouranos.wrapper.WrapperParameters;

@Controller
@RequestMapping(value = "/parameters")
public class ParametersController {

	/**
	 * Le logger de la classe.
	 */
	private static final Log LOGGER = LogFactory.getLog(ParametersController.class);

	@Autowired
	private OuranosExecutionService executionService;

	@RequestMapping(method = GET)
	public String showRegistrationForm() {
		return "parameters";
	}

	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(Parameters parameters, ModelMap model) {

		String nextPage = "";

		executionService.setParameters(WrapperParameters.wrapperParameter(parameters));
		executionService.setProcessComplet(false);
		try {
			executionService.launchExecution();
			nextPage = "redirect:/display";
		} catch (TechnicalException e) {
			String l_message = "Erreur technique lors de l'exécuion";
			LOGGER.info(l_message, e);
			model.addAttribute("success", "false");
			nextPage = "redirect:/parameters";
		} catch (BusinessException e) {
			String l_message = "Paramètre incorecte, pas d'exécution";
			LOGGER.info(l_message, e);
			model.addAttribute("success", "false");
			nextPage = "redirect:/parameters";
		}

		return nextPage;
	}

	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}
}
