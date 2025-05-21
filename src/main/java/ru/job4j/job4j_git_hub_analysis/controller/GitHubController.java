package ru.job4j.job4j_git_hub_analysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;
import ru.job4j.job4j_git_hub_analysis.model.Repo;
import ru.job4j.job4j_git_hub_analysis.service.commit.CommitService;
import ru.job4j.job4j_git_hub_analysis.service.repo.RepoService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GitHubController {

    @Autowired
    private RepoService repoService;

    @Autowired
    private CommitService commitService;

    @GetMapping("/repositories")
    public List<Repo> getRepositories() {
        return repoService.getRepositories();
    }

    @GetMapping("/commits/{name}")
    public List<CommitDto> getCommitsByAuthor(@PathVariable (value = "name") String name) {
        return commitService.getCommitsByAuthor(name);
    }

    @PostMapping("/repository")
    public ResponseEntity<Void> create(@RequestBody Repo repository) {
        repoService.create(repository);
        return ResponseEntity.noContent().build();
    }

}
