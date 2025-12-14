package com.angadi.springoauthclient.controllers;


import com.angadi.springoauthclient.business.services.GetDocumentsService;
import com.angadi.springoauthclient.business.services.Requests.DocumentsServiceRequest;
import com.angadi.springoauthclient.models.requests.FormData;
import com.angadi.springoauthclient.models.responses.DocumentsFormResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DocumiteControllerClient {

    public final GetDocumentsService getDocumentsService;

    // Simple in-memory "index" for demo purposes
    private static final Map<String,List<String>> INDEXMAP = Map.ofEntries(
            Map.entry("Passport", List.of("Passport"))
            ,Map.entry("I797",List.of("Petition","I797"))
            ,Map.entry("Petition",List.of("Petition","I797"))
            ,Map.entry("H1B", List.of("Visa","H1B"))
            ,Map.entry("Marriage",List.of("Marriage Certificate"))
            ,Map.entry("Degree",List.of("Degree Certificate"))
            ,Map.entry("Certificate",List.of("Degree Certificate","Certificate"))
            ,Map.entry("All", List.of("Get All"))
            ,Map.entry("Paycheck",List.of("Paycheck","Salary","Slips","Payslips"))
            ,Map.entry("Salary",List.of("Paycheck","Salary","Slips","Payslips"))
            ,Map.entry("Slips",List.of("Paycheck","Salary","Slips","Payslips"))
            ,Map.entry("Payslips",List.of("Paycheck","Salary","Slips","Payslips"))
            );


    @Autowired
    public DocumiteControllerClient(GetDocumentsService getDocumentsService){
        this.getDocumentsService = getDocumentsService;

    }

    @GetMapping
    public String getDocumentsLoad( @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient,
                                      @AuthenticationPrincipal OidcUser oidcUser, WebSession webSession, Model model){
        DocumentsServiceRequest request = new DocumentsServiceRequest();
        model.addAttribute("formData",new FormData());
        return "documents-page";
    }

    @PostMapping("/getDocuments")
    public String getDocumentsClient(@ModelAttribute FormData formData, @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient,
                                     @AuthenticationPrincipal OidcUser oidcUser, WebSession webSession, Model model){



        if(formData.getQ() == null)
           formData.setQ("All");
        List<String> key = INDEXMAP.get(formData.getQ());

        DocumentsServiceRequest request = new DocumentsServiceRequest();
        request.setDocName(key);

        Mono<List<DocumentsFormResponse>> documentsList = getDocumentsService.getDocuments(request,authorizedClient,oidcUser);
        model.addAttribute("documentsList",documentsList);
        return "documents-page";
    }



/*    @GetMapping("/search")
    public String search(@RequestParam(name="q", required=false) String q, Model model){
        model.addAttribute("q", q);
        if(q != null && !q.isBlank() && q.length()>2){
            String lower = q.toLowerCase();
            List<String> results = INDEX.entrySet().stream().filter(o -> o.toString().toLowerCase().contains(q) )
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            model.addAttribute("results", results);
        }
        return "search"; // resolves to templates/search.html
    }*/

    // Simple suggestions endpoint returning JSON array of strings
    @GetMapping(value="/suggest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> suggest(@RequestParam("q") String q){
        String lower = q.toLowerCase();
        // For demo return titles that start with q or contain q
        return INDEXMAP.entrySet().stream()
                .map(Map.Entry::getKey)
                .filter( o -> o.toString().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }


}
