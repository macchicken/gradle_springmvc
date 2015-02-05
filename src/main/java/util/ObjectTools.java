package main.java.util;

import java.lang.reflect.Field;

public class ObjectTools {

	public static Object MapToObject(Object from,Object to){
		Field[] fields=from.getClass().getDeclaredFields();
		if (fields!=null&&fields.length>0){
			for(Field fie : fields){
	    		fie.setAccessible(true);
	    		Object value = null;
				try {
					value = fie.get(from);
					Field tof=to.getClass().getDeclaredField(fie.getName());
					tof.setAccessible(true);
					tof.set(to, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
				} catch (SecurityException e) {
					e.printStackTrace();
				}
	    	}
		}
		return to;
	}
}
