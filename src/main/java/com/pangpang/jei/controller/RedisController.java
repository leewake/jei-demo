package com.pangpang.jei.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
	
	@Autowired
	private StringRedisTemplate template;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@PostMapping("/redis/setAndGet")
	public String testSetAndGet() {
		template.opsForValue().set("aaa", "111");
		String result = template.opsForValue().get("aaa");
		return result;
	}
	
	@PostMapping("/redis/cacheUser")
	public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user, 100, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists = redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
       System.out.println(operations.get("com.neo.f").getName());
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
