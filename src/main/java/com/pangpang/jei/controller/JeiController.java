package com.pangpang.jei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.jei.common.Channel;
import com.pangpang.jei.entity.PersonEntity;
import com.pangpang.jei.service.PersonServiceFactory;
import com.pangpang.jei.service.impl.FemaleServiceImpl;
import com.pangpang.jei.service.impl.MaleServiceImpl;
import com.pangpang.jei.service.impl.PersonSeviceImpl;
import com.pangpang.jei.util.GenericResponseEntity;

import io.swagger.annotations.ApiOperation;

/** 
* @author  : lijingwei
* @version ：2017年11月15日 下午7:07:57 
*/

@RestController
public class JeiController {
	
	/**
	 * 此处应该注入接口，然后使用@Qualifier，不应该直接注入实现类
	 */
	@Autowired
	private FemaleServiceImpl femaleService;
	
	@Autowired
	private MaleServiceImpl maleService;
	
	@Autowired
	private PersonSeviceImpl personSevice;
	
	@ApiOperation("工厂方法查询人")
	@GetMapping("/factory/search")
	public GenericResponseEntity<String> searchPersonFactory(@RequestParam Long id, @RequestParam Channel key){
		PersonEntity person = PersonServiceFactory.get(key).searchPerson(id);
		return GenericResponseEntity.ok(person.toString());
		
	}
	
	@ApiOperation("查询人")
	@GetMapping("/person/search")
	public GenericResponseEntity<String> searchPerson(@RequestParam Long id){
		PersonEntity person = personSevice.searchPerson(id);
		return GenericResponseEntity.ok(person.toString());
		
	}
	
	@ApiOperation("查询男人")
	@GetMapping("/male/search")
	public GenericResponseEntity<String> searchMale(@RequestParam Long id){
		PersonEntity person = maleService.searchPerson(id);
		return GenericResponseEntity.ok(person.toString());
		
	}
	
	@ApiOperation("查询女人")
	@GetMapping("/female/search")
	public GenericResponseEntity<String> searchFemale(@RequestParam Long id){
		PersonEntity person = femaleService.searchPerson(id);
		return GenericResponseEntity.ok(person.toString());
		
	}
	
	
}
