/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gframework.user.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import gframework.user.data.UserVo;

/**
 * @Class UserDAO.java
 * @Description User Management Data Access Object
 * @author ghy
 * @since 2016-11-29
 */
@Repository("userDao")
public class UserDao{
	
	@Resource(name = "mysqlSession")	
	private SqlSession sqlSession;
	
	/**
	 * List all matching records
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return List of HashMaps
	 * @exception Exception
	 */
	public List<Map<String, String>> list(UserVo userVo) throws Exception {
		return sqlSession.selectList("User.list", userVo);
	}
	
	/**
	 * Count all matching records
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return Integer number of rows
	 * @exception Exception
	 */
	public int countList(UserVo userVo) throws Exception {
		return sqlSession.selectOne("User.countList", userVo);
	}
	
	/**
	 * Get an item based on user parameter
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return HashMap item
	 * @exception Exception
	 */
	public Map<String, String> select(UserVo userVo) throws Exception {
		return sqlSession.selectOne("User.select", userVo);
	}
	
	/**
	 * Insert role(s) of a particular user
	 * @param paramBox HTTP request parameters wrapped in HashMap
	 * @return int number affected rows
	 * @throws Exception
	 */
	public int insertRole(UserVo userVo) throws Exception {
		return sqlSession.insert("User.insertRole", userVo);
	}
	
	/**
	 * Return roles of a particular user
	 * @param paramBox HTTP request parameters wrapped in HashMap
	 * @return List of user roles
	 * @throws Exception
	 */
	public List<String> listUserRoles(UserVo userVo) throws Exception {
		return sqlSession.selectList("User.listUserRoles", userVo);
	}
	
	/**
	 * Insert item into table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int last inserted ID (sequence)
	 * @exception Exception
	 */
	public int insert(UserVo userVo) throws Exception {
		int seq = sqlSession.insert("User.insert", userVo);
		seq = userVo.getSeq();
		
		return seq;
	}

	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int update(UserVo userVo) throws Exception {		
		return sqlSession.update("User.update", userVo);
	}
	
	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int updatePassword(UserVo userVo) throws Exception {		
		return sqlSession.update("User.updatePassword", userVo);
	}

	/**
	 * Delete item from table, only mark it as 'deleted'
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int delete(UserVo userVo) throws Exception {
		return sqlSession.update("User.delete", userVo);
	}
	
	/**
	 * Mark enabled as 'Y' if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int confirmEmail(UserVo userVo) throws Exception{
		return sqlSession.update("User.confirmEmail", userVo);
	}
	
	/**
	 * Reset password if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int resetPassword(UserVo userVo) throws Exception{
		return sqlSession.update("User.resetPassword", userVo);
	}
	
	/**
	 * Check if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkSecret(UserVo userVo) throws Exception{
		return sqlSession.selectOne("User.checkSecret", userVo);
	}	
	
	
	/**
	 * Update secret key field
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int updateSecret(UserVo userVo) throws Exception{
		return sqlSession.update("User.updateSecret", userVo);
	}
	
	/**
	 * Check if username matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkUsername(UserVo userVo) throws Exception{
		return sqlSession.selectOne("User.checkUsername", userVo);
	}		
}
