package ru.job4j.job4j_git_hub_analysis.service.repo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.job4j_git_hub_analysis.model.Repo;

import java.util.List;

public interface RepoService {

    @Transactional
    @Async
    void create(Repo repository);

    List<Repo> getRepositories();

    Repo getRepositoryByName(String repoName);
}
