package main.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.java.dto.PersonVS;
import main.java.model.PersonModelVS;
import main.java.service.NewPersonSevice;
import main.java.util.ObjectTools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AceDatatableController {

	@Resource(name="newPersonSerivce")
	private NewPersonSevice newPersonSerivce;


	@RequestMapping(value="/mymvcdatatable/getPersonfordt.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonInfoFordt(String id){
		PersonVS data=newPersonSerivce.select(id);
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
}
