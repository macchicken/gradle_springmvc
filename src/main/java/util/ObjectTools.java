package main.java.util;

import java.lang.reflect.Field;

public class ObjectTools {

	public static Object MapToObject(Object from,Object to){
		Field[] fields=from.getClass().getDeclaredFields();
		to=MapToObjectInternal(fields,from,to,to.getClass());
		return to;
	}
	
	public static Object MapToModel(Object data,Object model){
		Field[] fields=data.getClass().getDeclaredFields();
		model=MapToObjectInternal(fields,data,model,model.getClass());
		Field[] fieldss=data.getClass().getSuperclass().getDeclaredFields();
		model=MapToObjectInternal(fieldss,data,model,model.getClass());
		return model;
	}

	private static Object MapToObjectInternal(Field[] fields,Object from,Object to,Class<?> toclass){
		if (fields!=null&&fields.length>0){
			for(Field fie : fields){
	    		fie.setAccessible(true);
	    		Object value = null;
				try {
					value = fie.get(from);
					if (value instanceof String){value=((String) value).replaceAll("\"", "");}
					Field tof=null;
					try {
						tof=toclass.getDeclaredField(fie.getName());
					} catch (NoSuchFieldException e) {
						try {tof=toclass.getSuperclass().getDeclaredField(fie.getName());
						} catch (NoSuchFieldException e1) {tof=null;}}
					if (tof!=null) {
						tof.setAccessible(true);
						tof.set(to, value);
					}
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (SecurityException e) {
				}
	    	}
		}
		return to;
	}
	
}
