package com.pangpang.jei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangpang.jei.entity.PersonEntity;
import com.pangpang.jei.repository.PersonRepository;
import com.pangpang.jei.service.PersonSevice;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:06:40 
*/

@Service
public class PersonSeviceImpl implements PersonSevice{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public PersonEntity searchPerson(Long id) {
		PersonEntity person = personRepository.findOne(id);
		return person;
	}

}
