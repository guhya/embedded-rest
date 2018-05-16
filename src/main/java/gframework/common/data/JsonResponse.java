package gframework.common.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gframework.common.request.Box;

public class JsonResponse {
	
	private Map<String, String> meta;
	private List<Map<String, Object>> data;
	private List<Map<String, Object>> errors;

	public JsonResponse(Map<String, String> meta,	
			List<Map<String, Object>> data,
			List<Map<String, Object>> errors){
		
		this.meta = meta;
		this.data = data;
		this.errors = errors;
	}
	
	public JsonResponse(Box paramBox,	
			List<Map<String, Object>> data,
			List<Map<String, Object>> errors){
		
		Map<String, String> meta = new LinkedHashMap<String, String>();
		meta.put("pageSize", 	paramBox.getString("pageSize"));
		meta.put("startRow", 	paramBox.getString("startRow"));
		meta.put("page", 		paramBox.getString("page"));
		meta.put("condition", 	paramBox.getString("condition"));
		meta.put("keyword", 	paramBox.getString("keyword"));
		meta.put("sortColumn", 	paramBox.getString("sortColumn"));
		meta.put("sortOrder", 	paramBox.getString("sortOrder"));
		this.meta = meta;
		
		this.data = data;
		this.errors = errors;
	}

	public Map<String, String> getMeta() {
		return meta;
	}
	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public List<Map<String, Object>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, Object>> errors) {
		this.errors = errors;
	}
	
}
