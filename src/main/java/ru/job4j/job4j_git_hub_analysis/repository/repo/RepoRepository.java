package ru.job4j.job4j_git_hub_analysis.repository.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.job4j_git_hub_analysis.model.Repo;

public interface RepoRepository extends JpaRepository<Repo, Long> {
    Repo findRepoByName(String name);
}
