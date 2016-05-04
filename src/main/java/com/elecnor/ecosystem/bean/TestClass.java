package com.elecnor.ecosystem.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class TestClass {
	
	public static void main(String args[]){
		
		VendorDirectory obj = new VendorDirectory();
		fun(obj);
        
	}
	
	public static void fun(Object obj){
		
		System.out.println(obj.getClass());
		Class<? extends Object> abc = obj.getClass();
		
		BeanInfo info = null;
		try {
			info = Introspector.getBeanInfo(abc);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Calling setters");
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            if (pd.getWriteMethod() == null) continue;
            System.out.println("\tSetting " + pd.getName());
            //pd.getWriteMethod().invoke(sb, "Set now");
        }
        System.out.println("Reading the getters");
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println("\t" + pd.getName() + " = " + pd.getReadMethod());
        }
	}

}
