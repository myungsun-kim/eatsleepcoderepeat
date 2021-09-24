package com.ssafy.match.apiclient.controller;

import com.ssafy.match.apiclient.service.GithubApiServiceImpl;
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

    @GetMapping("/abc")
    public String test(){
        System.out.println("asdf");
        try{

            githubClient.callAPI();
        }
        catch(Exception e){

        }
        return "!";
    }

}
