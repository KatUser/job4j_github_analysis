package ru.job4j.job4j_git_hub_analysis.service.githubremote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;
import ru.job4j.job4j_git_hub_analysis.mapper.CommitMapper;
import ru.job4j.job4j_git_hub_analysis.model.GitResponce;
import ru.job4j.job4j_git_hub_analysis.model.Repo;

import java.util.ArrayList;
import java.util.List;

@Service
public class GitHubRemote {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CommitMapper commitMapper;

    public List<Repo> fetchRepositories(String username) {
        var url = "https://api.github.com/users/" + username + "/repos";
        ResponseEntity<List<Repo>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Repo>>() {
                });
        return response.getBody();
    }


    public List<CommitDto> fetchCommits(String owner, String repoName) {
        var url = "https://api.github.com/repos/" + owner + "/" + repoName + "/commits";
        ResponseEntity<List<GitResponce>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GitResponce>>() {
                });
        List<CommitDto> commitsDto = new ArrayList<>();
        for (GitResponce responce : response.getBody()) {
            var commit  = responce.getCommit();
            commitsDto.add(commitMapper.toCommitDto(commit));
        }
        System.out.println(commitsDto);
        return commitsDto;
    }

    public List<CommitDto> fetchCommits(String owner, String repoName, String sha) {
        var url = String.format("https://api.github.com/repos/%s/%s/commits?sha=%s", owner, repoName, sha);
        ResponseEntity<List<CommitDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });

                return response.getBody();
    }

    public List<CommitDto> fetchCommits(String repoUrl) {
        var url = repoUrl + "/commits";
        ResponseEntity<List<CommitDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}

