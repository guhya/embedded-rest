package gframework.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gframework.common.request.Box;
import gframework.user.repository.BoxUserDao;

@Service("boxUserService")
public class BoxUserService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "boxUserDao")
	private BoxUserDao boxUserDao;
	
	public List<Map<String, Object>> list(Box paramBox) throws Exception {
		return boxUserDao.list(paramBox);
	}
	
	public int countList(Box paramBox) throws Exception {
		return boxUserDao.countList(paramBox);
	}

	public Map<String, Object> select(Box paramBox) throws Exception{
		return boxUserDao.select(paramBox);
	}

	/**
	 * Insert item into table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int last inserted ID (sequence)
	 * @exception Exception
	 */
	public int insert(Box paramBox) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(paramBox.getString("password"));
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		paramBox.put("password", hashedPassword);
		
		int r = boxUserDao.insert(paramBox);
		
		return r;
	}	
	
	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int update(Box paramBox) throws Exception {

		int r = boxUserDao.update(paramBox);
		
		return r;
	}
	
	/**
	 * Delete item from table, only mark it as 'deleted'
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int delete(Box paramBox) throws Exception {
		return boxUserDao.delete(paramBox);
	}
	
	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int updatePassword(Box paramBox) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(paramBox.getString("password"));
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		paramBox.put("password", hashedPassword);

		return boxUserDao.updatePassword(paramBox);
	}
	
	/**
	 * Mark enabled as 'Y' if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int confirmEmail(Box paramBox) throws Exception{
		return boxUserDao.confirmEmail(paramBox);
	}
	
	/**
	 * Reset password if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int resetPassword(Box paramBox) throws Exception{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(paramBox.getString("password"));
		
		logger.info("##### [Bcrypt] Hashed password : " + hashedPassword);
		paramBox.put("password", hashedPassword);
		
		return boxUserDao.resetPassword(paramBox);
	}
		
	/**
	 * Check if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkSecret(Box paramBox) throws Exception{
		return boxUserDao.checkSecret(paramBox);
	}
	
	/**
	 * Update secret key field
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int updateSecret(Box paramBox) throws Exception{
		return boxUserDao.updateSecret(paramBox);
	}
	
	/**
	 * Check if username matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkUsername(Box paramBox) throws Exception{
		return boxUserDao.checkUsername(paramBox);
	}
	
	public int insertRole(Box paramBox) throws Exception{
		return boxUserDao.insertRole(paramBox);
	}
	
	public List<String> listUserRoles(Box paramBox) throws Exception{
		return (List<String>) boxUserDao.listUserRoles(paramBox);
	}
	

}
