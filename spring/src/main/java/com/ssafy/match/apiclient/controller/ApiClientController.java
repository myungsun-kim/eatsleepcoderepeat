package com.ssafy.match.apiclient.controller;

import com.ssafy.match.apiclient.dto.UserGitRepository;
import com.ssafy.match.apiclient.service.GithubApiServiceImpl;
import com.ssafy.match.apiclient.service.JsonParseServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@AllArgsConstructor
public class ApiClientController {
    private GithubApiServiceImpl githubClient;
    private JsonParseServiceImpl parser;
    @GetMapping("/abc")
    public String test(){
        System.out.println("asdf");
        try{
            String str= githubClient.callAPI("sbsk1997");
            System.out.println(str);
            List<UserGitRepository> l = parser.getRepoList(str);

            System.out.println(l);
            for(UserGitRepository r : l){
                System.out.println(r);
            }
        }
        catch(Exception e){

        }
        return "!";
    }

}
