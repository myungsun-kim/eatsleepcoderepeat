package com.ssafy.match.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGitRepository {
    long id;
    String node_id;
    String name;
    String full_name;

    long forks_count;
    long stargazers_count;
    long watchers_count;
    String description;
    Map<String, Long> lines_of_code;
}
