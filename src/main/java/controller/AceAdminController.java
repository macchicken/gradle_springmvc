package main.java.controller;

import javax.annotation.Resource;

import main.java.service.NewPersonSevice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AceAdminController{

	@Resource(name="newPersonSerivce")
	private NewPersonSevice newPersonSerivce;

	@RequestMapping(value = "/mymvc/aceAdmin.do")
	public String AceAdmin(){
		return "aceAdmin";
	}
	@RequestMapping(value = "/mymvc/myhelloworld.do")
	public String MyHelloWorld(){
		return "helloWorld";
	}
	@RequestMapping(value = "/mymvc/myhelloworldsec.do")
	public String MyHelloWorldSec(){
		return "helloWorldSec";
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

	/*@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aceAdmin");
		return mv;
	}*/


}
