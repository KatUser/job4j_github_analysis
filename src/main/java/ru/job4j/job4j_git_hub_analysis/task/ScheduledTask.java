package ru.job4j.job4j_git_hub_analysis.task;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;

import ru.job4j.job4j_git_hub_analysis.model.Repo;
import ru.job4j.job4j_git_hub_analysis.service.commit.CommitService;
import ru.job4j.job4j_git_hub_analysis.service.githubremote.GitHubRemote;
import ru.job4j.job4j_git_hub_analysis.service.repo.RepoService;

import java.util.List;

@AllArgsConstructor
@Component
public class ScheduledTask {

    @Autowired
    private final RepoService repositoryService;

    @Autowired
    private final CommitService commitService;

    @Autowired
    private final GitHubRemote gitHubRemote;

    private final String username = "KatUser";

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void fetchRepoCommits() {
        System.out.println("Fetching repos list");
        List<Repo> repos = gitHubRemote.fetchRepositories(username);

        for (Repo repo : repos) {
            if (!repositoryService.getRepositories().contains(repo)) {
                repositoryService.create(new Repo(
                        repo.getId(),
                        repo.getName(),
                        repo.getUrl()
                ));
            }
            var commitDtos
                    = gitHubRemote.fetchCommits(username, repo.getName());

            for (CommitDto commitDto : commitDtos) {
                if (!commitService.getCommits().contains(commitDto)) {
                    commitService.create(new CommitDto(
                            commitDto.getId(),
                            commitDto.getMessage(),
                            commitDto.getAuthor(),
                            commitDto.getDate(),
                            repositoryService.getRepositoryByName(repo.getName())));
                }
            }

        }

    }
}
