package com.pangpang.jei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangpang.jei.entity.PersonEntity;
import com.pangpang.jei.repository.FemaleRepository;
import com.pangpang.jei.service.PersonSevice;

/** 
* @author  : lijingwei
* @version ：2017年11月16日 上午11:19:59 
*/

@Service("femaleService")
public class FemaleServiceImpl implements PersonSevice{
	
	@Autowired
	private FemaleRepository femaleRepository;

	@Override
	public PersonEntity searchPerson(Long id) {
		PersonEntity female = femaleRepository.findOne(id);
		return female;
	}

}