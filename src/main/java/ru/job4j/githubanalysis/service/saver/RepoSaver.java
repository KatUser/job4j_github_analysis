package ru.job4j.githubanalysis.service.saver;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import ru.job4j.githubanalysis.model.Repo;
import ru.job4j.githubanalysis.service.repo.RepoService;

import java.util.List;

@AllArgsConstructor
@Component
public class RepoSaver {

    @Autowired
    private final RepoService repositoryService;

    public void saveRepo(List<Repo> remoteRepos) {
        for (Repo repo : remoteRepos) {
            if (repositoryService.getRepositoryByName(repo.getName()) == null) {
                repositoryService.create(new Repo(
                        repo.getId(),
                        repo.getName(),
                        repo.getUrl()
                ));
            }
        }
    }
}
