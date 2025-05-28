package ru.job4j.githubanalysis.service.commit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.githubanalysis.dto.CommitDto;

import java.util.List;

public interface CommitService {

    @Transactional
    @Async
    void create(CommitDto commit);

    List<CommitDto> getCommitsByAuthor(String name);

    List<CommitDto> getCommits();
}
