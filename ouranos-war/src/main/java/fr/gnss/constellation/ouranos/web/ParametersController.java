package fr.gnss.constellation.ouranos.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	private Parameters parameterInError = null;

	@RequestMapping(method = GET)
	public String showParameterForm(Model model) {

		Parameters parameters = null;
		if (parameterInError != null) {
			parameters = parameterInError;
		} else {
			parameters = new Parameters();
		}
		model.addAttribute("parameters", parameters);
		return "parameters";
	}

	@RequestMapping(method = POST)
	public String processLaunchParameters(@Valid Parameters parameters, BindingResult result,
			Model model) {

		String nextPage = "";

		if (result.hasErrors()) {
			LOGGER.error("validation incorrecte : " + result.getAllErrors());
			nextPage = "parameters";
		} else {

			executionService.setParameters(WrapperParameters.wrapperParameter(parameters));
			executionService.setProcessComplet(false);
			try {
				executionService.launchExecution();
				parameterInError = null;
				nextPage = "redirect:/display";
			} catch (TechnicalException e) {
				String l_message = "Erreur technique lors de l'exécuion";
				LOGGER.info(l_message, e);
				nextPage = "parameters";
			} catch (BusinessException e) {
				String l_message = "Paramètre incorecte, pas d'exécution";
				LOGGER.info(l_message, e);
				parameterInError = parameters;
				model.addAttribute("errorMessage", e.getMessage());
				nextPage = "parameters";
			}
		}

		return nextPage;
	}

	public void setExecutionService(OuranosExecutionService executionService) {
		this.executionService = executionService;
	}
}