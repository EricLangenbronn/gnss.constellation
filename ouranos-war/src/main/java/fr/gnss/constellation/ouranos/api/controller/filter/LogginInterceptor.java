package fr.gnss.constellation.ouranos.api.controller.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogginInterceptor implements HandlerInterceptor {

	/**
	 * Le logger de la classe.
	 */
	private final static Logger LOGGER_AUDIT = LoggerFactory.getLogger("OURANOS_AUDIT");
	private final static Logger LOGGER = LoggerFactory.getLogger(LogginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		MDC.put("start_time", String.valueOf(System.currentTimeMillis()));
		MDC.put("url", request.getRequestURI());
		MDC.put("method", request.getMethod());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse respons, Object handler, ModelAndView mav) throws Exception {

		String stTime = MDC.get("start_time");
		if (null == stTime || stTime.length() == 0) {

			return;
		}

		long startTime = Long.parseLong(stTime);
		long executionTime = System.currentTimeMillis() - startTime;
		MDC.put("execution_time", String.valueOf(executionTime));
		// MDC.put("http_status", String.valueOf(mav.getStatus()));

		LOGGER.debug("Total request execution time : {} milliseconds", executionTime);
		// LOGGER_AUDIT.info("");
	}
}
