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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gframework.common.data.JsonResponse;
import gframework.common.request.Box;
import gframework.user.data.UserVo;
import gframework.user.service.BoxUserService;
import gframework.user.service.UserService;

/**
 * @Class User Controller.java
 * @Description User App Controller Class
 * @author ghy
 * @since 2016-12-06
 */

@Controller
public class RestUserController {
	
	@Resource(name = "boxUserService")
	private BoxUserService boxUserService;

	@Resource(name = "userService")
	private UserService userService;


	@RequestMapping(value = "/user/{seq}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> get(ModelMap model, Box paramBox, @PathVariable String seq) throws Exception {
		
		paramBox.put("seq", seq);
		return boxUserService.select(paramBox);
	}

	@RequestMapping(value = "/rest/user/list.do", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> list(ModelMap model, UserVo userVo) throws Exception {
		
		userVo.setPageSize(10);
		userVo.setStartRow(0);
		return userService.list(userVo);
	}

	@RequestMapping(value = "/rest/user/detail.do", method=RequestMethod.GET)
	public @ResponseBody Map<String, String> boxSelect(ModelMap model, UserVo userVo) throws Exception {
		
		return userService.select(userVo);
	}	
	

	@RequestMapping(value = "/rest/user/box/list.do", method=RequestMethod.GET)
	public @ResponseBody JsonResponse boxList(ModelMap model, Box paramBox) throws Exception {
		
		paramBox.put("namespace", "User");
		List<Map<String, Object>> resultList = boxUserService.list(paramBox);
		
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		JsonResponse response = new JsonResponse(paramBox, resultList, errors);
		
		return response;
		
	}
	
	@RequestMapping(value = "/rest/user/box/detail.do", method=RequestMethod.GET)
	public @ResponseBody JsonResponse select(ModelMap model, Box paramBox) throws Exception {
		
		paramBox.put("namespace", "User");
		
		Map<String, Object> result = boxUserService.select(paramBox);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.add(result);
		
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		JsonResponse response = new JsonResponse(paramBox, resultList, errors);
		
		return response;
	}	
	
}
