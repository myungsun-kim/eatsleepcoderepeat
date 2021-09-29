package com.ssafy.match.apiclient.controller;

import com.ssafy.match.apiclient.dto.UserGitRepository;
import com.ssafy.match.apiclient.dto.UserGitStatus;
import com.ssafy.match.apiclient.service.ClocServiceImpl;
import com.ssafy.match.apiclient.service.GithubApiServiceImpl;
import com.ssafy.match.apiclient.service.JsonParseServiceImpl;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ext")
@AllArgsConstructor
public class ApiClientController {
    private GithubApiServiceImpl githubClient;
    private JsonParseServiceImpl parser;
    private ClocServiceImpl cloc;
    @GetMapping("/github/{githubId}")
    public ResponseEntity<?> test(@PathVariable String githubId){
        Map<String, UserGitStatus> ret = null;
        try{
            String str= githubClient.callAPI(githubId);
            str = str.substring(1, str.length()-1);
            str = str.replace("\\", "");
            //... ha.........
//            System.out.println(str);
            List<UserGitRepository> l = parser.getRepoList(str);
            for(UserGitRepository repo : l){
                ret = cloc.getGitStatus(repo.getFull_name());
            }
        }
        catch(Exception e){

        }
        return new ResponseEntity<Map<String, UserGitStatus>>(ret, HttpStatus.OK);
    }

}
