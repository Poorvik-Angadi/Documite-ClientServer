package com.angadi.springoauthclient.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {


    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
                        ReactiveOAuth2AuthorizedClientService clientService) {

        var oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                        clientRegistrations, clientService));

        oauth.setDefaultClientRegistrationId("google"); // uses your config



        return WebClient.builder()
                .baseUrl("http://localhost:8082/")
                .filter(oauth)
                .filter((request, next) -> {
                    System.out.println(">>> Request Headers:");
                    request.headers().forEach((k, v) -> System.out.println(k + " = " + v));
                    return next.exchange(request);
                })
                .build();
    }

}