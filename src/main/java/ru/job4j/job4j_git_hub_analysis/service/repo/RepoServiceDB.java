package ru.job4j.job4j_git_hub_analysis.service.repo;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.job4j_git_hub_analysis.model.Repo;
import ru.job4j.job4j_git_hub_analysis.repository.repo.RepoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RepoServiceDB implements RepoService {

    private final RepoRepository repoRepository;

    @Override
    @Transactional
    @Async
    public void create(Repo repository) {
        repoRepository.save(repository);
    }

    @Override
    public List<Repo> getRepositories() {
        return repoRepository.findAll();
    }

    @Override
    public Repo getRepositoryByName(String repoName) {
        return repoRepository.findRepoByName(repoName);
    }
}
