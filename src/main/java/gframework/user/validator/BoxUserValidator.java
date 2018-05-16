package gframework.user.validator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gframework.common.request.Box;
import gframework.user.service.BoxUserService;

/**
 * @Class UserValidator.java
 * @Description Validate form, check mandatory fields, correctness, 
 *  sent back all error messages on fail.
 * @author ghy
 * @since 2017-01-18
 */

@Service
public class BoxUserValidator {
	
	@Resource(name="boxUserService")
	BoxUserService boxUserService;
	
	public Map<String, String> validate(Box paramBox) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		//TODO add validation email regex
		
		//Check if username is already registered
		if(paramBox.getString("seq") != null && "".equals(paramBox.getString("seq"))){
			Map<String, Object> userMap = boxUserService.select(paramBox);
			if(userMap != null && userMap.get("username") != null){
				errors.put("username", "This username has already been registered, please use another.");
			}
		}
		
		return errors;
		
	}
	
	public Map<String, String> validatePassword(Box paramBox) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		
		//Match old and new password on user edit
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String rawPassword = paramBox.getString("oldPassword");
				
		Map<String, Object> userMap = boxUserService.select(paramBox);
		String oldHashedPassword = String.valueOf(userMap.get("password"));
		
		if(!passwordEncoder.matches(rawPassword, oldHashedPassword)){
			errors.put("oldPassword", "Old password does not match");
		}
		
		return errors;
		
	}
	
	public Map<String, String> validateResetPassword(Box paramBox) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		
		int count = boxUserService.checkSecret(paramBox);
		if(count != 1){
			errors.put("username", "We cannot reset your password, please contact administrator");
		}
		
		return errors;
		
	}
	
	public Map<String, String> validateIfUsernameExist(Box paramBox) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		Map<String, Object> userMap = boxUserService.select(paramBox);
		if(userMap.get("username") == null){
			errors.put("username", "There is no data in our records with this username");
		}
		
		return errors;
		
	}
	
}
