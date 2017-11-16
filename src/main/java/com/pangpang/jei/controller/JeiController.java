package com.pangpang.jei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.jei.common.Channel;
import com.pangpang.jei.entity.PersonEntity;
import com.pangpang.jei.service.PersonServiceFactory;
import com.pangpang.jei.util.GenericResponseEntity;

import io.swagger.annotations.ApiOperation;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:07:57 
*/

@RestController
public class JeiController {
	
	@ApiOperation("查询人")
	@GetMapping("/person/search")
	public GenericResponseEntity<PersonEntity> searchPerson(@RequestParam Long id, @RequestParam Channel key){
		PersonEntity person = PersonServiceFactory.get(key).searchPerson(id);
		return GenericResponseEntity.ok(person);
		
	}
	
	@ApiOperation("查询男人")
	@GetMapping("/male/search")
	public GenericResponseEntity<PersonEntity> searchMale(@RequestParam Long id, @RequestParam Channel key){
		PersonEntity person = PersonServiceFactory.get(key).searchPerson(id);
		return GenericResponseEntity.ok(person);
		
	}
	
	
}
