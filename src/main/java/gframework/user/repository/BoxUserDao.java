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

import gframework.common.request.Box;

/**
 * @Class UserDAO.java
 * @Description User Management Data Access Object
 * @author ghy
 * @since 2016-11-29
 */
@Repository("boxUserDao")
public class BoxUserDao {
	
	@Resource(name = "derbySession")	
	private SqlSession sqlSession;
	
	/**
	 * List all matching records
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return List of HashMaps
	 * @exception Exception
	 */
	public List<Map<String, Object>> list(Box paramBox) throws Exception {
		return sqlSession.selectList("User.list", paramBox.getMap());
	}
	
	/**
	 * Count all matching records
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return Integer number of rows
	 * @exception Exception
	 */
	public int countList(Box paramBox) throws Exception {
		return sqlSession.selectOne("User.countList", paramBox.getMap());
	}
	
	/**
	 * Get an item based on .parameter
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return HashMap item
	 * @exception Exception
	 */
	public Map<String, Object> select(Box paramBox) throws Exception {
		return sqlSession.selectOne("User.select", paramBox.getMap());
	}
	
	/**
	 * Insert item into table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int last inserted ID (sequence)
	 * @exception Exception
	 */
	public int insert(Box paramBox) throws Exception {
		Map<String, Object> map = paramBox.getMap();
		int seq = sqlSession.insert("User.insert", map);
		seq = map.get("seq") == null ? seq : Integer.parseInt(map.get("seq").toString());
		
		return seq;
	}

	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int update(Box paramBox) throws Exception {		
		return sqlSession.update("User.update", paramBox.getMap());
	}
	
	/**
	 * Delete item from table, only mark it as 'deleted'
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int delete(Box paramBox) throws Exception {
		return sqlSession.update("User.delete", paramBox.getMap());
	}

	/**
	 * Insert role(s) of a particular user
	 * @param paramBox HTTP request parameters wrapped in HashMap
	 * @return int number affected rows
	 * @throws Exception
	 */
	public int insertRole(Box paramBox) throws Exception {
		return sqlSession.insert("User.insertRole", paramBox.getMap());
	}
	
	/**
	 * Return roles of a particular user
	 * @param paramBox HTTP request parameters wrapped in HashMap
	 * @return List of user roles
	 * @throws Exception
	 */
	public List<String> listUserRoles(Box paramBox) throws Exception {
		return sqlSession.selectList("User.listUserRoles", paramBox.getMap());
	}
	
	/**
	 * Update particular item in the table
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return int updated ID (sequence)
	 * @exception Exception
	 */
	public int updatePassword(Box paramBox) throws Exception {		
		return sqlSession.update("User.updatePassword", paramBox.getMap());
	}

	/**
	 * Mark enabled as 'Y' if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int confirmEmail(Box paramBox) throws Exception{
		return sqlSession.update("User.confirmEmail", paramBox.getMap());
	}
	
	/**
	 * Reset password if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int resetPassword(Box paramBox) throws Exception{
		return sqlSession.update("User.resetPassword", paramBox.getMap());
	}
	
	/**
	 * Check if secret matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkSecret(Box paramBox) throws Exception{
		return sqlSession.selectOne("User.checkSecret", paramBox.getMap());
	}	
	
	
	/**
	 * Update secret key field
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return updated record
	 * @throws Exception
	 */
	public int updateSecret(Box paramBox) throws Exception{
		return sqlSession.update("User.updateSecret", paramBox.getMap());
	}
	
	/**
	 * Check if username matches
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @return result count
	 * @throws Exception
	 */
	public int checkUsername(Box paramBox) throws Exception{
		return sqlSession.selectOne("User.checkUsername", paramBox.getMap());
	}
	
}
