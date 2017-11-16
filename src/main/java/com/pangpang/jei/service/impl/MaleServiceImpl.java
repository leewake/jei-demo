package com.pangpang.jei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangpang.jei.entity.PersonEntity;
import com.pangpang.jei.repository.MaleRepository;
import com.pangpang.jei.service.PersonSevice;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:59:36 
*/

@Service("maleService")
public class MaleServiceImpl implements PersonSevice{
	
	@Autowired
	private MaleRepository maleRepository;

	@Override
	public PersonEntity searchPerson(Long id) {
		PersonEntity male = maleRepository.findOne(id);
		return male;
	}

}
