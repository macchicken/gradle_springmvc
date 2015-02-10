package main.java.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.java.dto.PersonVS;
import main.java.model.PageModel;
import main.java.model.PersonModelVS;
import main.java.service.IPersonServie;
import main.java.util.ObjectTools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AceDatatableController {

	@Resource(name="newPersonSerivce")
	private IPersonServie newPersonSerivce;

	@Resource(name="newPersonCacheService")
	private IPersonServie newPersonCacheService; 

	@RequestMapping(value="/mymvcdatatable/modifyPersonInCache.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> modifyPersonInCache(PersonModelVS model){
		PersonVS data=(PersonVS) ObjectTools.MapToObject(model, new PersonVS());
		newPersonCacheService.update(data);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", model);
		return result;
	}

	@RequestMapping(value="/mymvcdatatable/getPersonfordt.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonInfoFordt(String id){
		PersonVS data=newPersonSerivce.select(id);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", ObjectTools.MapToObject(data, new PersonModelVS()));
		return result;
	}
	@RequestMapping(value="/mymvcdatatable/getPersonByCache.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> getPersonByCache(String id){
		PersonVS data=newPersonCacheService.select(id);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", ObjectTools.MapToObject(data, new PersonModelVS()));
		return result;
	}

	@RequestMapping(value="/mymvcdatatable/getPersonsfordt.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonsInfoFordt(){
		List<PersonModelVS> personList = new ArrayList<PersonModelVS>();
		List<PersonVS> temp=newPersonSerivce.selectAll();
		for (PersonVS p : temp){
			personList.add((PersonModelVS) ObjectTools.MapToObject(p, new PersonModelVS()));
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("personlist", personList);
		return result;
	}
	
	@RequestMapping(value="/mymvcdatatable/getPersonsByPage.do", method = RequestMethod.POST)  
	@ResponseBody
	public String getPersonsfordtByPage(String sEcho,String totalRecord,String iDisplayStart,String iDisplayLength){
		Map<String,Integer> params=new HashMap<String,Integer>();
		int pageindex=Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		PageModel pm=PageModel.newPageModel(Integer.parseInt(iDisplayLength), pageindex, Integer.parseInt(totalRecord));
		params.put("offset", pm.getOffset());
		params.put("pageSize", Integer.parseInt(iDisplayLength));
		List<PersonVS> temp=newPersonSerivce.selectBySize(params);
		String result=resultToJson(sEcho,totalRecord,temp);
		return result;
	}

	@RequestMapping(value="/mymvcdatatable/getPersonsByCache.do", method = RequestMethod.POST)  
	@ResponseBody
	public String getPersonsfordtByCache(String sEcho,String totalRecord,String iDisplayStart,String iDisplayLength){
		Map<String,Integer> params=new HashMap<String,Integer>();
		int pageindex=Integer.parseInt(iDisplayStart)/Integer.parseInt(iDisplayLength)+1;
		PageModel pm=PageModel.newPageModel(Integer.parseInt(iDisplayLength), pageindex, Integer.parseInt(totalRecord));
		params.put("offset", pm.getOffset());
		params.put("pageSize", Integer.parseInt(iDisplayLength));
		List<PersonVS> temp=newPersonCacheService.selectBySize(params);
		String result=resultToJson(sEcho,totalRecord,temp);
		return result;
	}
	
	private String resultToJson(String sEcho, String totalRow, List<PersonVS> result){
		StringBuilder json = new StringBuilder();
		json.append("{\"sEcho\":" + sEcho + ",");
		json.append("\"iTotalRecords\":" + totalRow + ",");
		json.append("\"iTotalDisplayRecords\":" + totalRow + ",");
		if (result!=null&&result.size()>0){
			json.append("\"aaData\":[");
			for (PersonVS p: result){
				Field[] fields=p.getClass().getDeclaredFields();
				StringBuilder temp = new StringBuilder();
				temp.append("{");
				for(Field fie : fields){
					try {
						fie.setAccessible(true);
						Object value = null;
						temp.append("\"");temp.append(fie.getName());temp.append("\":\"");
						value = fie.get(p);
						value = value == null ? "" : value.toString();
						value=value.toString().replace("\"","");
						temp.append(value); temp.append("\",");
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				temp.delete(temp.length()-1, temp.length());
				temp.append("},");
				json.append(temp);
			}
			json.delete(json.length()-1, json.length());
            json.append("]}");
		}else{
			json.append("\"aaData\":[]}");
		}
		return json.toString();
	}
}
