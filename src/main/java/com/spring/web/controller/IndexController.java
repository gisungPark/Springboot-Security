package com.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.web.model.User;
import com.spring.web.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@GetMapping("/")
	public String index() {
		//머스테치 기본폴더 src/main/resources/
		//뷰리졸버 설정: template(prefix), .mustache(suffix)
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// 스프링시큐리티가 해당주소를 낚아챔 - SecurityConfig 파일 생성 후 비활성화
	@GetMapping("/loginForm")
	public String login() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPasswrod = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPasswrod);
		userRepository.save(user);	// 회원가입 잘됨. 비밀번호 1234
									// but, 시큐리티로 로그인을 할 수 없음.
									// 이유는 패스워드가 암호화가 안되었기 때문!!
		return "redirect:/loginForm";
	}
	
}
