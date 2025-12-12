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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GetDocumentsService {

    private final WebClient webClient;

    @Autowired
    public GetDocumentsService(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<List<DocumentsFormResponse>> getDocuments(DocumentsServiceRequest documentsServiceRequest, @RegisteredOAuth2AuthorizedClient("wso2") OAuth2AuthorizedClient authorizedClient,
                                                             @AuthenticationPrincipal OidcUser oidcUser ){

        return webClient.post()
                .uri("http://localhost:8080/spring-resource-server/getdocuments")
                .contentType(MediaType.APPLICATION_JSON)
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .bodyValue(documentsServiceRequest)
                .retrieve()
                .bodyToFlux(DocumentsServiceResponse.class)
                .map(this::documentsListMapper)
                .collectList();

    }


    public DocumentsFormResponse documentsListMapper(DocumentsServiceResponse documentsServiceResponse){
        DocumentsFormResponse documentsFormResponse = new DocumentsFormResponse();

                    documentsFormResponse.setDocName(documentsServiceResponse.getDocName());
                    documentsFormResponse.setDocType(documentsServiceResponse.getDocType());
                    documentsFormResponse.setDocYear(documentsServiceResponse.getDocYear());
                    if( documentsServiceResponse.getDocValidity()!=null && documentsServiceResponse.getDocValidity().length ==2) {
                        String s = documentsServiceResponse.getDocValidity()[0].toString().concat(" - ").concat( documentsServiceResponse.getDocValidity()[1].toString());
                        documentsFormResponse.setDocValidity(s);
                    }


        return documentsFormResponse;

    }

}
