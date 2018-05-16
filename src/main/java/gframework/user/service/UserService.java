package gframework.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gframework.user.data.UserVo;
import gframework.user.repository.UserDao;

@Service("userService")
public class UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource(name = "userDao")
	private UserDao userDao;
	
	public List<Map<String, String>> list(UserVo userVo) throws Exception {
		return userDao.list(userVo);
	}
	
	public int countList(UserVo userVo) throws Exception {
		return userDao.countList(userVo);
	}

	public Map<String, String> select(UserVo userVo) throws Exception{
		return userDao.select(userVo);
	}

	/**
	 * Insert item into table
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return int last inserted ID (sequence)
	 * @exception Exception
	 */
	public int insert(UserVo userVo) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userVo.getPassword());
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		userVo.setPassword(hashedPassword);
		
		int r = userDao.insert(userVo);
		
		return r;
	}	
	
	/**
	 * Update particular item in the table
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int update(UserVo userVo) throws Exception {

		int r = userDao.update(userVo);
		
		return r;
	}
	
	/**
	 * Update particular item in the table
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int updatePassword(UserVo userVo) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userVo.getPassword());
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		userVo.setPassword(hashedPassword);

		return userDao.updatePassword(userVo);
	}
	
	/**
	 * Delete item from table, only mark it as 'deleted'
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int delete(UserVo userVo) throws Exception {
		return userDao.delete(userVo);
	}
	
	/**
	 * Mark enabled as 'Y' if secret matches
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int confirmEmail(UserVo userVo) throws Exception{
		return userDao.confirmEmail(userVo);
	}
	
	/**
	 * Reset password if secret matches
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int resetPassword(UserVo userVo) throws Exception{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userVo.getPassword());
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		userVo.setPassword(hashedPassword);
		
		return userDao.resetPassword(userVo);
	}
		
	/**
	 * Check if secret matches
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkSecret(UserVo userVo) throws Exception{
		return userDao.checkSecret(userVo);
	}
	
	/**
	 * Update secret key field
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int updateSecret(UserVo userVo) throws Exception{
		return userDao.updateSecret(userVo);
	}
	
	/**
	 * Check if username matches
	 * @param userVo HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkUsername(UserVo userVo) throws Exception{
		return userDao.checkUsername(userVo);
	}
	
	public int insertRole(UserVo userVo) throws Exception{
		return userDao.insertRole(userVo);
	}
	
	public List<String> listUserRoles(UserVo userVo) throws Exception{
		return (List<String>) userDao.listUserRoles(userVo);
	}
	
}
