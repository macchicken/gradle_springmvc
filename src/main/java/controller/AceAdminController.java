package main.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AceAdminController{

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

	/*@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aceAdmin");
		return mv;
	}*/


}
