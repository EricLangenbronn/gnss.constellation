package fr.gnss.constellation.ouranos.api.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogginInterceptor implements HandlerInterceptor {

	private final static Logger LOGGER_AUDIT = LoggerFactory.getLogger("OURANOS_AUDIT");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		request.setAttribute("start_time", String.valueOf(System.currentTimeMillis()));
		request.setAttribute("url", request.getRequestURI());
		request.setAttribute("method", request.getMethod());

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		String stTime = (String) request.getAttribute("start_time");
		if (null == stTime || stTime.length() == 0) {
			return;
		}

		long startTime = Long.parseLong(stTime);
		long executionTime = System.currentTimeMillis() - startTime;

		LOGGER_AUDIT.info("Total request execution time : {} milliseconds : url= {}, methode={}, status={}", executionTime,
				String.valueOf(request.getAttribute("url")), String.valueOf(request.getAttribute("method")), String.valueOf(response.getStatus()));
	}
}
