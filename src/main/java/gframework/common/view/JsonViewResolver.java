package gframework.common.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @Class JsonViewResolver.java
 * @Description Handle JSON return type
 * @author ghy
 * @since 2016-12-22
 */

public class JsonViewResolver implements ViewResolver{

	/**
	 * Return JSON representation of model map
	 * 	<br/>1. Serialized model is named with key "json"
	 * @param view name (URL)
	 * @param locale
	 * @return view
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		
		final String key = "json";
		
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setModelKey(key);
		view.setPrettyPrint(true);
		
		return view;
	}

}
