package gframework.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Util {
	
	public static String stripTags(String raw){
		String cooked = "";
		if(!"".equals(raw)){
			String pattern = "<[^>]+>";
			cooked = raw.replaceAll(pattern, "");
			cooked = cooked.replace("&nbsp;", " ");
			cooked = cooked.replace("&quot;", "\"");
			cooked = cooked.replace("&ndash;", "-");		
		}
		
		return cooked;
	}
	
	public static String escapeHtml(String raw){
		String cooked = "";
		if(!"".equals(raw)){
			cooked = HtmlUtils.htmlEscape(raw);
		}
		
		return cooked;
	}
		
	
	public static String stringCut(String raw, int length){
		String cooked = "";
		if(!"".equals(raw)){
			if(raw.length() > length){
				cooked = raw.substring(0, length) + "...";
			}else{
				cooked = raw;
			}
		}
		
		return cooked;
	}	
	
	public static String formatDate(String raw, String pattern){
		String cooked = "";
		pattern = pattern == null ? "yyyy-MM-dd" : pattern;
		if(!"".equals(raw)){
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				Date date = sdf.parse(raw);
				cooked = sdf.format(date);
			} catch (ParseException e) {
				cooked =  "";
			}
		}
		
		return cooked;
	}
	
	public static String getClientIP(HttpServletRequest request) {
		String clientIp = request.getHeader("x-forwarded-for");
		if((clientIp == null || clientIp.length() == 0) || "unknown".equals(clientIp)) {
			clientIp = request.getHeader("REMOTE_ADDR");
			if((clientIp == null || clientIp.length() == 0) || "unknown".equals(clientIp)) {
				clientIp = request.getRemoteAddr();
			}
		}
		return clientIp;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object convertToVo(Object obj, Class clazz){
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		
		Object vo = new Object();
		vo = objMapper.convertValue(obj, clazz);
		
		return vo;
	}
	
	public static String generateSecret(){
		String result = "";
		
		try {
			String sNow = String.valueOf(System.currentTimeMillis());
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(sNow.getBytes());
			BigInteger bigInt = new BigInteger(1, digest);
			result = bigInt.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
