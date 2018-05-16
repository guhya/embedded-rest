package gframework.common.request;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import gframework.util.Util;

public class CustomWebArgumentResolver implements HandlerMethodArgumentResolver {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(Box.class);
	}

	@SuppressWarnings("rawtypes")
	public Object resolveArgument(MethodParameter methodParameter, 
								ModelAndViewContainer mavContainer,
								NativeWebRequest webRequest, 
								WebDataBinderFactory binderFactory) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		Box paramBox = new Box();
		
		//Extract form/querystring parameters to hashmap and bind them in a box		
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			logger.info("########## Param key : " + key);
			String[] values = request.getParameterValues(key);
			if (values != null) {
				paramBox.put(key, (values.length > 1) ? values : values[0]);
			}
		}
		
		//All fields in request variable are string, match type
		paramBox = postProcessBox(request, paramBox);		
		logger.info("########## Box : " + paramBox.toString());
		
		return paramBox;
	}
	
	private Box postProcessBox(HttpServletRequest request, Box paramBox) throws Exception{
		
		//Set request metadata
		
		paramBox.put("modIp", Util.getClientIP(request));
		paramBox.put("regIp", Util.getClientIP(request));
		
		return paramBox;
		
	}
}