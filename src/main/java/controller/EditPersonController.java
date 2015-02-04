package main.java.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import main.java.dto.Person;
import main.java.dto.PersonVS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import main.java.service.NewPersonSevice;
import main.java.service.PersonService;
import main.java.service.EditService;

@Controller
public class EditPersonController {

	@Resource(name="editService")
	private EditService editService;

	@Resource(name="personInMemorySerive")
	private PersonService personService;

	@Resource(name="newPersonSerivce")
	private NewPersonSevice newPersonSerivce;

	@RequestMapping(value="/mymvc/saveperson.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> savePersonInfo(Person person){
		editService.savePerson(person);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", person);
		return result;
	}

	@RequestMapping(value="/mymvc/queryperson.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> queryPersonInfo(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", editService.getPerson());
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
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", personService.getPerson(Integer.parseInt(id)));
		return result;
	}
	@RequestMapping(value="/mymvc/getPersonindb.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonInfoIndb(String id){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("person", newPersonSerivce.select(id));
		return result;
	}

	@RequestMapping(value="/mymvc/saveOrUpdatePerson.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> saveOrUpdatePersonInfo(Person person){
		if (person.getId() >-1) {
			personService.update(person) ;
		} else {
			personService.save(person);
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}
	@RequestMapping(value="/mymvc/saveOrUpdatePersonindb.do", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,Object> saveOrUpdatePersonInfoIndb(PersonVS person){
		if (person.getId()!=null&&!"-1".equals(person.getId().trim())) {
			newPersonSerivce.update(person);
		} else {
			String newId= java.util.UUID.randomUUID().toString().replaceAll("-", "");
			person.setId(newId);
			newPersonSerivce.save(person);
		}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		return result;
	}

	@RequestMapping(value="/mymvc/getPersons.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonsInfo(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("personlist", personService.getPersons());
		return result;
	}
	@RequestMapping(value="/mymvc/getPersonsindb.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> getPersonsInfoIndb(){
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("personlist", newPersonSerivce.selectAll());
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
