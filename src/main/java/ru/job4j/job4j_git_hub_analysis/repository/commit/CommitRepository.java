package ru.job4j.job4j_git_hub_analysis.repository.commit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;

import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<CommitDto, Long> {

    List<CommitDto> getCommitByAuthor(String author);
}
