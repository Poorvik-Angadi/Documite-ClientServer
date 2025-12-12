package com.angadi.springoauthclient.controllers;


import com.angadi.springoauthclient.business.services.GetReservationService;
import com.angadi.springoauthclient.models.responses.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;


@Controller
public class ReservationControllerClient{

    public final GetReservationService getReservationService;

    @Autowired
    public ReservationControllerClient(GetReservationService getReservationService){
        this.getReservationService = getReservationService;
    }


    @GetMapping("/reservations")
    public String callApi( @RegisteredOAuth2AuthorizedClient("wso2") OAuth2AuthorizedClient authorizedClient,
                          @AuthenticationPrincipal OidcUser oidcUser, WebSession webSession, Model model) {

        Mono<List<RoomReservation>> roomReservations = getReservationService.getReservations(new Date(),authorizedClient,oidcUser);

        model.addAttribute("roomReservations",roomReservations);
        return "reservations-page";
    }


}