package ru.job4j.githubanalysis.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.job4j.githubanalysis.dto.CommitDto;

import ru.job4j.githubanalysis.model.Repo;
import ru.job4j.githubanalysis.service.commit.CommitSaver;
import ru.job4j.githubanalysis.service.githubremote.GitHubRemote;
import ru.job4j.githubanalysis.service.repo.RepoSaver;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ScheduledTask {

    @Autowired
    private final GitHubRemote gitHubRemote;

    @Autowired
    private RepoSaver repoSaver;

    @Autowired
    private final CommitSaver commitSaver;

    @Value("${repousername}")
    private String repoUserName;

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void scheduledTaskRun() {
        System.out.println(repoUserName);
        System.out.println("starting fetching remote repositories");
        List<Repo> remoteRepos = gitHubRemote.fetchRepositories(repoUserName);

        System.out.println("starting saving remote repositories into database");
        repoSaver.saveRepoCommits(remoteRepos);

        System.out.println("starting saving commits into database");
        for (Repo repo : remoteRepos) {
            List<CommitDto> commitDtos = gitHubRemote.fetchCommits(repoUserName, repo.getName());
            commitSaver.saveCommits(commitDtos, repo);
        }
    }
}

