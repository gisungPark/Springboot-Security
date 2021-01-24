package com.spring.web.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	// 구글로부터 받은 userRequest 데이터에 대한 후처리 함수.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// 어떤 oAuth로 로그인했는지 확인가능
		System.out.println("getClientRegistration: " +userRequest.getClientRegistration());
		System.out.println("getAccessToken: " +userRequest.getAccessToken());
		// 구글로그인 버튼 클릭 -> 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Client라이브러리) -> accessToken 요청
		// userRequest 정보 -> loadUser 함수 -> 회원프로필 
		System.out.println("getAttributes: " +super.loadUser(userRequest).getAttributes());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		return super.loadUser(userRequest);
	}
}
