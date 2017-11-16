package com.pangpang.jei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangpang.jei.common.Channel;
import com.pangpang.jei.service.impl.MaleServiceImpl;
import com.pangpang.jei.service.impl.PersonSeviceImpl;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:58:52 
*/

@Component
public class PersonServiceFactory {
	
	private static MaleServiceImpl maleSevice;
	
	private static PersonSeviceImpl personSevice;

	@Autowired
	public static void setMaleSevice(MaleServiceImpl maleSevice) {
		PersonServiceFactory.maleSevice = maleSevice;
	}

	@Autowired
	public static void setPersonSevice(PersonSeviceImpl personSevice) {
		PersonServiceFactory.personSevice = personSevice;
	}
	
	public static PersonSevice get(Channel key){
		
		switch (key) {
		case PERSON:
			return personSevice;
			
		case MALE:
			return maleSevice;
			
		default:
			throw new RuntimeException("no valid service!!!");
		}
		
	}

}
