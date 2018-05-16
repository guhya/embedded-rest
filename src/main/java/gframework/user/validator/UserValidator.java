package gframework.user.validator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gframework.user.data.UserVo;
import gframework.user.service.UserService;

/**
 * @Class UserValidator.java
 * @Description Validate form, check mandatory fields, correctness, 
 *  sent back all error messages on fail.
 * @author ghy
 * @since 2017-01-18
 */

@Service
public class UserValidator {
	
	@Resource(name="userService")
	UserService userService;
	
	public Map<String, String> validate(UserVo userVo) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		//TODO add validation email regex
		
		//Check if username is already registered
		if(!"".equals(userVo.getSeq())){
			Map<String, String> userMap = userService.select(userVo);
			if(userMap.get("username") != null){
				errors.put("username", "This username has already been registered, please use another.");
			}
		}
		
		return errors;
		
	}
	
	public Map<String, String> validatePassword(UserVo userVo) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		
		//Match old and new password on user edit
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String rawPassword = userVo.getOldPassword();
				
		Map<String, String> userMap = userService.select(userVo);
		String oldHashedPassword = userMap.get("password");
		
		if(!passwordEncoder.matches(rawPassword, oldHashedPassword)){
			errors.put("oldPassword", "Old password does not match");
		}
		
		return errors;
		
	}
	
	public Map<String, String> validateResetPassword(UserVo userVo) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		//TODO declare and check all mandatory fields
		
		int count = userService.checkSecret(userVo);
		if(count != 1){
			errors.put("username", "We cannot reset your password, please contact administrator");
		}
		
		return errors;
		
	}
	
	public Map<String, String> validateIfUsernameExist(UserVo userVo) throws Exception {
		
		Map<String, String> errors = new HashMap<String, String>();
		
		Map<String, String> userMap = userService.select(userVo);
		if(userMap.get("username") == null){
			errors.put("username", "There is no data in our records with this username");
		}
		
		return errors;
		
	}
	
}
