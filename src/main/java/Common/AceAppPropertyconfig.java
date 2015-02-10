package main.java.Common;

import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AceAppPropertyconfig extends PropertyPlaceholderConfigurer {

	private HashMap<String,Object> aceproperties=new HashMap<String,Object>();


	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		aceproperties = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            aceproperties.put(keyStr, value);  
        }  
	}
	
	public HashMap<String, Object> getAceproperties() {
		return aceproperties;
	}
	
	public Object getPropertyValue(String key){
		if (aceproperties.isEmpty()){return null;}
		return aceproperties.get(key);
	}
	
}
