package gframework.common.view;

import java.util.Locale;

import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * @Class MarshallingXMLViewResolver.java
 * @Description Handle XML return type
 * @author ghy
 * @since 2016-12-22
 */

public class MarshallingXMLViewResolver implements ViewResolver {

	private Marshaller marshaller;
	
	public MarshallingXMLViewResolver(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	
	/**
	 * Return XML representation of model map
	 * 	<br/>1. Serialized model is named with key "xml"
	 * @param view name (URL)
	 * @param locale
	 * @return view
	 */	
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		
		final String key = "xml";

		MarshallingView view = new MarshallingView();
		view.setModelKey(key);
		view.setMarshaller(marshaller);
		
		return view;
	}

}
