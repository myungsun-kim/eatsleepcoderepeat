package com.ssafy.match.apiclient.service;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.match.apiclient.dto.UserGitRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class JsonParseServiceImpl {
    public static List<UserGitRepository> getRepoList(String json) {
        List<UserGitRepository> repos = null;
//        List<UserGitRepository> ret = null;
        try {
            JSONParser parser = new JSONParser();
            JSONArray all = (JSONArray) parser.parse(json);
            repos = new ArrayList<>();
            for(int i = 0; i < all.size(); i++){
                JSONObject obj = (JSONObject) all.get(i);
                UserGitRepository repo = new UserGitRepository();
                repo.setId((Long)obj.get("id"));
                repo.setForks_count((Long) obj.get("forks_count"));
                repo.setFull_name((String) obj.get("full_name"));
                repo.setDescription((String) obj.get("description"));
                repo.setStargazers_count((Long) obj.get("stargazers_count"));
                repo.setWatchers_count((Long) obj.get("watchers_count"));
                repos.add(repo);
            }
//            UserGitRepository repo = new UserGitRepository();
//            repo.setId((Long)repos.get("id"));
//            repo.setForks_count((Long) repos.get("forks_count"));
//            repo.setFull_name((String) repos.get("full_name"));
//            repo.setDescription((String) repos.get("description"));
//            repo.setStargazers_count((Long) repos.get("stargazers_count"));
//            repo.setWatchers_count((Long) repos.get("watchers_count"));
//            for(UserGitRepository repo : repos){
//                System.out.println(repo);
//            }
//            System.out.println(repos);
//            System.out.println("--------------------------");
//
//            return repos;
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
////            return repos;
//        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e){

        }
        return repos;

    }

}
