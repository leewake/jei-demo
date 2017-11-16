package com.pangpang.jei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangpang.jei.common.Channel;
import com.pangpang.jei.service.impl.FemaleServiceImpl;
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
	
	private static FemaleServiceImpl femaleService;

	/**
	 * 之前一直写成static方法，导致service注入不进去
	 * 
	 * @param maleSevice
	 */
	@Autowired
	public void setMaleSevice(MaleServiceImpl maleSevice) {
		PersonServiceFactory.maleSevice = maleSevice;
	}

	@Autowired
	public void setPersonSevice(PersonSeviceImpl personSevice) {
		PersonServiceFactory.personSevice = personSevice;
	}
	
	@Autowired
	public void setFemaleService(FemaleServiceImpl femaleService) {
		PersonServiceFactory.femaleService = femaleService;
	}
	
	public static PersonSevice get(Channel key){
		
		switch (key) {
		case PERSON:
			return personSevice;
			
		case MALE:
			return maleSevice;
			
		case FEMALE:
			return femaleService;
			
		default:
			throw new RuntimeException("no valid service!!!");
		}
		
	}

}
