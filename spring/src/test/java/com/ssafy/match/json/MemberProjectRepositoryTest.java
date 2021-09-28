package com.ssafy.match.json;

import com.ssafy.match.apiclient.dto.UserGitRepository;
import com.ssafy.match.apiclient.service.GithubApiServiceImpl;
import com.ssafy.match.apiclient.service.JsonParseServiceImpl;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.group.repository.MemberProjectRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberProjectRepositoryTest {
    @Autowired
    GithubApiServiceImpl githubClient;
    @Autowired
    JsonParseServiceImpl parser;

    @Test
    public void testJsonParse(){
//        try{
//            String str= githubClient.callAPI("sbsk1997");
//            System.out.println(str);
//            UserGitRepository[] l = parser.getRepoList(str);
//
//            System.out.println(l);
//            for(UserGitRepository r : l){
//                System.out.println(r);
//            }
//        }
//        catch(Exception e){
//
//        }
    }
}