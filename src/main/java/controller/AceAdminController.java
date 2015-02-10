package main.java.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import main.java.Common.AceAppPropertyconfig;
import main.java.model.User;
import main.java.service.IPersonServie;
import main.java.util.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes(Constants.ACEUSER)
public class AceAdminController{

	@Resource(name="newPersonSerivce")
	private IPersonServie newPersonSerivce;

	@Resource(name="newPersonCacheService")
	private IPersonServie newPersonCacheService; 

	@Resource(name="aceAppPropertyconfig")
	private AceAppPropertyconfig aceAppPropertyconfig;


	@RequestMapping(value = "/mymvc/aceAdmin.do")
	public ModelAndView AceAdmin(ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		if (model.get(Constants.ACEUSER)==null){
			User my=new User("Barry",Calendar.getInstance().getTime());
			model.addAttribute(Constants.ACEUSER, my);
		}
		modelAndView.setViewName("aceAdmin");
		modelAndView.addObject("myemail", aceAppPropertyconfig.getPropertyValue("myemail"));
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/myhelloworld.do")
	public ModelAndView MyHelloWorld(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helloWorld");
		HashMap<String,Object> properties=aceAppPropertyconfig.getAceproperties();
		modelAndView.addObject("hellomessage", properties.get("HelloWorld-message-en"));
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/myhelloworldsec.do")
	public ModelAndView MyHelloWorldSec(ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helloWorldSec");
		modelAndView.addObject(Constants.ACEUSER, model.get(Constants.ACEUSER));
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/editpersonview.do")
	public String MyEditperson(){
		return "editperson";
	}
	@RequestMapping(value = "/mymvc/createperson.do")
	public ModelAndView  MyCreateperson(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("createperson");
		modelAndView.addObject("personid", -1);
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/acedatatable.do")
	public ModelAndView  MyAcedatatable(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("aceDatatable");
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/acedatatableByPage.do")
	public ModelAndView  MyAcedatatableByPage(){
		ModelAndView modelAndView = new ModelAndView();
		Integer count= newPersonSerivce.selectCountAll();
		modelAndView.setViewName("aceDatatableByPage");
		modelAndView.addObject("totalperson", count.toString());
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/acedatatableByCache.do")
	public ModelAndView  MyAcedatatableByCache(){
		ModelAndView modelAndView = new ModelAndView();
		Integer count= newPersonCacheService.selectCountAll();
		modelAndView.setViewName("acedatatableByCache");
		modelAndView.addObject("totalperson", count.toString());
		return modelAndView;
	}
	@RequestMapping(value = "/mymvc/changeDisplayWords.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  MyAcechangeDisplayWords(String lanCode){
		String hellomessage=(String) aceAppPropertyconfig.getPropertyValue("HelloWorld-message-"+lanCode);
		String yes=(String) aceAppPropertyconfig.getPropertyValue("yes-"+lanCode);
		String no=(String) aceAppPropertyconfig.getPropertyValue("no-"+lanCode);
		String notsure=(String) aceAppPropertyconfig.getPropertyValue("notsure-"+lanCode);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("hellomessage", hellomessage);
		result.put("yes", yes);
		result.put("no", no);
		result.put("notsure", notsure);
		return result;
	}

	/*@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aceAdmin");
		return mv;
	}*/


}
