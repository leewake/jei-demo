package com.pangpang.jei.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.jei.entity.MaleEntity;
import com.pangpang.jei.service.impl.MaleServiceImpl;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate template;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private MaleServiceImpl maleService;
	
	@PostMapping("/setAndGet")
	public String testSetAndGet() {
		template.opsForValue().set("aaa", "111");
		String result = template.opsForValue().get("aaa");
		return result;
	}

	@PostMapping("/cacheUser")
	public void testObj() throws Exception {
		User user = new User("aa@126.com", "aa", "aa123456");
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.set("com.neox", user);
		operations.set("com.neo.f", user, 10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		boolean exists = redisTemplate.hasKey("com.neo.f");
		if (exists) {
			System.out.println("exists is true");
		} else {
			System.out.println("exists is false");
		}
		User tmp = (User) operations.get("com.neo.f");
		System.out.println(tmp.getName());
	}
	
	@GetMapping("/test/cacheable")
	@Cacheable(value = "male", key = "#id")
	public MaleEntity findMale(@RequestParam Long id) {
		MaleEntity male = (MaleEntity) maleService.searchPerson(id);
		return male; 
	}

	public static class User {
		private String mail;
		private String name;
		private String password;

		public User() {
		}

		public User(String mail, String name, String password) {
			this.mail = mail;
			this.name = name;
			this.password = password;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

}
