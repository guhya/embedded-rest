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
package gframework.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gframework.common.request.Box;
import gframework.user.data.UserListVo;
import gframework.user.data.UserVo;
import gframework.user.service.BoxUserService;
import gframework.user.service.UserService;
import gframework.user.validator.BoxUserValidator;
import gframework.util.Util;

/**
 * @Class UserController.java
 * @Description User Manager Controller Class
 * @author ghy
 * @since 2017-01-08
 */

@Controller
public class ManagerUserController {

	@Resource(name = "boxUserService")
	private BoxUserService boxUserService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "boxUserValidator")
	private BoxUserValidator boxUserValidator;
	
	/**
	 * List all items
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String View Name
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/list.do")
	public String list(ModelMap model, Box paramBox) throws Exception {
		
		List<Map<String, Object>> userList 	= boxUserService.list(paramBox);
		
		model.addAttribute("user", 			userList);
		
		//JSON response need no preparation
		model.addAttribute("json", 			userList);
		
		//Prepare object for XML response
		List<UserVo> userVoList 	= new ArrayList<UserVo>();
		for(Map<String, Object> user : userList){
			UserVo userVo = (UserVo) Util.convertToVo(user, UserVo.class);
			userVoList.add(userVo);
		}
		UserListVo userListVo = new UserListVo(userVoList);		
		model.addAttribute("xml", 			userListVo);
		
		model.addAttribute("parameter", 	paramBox.getMap());
		
		return "manager/user/list";
	}
	
	/**
	 * Show an item
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String View Name
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/detail.do", method=RequestMethod.GET)
	public String select(ModelMap model, Box paramBox) throws Exception {

		Map<String, Object> user	= boxUserService.select(paramBox);
		
		model.addAttribute("user", 	user);
		model.addAttribute("json", 	user);
		
		return "manager/user/detail";
	}	
	
	/**
	 * Show form for insert
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String View Name
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/write.do", method=RequestMethod.GET)
	public String write(ModelMap model, Box paramBox) throws Exception {
		
		model.addAttribute("action", 	"write");
		
		return "manager/user/form";
	}
	
	/**
	 * Process page for insert
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String write form view name on error or String redirect list page view name on success 
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/write.do", method=RequestMethod.POST)
	public String writeProcess(ModelMap model, Box paramBox) throws Exception {
		
		Map<String, String> errors = boxUserValidator.validate(paramBox);
		if(errors.isEmpty()){
			int success = boxUserService.insert(paramBox);
			final String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
			for(String role : roles){
				paramBox.put("role", role);
				success		= boxUserService.insertRole(paramBox);
			}
			if(success > 0){
				return "redirect:/manager/user/list.do";
			}
		}
		
		model.addAttribute("user", 				paramBox.getMap());
		model.addAttribute("action", 			"write");
		model.addAttribute("errors", 			errors);
		
		return "manager/user/form";
	}
	
	/**
	 * Show form for edit
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String View Name
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/edit.do", method=RequestMethod.GET)
	public String edit(ModelMap model, Box paramBox) throws Exception {
		
		Map<String, Object> user	= boxUserService.select(paramBox);
		
		model.addAttribute("action", 	"edit");
		model.addAttribute("user", 		user);
		
		return "manager/user/form";
	}
	
	/**
	 * Process page for update
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String write form view name on error or String redirect list page view name on success 
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/edit.do", method=RequestMethod.POST)
	public String editProcess(ModelMap model, Box paramBox) throws Exception {
		
		Map<String, String> errors = boxUserValidator.validate(paramBox);
		if(errors.isEmpty()){
			final int success = boxUserService.update(paramBox);
			if(success > 0){
				return "redirect:/manager/user/list.do";
			}
		}
		
		model.addAttribute("user", 				paramBox.getMap());
		model.addAttribute("action", 			"edit");
		model.addAttribute("errors", 			errors);
		
		return "manager/user/form";
	}	
	
	/**
	 * Process page for item deletion
	 * @param paramBox HTTP Request parameters wrapped in HashMap
	 * @param model Spring ModelMap 
	 * @return String write form view name on error or String redirect list page view name on success 
	 * @exception Exception
	 */
	@RequestMapping(value = "/manager/user/delete.do", method=RequestMethod.POST)
	public String delete(ModelMap model, Box paramBox) throws Exception {
		
		boxUserService.delete(paramBox);
		
		return "redirect:/manager/user/list.do";
	}
	
	
}
