package com.angadi.springoauthclient.business.services;

import com.angadi.springoauthclient.models.requests.RoomReservationRequest;
import com.angadi.springoauthclient.models.responses.RoomReservation;
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

import java.util.Date;
import java.util.List;


@Service
public class GetReservationService {

    private final WebClient webClient;

    @Autowired
    public GetReservationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<RoomReservation>> getReservations(Date date, @RegisteredOAuth2AuthorizedClient("wso2") OAuth2AuthorizedClient authorizedClient,
                                                       @AuthenticationPrincipal OidcUser oidcUser){

        // You can inspect user claims
        String email = oidcUser.getEmail();
        String sub = oidcUser.getSubject();

        // Access token from authorized client
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        RoomReservationRequest roomReservationRequest = new RoomReservationRequest();
        //roomReservationRequest.setDate( OffsetDateTime.now());

        // attach the OAuth2AuthorizedClient so WebClient sends the Authorization header

        return webClient.post()
                .uri("http://localhost:8080/spring-resource-server/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                // attach the OAuth2AuthorizedClient so WebClient sends the Authorization header
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .bodyValue(roomReservationRequest)
                .retrieve()
                .bodyToFlux(RoomReservation.class)
                .collectList();


    }
}
