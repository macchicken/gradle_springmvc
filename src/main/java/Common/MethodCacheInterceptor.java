package main.java.Common;

import java.io.Serializable;
import java.util.List;

import main.java.dto.AceObject;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class MethodCacheInterceptor implements MethodInterceptor,InitializingBean  {

	private Cache cache;
	
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();
		Object result = null;
		String cacheKey="";
		if (methodName.equals("select")){
			cacheKey=(String) arguments[0];
		}else if (methodName.equals("update")){
			AceObject aceobj=(AceObject) arguments[0];
			cacheKey=aceobj.getId();
		}else{
			cacheKey = getCacheKey(targetName, methodName, arguments);
		}
		Element element = null;
		synchronized (this) {
			element = cache.get(cacheKey);
			if (element == null) {
				result = invocation.proceed();
				element = new Element(cacheKey, (Serializable) result);
				cache.put(element);
				if (result!=null){if (result instanceof List){
					for (AceObject aobj:(List<AceObject>) result){
						Element el=new Element(aobj.getId(),aobj);
						cache.put(el);
					}
				}}
			}else{
				if (methodName.equals("update")){
					result = invocation.proceed();
					element = new Element(cacheKey, (Serializable) result);
					cache.put(element);
				}
			}
		}
		return element.getValue();
	}
	
	private String getCacheKey(String targetName,String methodName,Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cache,"A cache is required. Use setCache(Cache) to provide one.");
	}

}
