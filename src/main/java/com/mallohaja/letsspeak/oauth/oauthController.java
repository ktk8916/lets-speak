package com.mallohaja.letsspeak.oauth;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class oauthController {
    @Value("${github.client-id}")
    private String CLIENT_ID;
    @Value("${github.client-secret")
    private String CLIENT_SECRET;

    //쓰레기
    @GetMapping("/login")
    public void login(){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://github.com/login/oauth/authorize")
                .build();
        String res = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("").build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(res);
    }

    @GetMapping("/oauth/github/callback")
    public void getAccessToken(@RequestParam String code){
        WebClient webClient = WebClient.builder()
                .baseUrl("https://github.com/login/oauth/access_token")
                .build();
        String accessToken = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("client_id", CLIENT_ID)
                        .queryParam("client_secret", CLIENT_SECRET)
                        .queryParam("code", code)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .block();


        System.out.println(accessToken);
    }

//    private GitUserInfoDto getUserInfo(String accessToken){
//        WebClient webClient = WebClient.builder()
//                .baseUrl("https://api.github.com/user")
//                .build();
//
//    }

    @AllArgsConstructor
    static class authorizeId{
        String client_id;
    }
}
