package com.angadi.springoauthclient.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CommonUtility {

    @Autowired
    private OAuth2AuthorizedClientService clientService;

    public static String getIdToken(OidcUser oidcUser) {

            return oidcUser.getIdToken().getTokenValue();
    }

}
