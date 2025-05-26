package ru.job4j.githubanalysis.repository.commit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.githubanalysis.dto.CommitDto;

import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<CommitDto, Long> {

    List<CommitDto> getCommitByAuthor(String author);
}
