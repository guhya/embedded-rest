package gframework.common.exception;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String DEFAULT_ERROR_VIEW 	= "cmmn/defaultException";
	
	private static final String MESSAGE_EXCEPTION 				= "exception.common";
	private static final String MESSAGE_SQL_EXCEPTION 			= "[003] SQL exception occured";
	private static final String MESSAGE_NOT_READABLE_ERROR 		= "error.badRequest";
	private static final String MESSAGE_NOT_WRITABLE_ERROR 		= "error.badResponse";

	public static final String EXCEPTION		= "exception";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e, Locale locale) throws Exception {
		
		logger.error(e.getMessage(), e);
		
		ModelAndView mav = new ModelAndView();
		mav = prepareMav(mav, locale, MESSAGE_EXCEPTION);
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    return mav;
	}
	  
	@ExceptionHandler(SQLException.class)
	public ModelAndView sql(HttpServletRequest req, Exception e, Locale locale) throws Exception {
		
		logger.error(e.getMessage(), e);
		
	    ModelAndView mav = new ModelAndView();
		mav.addObject(EXCEPTION		, MESSAGE_SQL_EXCEPTION);
	    mav.setViewName("cmmn/dataAccessException");
	    return mav;
	}
	  
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ModelAndView badRequest(HttpServletRequest req, Exception e, Locale locale) throws Exception {
		
		logger.error(e.getMessage(), e);
		
	    ModelAndView mav = new ModelAndView();
		mav = prepareMav(mav, locale, MESSAGE_NOT_READABLE_ERROR);
	    mav.setViewName("cmmn/badRequestError");
	    return mav;
	}

	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ModelAndView badResponse(HttpServletRequest req, Exception e, Locale locale) throws Exception {
		
		logger.error(e.getMessage(), e);
		
	    ModelAndView mav = new ModelAndView();
		mav = prepareMav(mav, locale, MESSAGE_NOT_WRITABLE_ERROR);
	    mav.setViewName("cmmn/badResponseError");
	    return mav;
	}

	
	private ModelAndView prepareMav(ModelAndView mav, Locale locale, String code){
		final String msg = code;
		mav.addObject(EXCEPTION		, msg);
	    mav.setViewName(DEFAULT_ERROR_VIEW);
		
		return mav;
	}
	
}
