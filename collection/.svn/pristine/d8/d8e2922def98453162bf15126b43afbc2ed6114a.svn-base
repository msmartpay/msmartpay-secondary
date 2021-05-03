package in.softsolutionzone.interceptor;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.softsolutionzone.exception.UnAuthorizeAccessException;
import lombok.extern.java.Log;

@Component
@Log
public class GlobalInterceptor extends HandlerInterceptorAdapter {



	public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object handler)
			throws Exception {
		log.info("GlobalInterceptor class in preHandle method:::");
		boolean status=false;

		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			status=true;
		}else {
			Map<String, String> headers = Collections.list(request.getHeaderNames())
					.stream()
					.collect(Collectors.toMap(h -> h, request::getHeader));

			// token validation
			/*
			 * UserDetails user_details=authService.authorize(headers);
			 * if(user_details!=null) {
			 * request.setAttribute("user_details", user_details);
			 * status=true;
			 * }else{
			 * throw new UnAuthorizeAccessException("Please provide valid credentials");
			 * }
			 */
			status=true;
		}
		return status;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
