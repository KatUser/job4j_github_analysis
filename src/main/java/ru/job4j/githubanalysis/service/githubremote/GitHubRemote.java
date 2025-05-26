package ru.job4j.githubanalysis.service.githubremote;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.mapper.CommitMapper;
import ru.job4j.githubanalysis.model.GitResponce;
import ru.job4j.githubanalysis.model.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Component
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
        for (GitResponce responce : Objects.requireNonNull(response.getBody())) {
            var commit  = responce.getCommit();
            commitsDto.add(commitMapper.toCommitDto(commit));
        }
        System.out.println(commitsDto);
        return commitsDto;
    }
}

