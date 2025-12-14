package com.angadi.springoauthclient.business.services;


import com.angadi.springoauthclient.business.services.Requests.DocumentsServiceRequest;
import com.angadi.springoauthclient.business.services.Responses.DocumentsServiceResponse;
import com.angadi.springoauthclient.models.responses.DocumentsFormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


public class SuggestionsService {
/*
    private final WebClient webClient;

    @Autowired
    public SuggestionsService(WebClient webClient){
        this.webClient = webClient;
    }


    public Mono<List<String>> getSuggestions(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient,
                                                          @AuthenticationPrincipal OidcUser oidcUser ){
        return webClient.post()
                .uri("http://localhost:8080/spring-resource-server/getSuggestions")
                .contentType(MediaType.APPLICATION_JSON)
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToFlux(String.class)
                .collectList();

    }*/
}
