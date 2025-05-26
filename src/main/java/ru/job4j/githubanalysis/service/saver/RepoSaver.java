package ru.job4j.githubanalysis.service.saver;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import ru.job4j.githubanalysis.model.Repo;
import ru.job4j.githubanalysis.service.repo.RepoService;

import java.util.List;

@AllArgsConstructor
@Component
public class RepoSaver implements DataSaver<Repo> {

    @Autowired
    private final RepoService repoService;

    @Override
    public void saveData(List<Repo> remoteRepos) {
        for (Repo repo : remoteRepos) {
            if (repoService.getRepositoryByName(repo.getName()) == null) {
                repoService.create(repo);
            }
        }
    }
}
