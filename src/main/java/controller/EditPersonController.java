package main.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.java.dto.Person;
import main.java.dto.PersonVS;
import main.java.model.PersonModel;
import main.java.model.PersonModelVS;
import main.java.service.EditService;
import main.java.service.IPersonServie;
import main.java.service.PersonService;
import main.java.util.ObjectTools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditPersonController {

	@Resource(name="editService")
	private EditService editService;

	@Resource(name="personInMemorySerive")
	private PersonService personService;

	@Resource(name="newPersonSerivce")
	private IPersonServie newPersonSerivce;


	@RequestMapping(value="/mymvc/saveperson.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> savePersonInfo(PersonModel person){
		Person data=(Person) ObjectTools.MapToObject(person, new Person());
		editService.savePerson(data);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", person);
		return result;
	}

	@RequestMapping(value="/mymvc/queryperson.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> queryPersonInfo(){
		Person data=editService.getPerson();
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", ObjectTools.MapToObject(data, new PersonModel()));
		return result;
	}

	@RequestMapping(value="/mymvc/editperson.do")  
	public ModelAndView editPersonInfo(String id){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createperson");
		modelAndView.addObject("personid",id);
		return modelAndView;
	}
	@RequestMapping(value="/mymvc/editpersonindb.do")  
	public ModelAndView editPersonInfoIndb(String id){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createpersondb");
		modelAndView.addObject("personid",id);
		return modelAndView;
	}
	@RequestMapping(value="/mymvc/deleteperson.do")  
	public ModelAndView deletePersonInfo(String id){
		personService.deletePerson(Integer.parseInt(id));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewperson");
		return modelAndView;
	}
	@RequestMapping(value="/mymvc/deletepersonindb.do")  
	public ModelAndView deletePersonInfoIndb(String id){
		newPersonSerivce.delete(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewpersondb");
		return modelAndView;
	}

	@RequestMapping(value="/mymvc/getPerson.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonInfo(String id){
		Person data=personService.getPerson(Integer.parseInt(id));
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", ObjectTools.MapToObject(data, new PersonModel()));
		return result;
	}
	@RequestMapping(value="/mymvc/getPersonindb.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonInfoIndb(String id){
		PersonVS data=newPersonSerivce.select(id);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", ObjectTools.MapToObject(data, new PersonModelVS()));
		return result;
	}

	@RequestMapping(value="/mymvc/saveOrUpdatePerson.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> saveOrUpdatePersonInfo(PersonModel person){
		Person data=(Person) ObjectTools.MapToObject(person, new Person());
		if (person.getId() >-1) {
			personService.update(data) ;
		} else {
			personService.save(data);
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}
	@RequestMapping(value="/mymvc/saveOrUpdatePersonindb.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> saveOrUpdatePersonInfoIndb(PersonModelVS person){
		PersonVS data=(PersonVS) ObjectTools.MapToObject(person, new PersonVS());
		if (data.getId()!=null&&!"-1".equals(data.getId().trim())) {
			newPersonSerivce.update(data);
		} else {
			String newId= java.util.UUID.randomUUID().toString().replaceAll("-", "");
			data.setId(newId);
			newPersonSerivce.save(data);
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}

	@RequestMapping(value="/mymvc/getPersons.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonsInfo(){
		List<PersonModel> personList = new ArrayList<PersonModel>();
		List<Person> temp=personService.getPersons();
		for (Person p : temp){
			personList.add((PersonModel) ObjectTools.MapToObject(p, new PersonModel()));
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("personlist", personList);
		return result;
	}
	@RequestMapping(value="/mymvc/getPersonsindb.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonsInfoIndb(){
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

	@RequestMapping(value="/mymvc/viewperson.do")  
	public String Viewpeopele(){
		return "viewperson";
	}
	@RequestMapping(value="/mymvc/viewpersonindb.do")  
	public String ViewpeopeleIndb(){
		return "viewpersondb";
	}
	@RequestMapping(value = "/mymvc/createpersonindb.do")
	public ModelAndView  MyCreatepersonIndb(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createpersondb");
		modelAndView.addObject("personid", -1);
		return modelAndView;
	}
}
