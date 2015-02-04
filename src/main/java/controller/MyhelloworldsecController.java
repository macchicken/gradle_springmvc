package main.java.controller;

import java.util.HashMap;
import java.util.Map;

import main.java.dto.MessageStore;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyhelloworldsecController {

	@RequestMapping(value="/mymvc/getMessageInJson.do", method = RequestMethod.GET)  
	@ResponseBody
	public Map<String, Object> getMessageInJson( String name, String message,Integer count){
		MessageStore temp=null;
		if (StringUtils.isNotEmpty(name)&&StringUtils.isNotEmpty(message)){
			temp=new MessageStore(message,name);
		}else{
			temp=new MessageStore();
		}
		if (count!=null) {count+=1;temp.setHelloCount(count);}
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("messageObj", temp);
		return result;
	}
	
	
}
