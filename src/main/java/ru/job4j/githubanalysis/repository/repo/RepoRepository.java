package ru.job4j.githubanalysis.repository.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.githubanalysis.model.Repo;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {

    Repo findRepoByName(String name);
}
