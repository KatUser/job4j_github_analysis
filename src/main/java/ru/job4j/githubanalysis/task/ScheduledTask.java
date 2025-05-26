package ru.job4j.githubanalysis.task;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ru.job4j.githubanalysis.dto.CommitDto;

import ru.job4j.githubanalysis.model.Repo;
import ru.job4j.githubanalysis.service.repo.RepoService;
import ru.job4j.githubanalysis.service.saver.CommitSaver;
import ru.job4j.githubanalysis.service.githubremote.GitHubRemote;
import ru.job4j.githubanalysis.service.saver.RepoSaver;

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

    @Autowired
    private final RepoService repositoryService;

    @Value("${repousername}")
    private String repoUserName;

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void scheduledTaskRun() {
        System.out.println(repoUserName);
        System.out.println("starting fetching remote repositories");
        List<Repo> remoteRepos = gitHubRemote.fetchRepositories(repoUserName);

        System.out.println("starting saving remote repositories into database");
        repoSaver.saveData(remoteRepos);

        System.out.println("starting saving commits into database");
        for (Repo repo : remoteRepos) {
            List<CommitDto> commitDtos = gitHubRemote.fetchCommits(repoUserName, repo.getName());
            commitDtos.forEach(commitDto -> commitDto
                    .setRepository(repositoryService.getRepositoryByName(repo.getName())));
            commitSaver.saveData(commitDtos);
        }
    }
}

