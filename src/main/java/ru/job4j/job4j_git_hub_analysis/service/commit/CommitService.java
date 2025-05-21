package ru.job4j.job4j_git_hub_analysis.service.commit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;

import java.util.List;

public interface CommitService {

    @Transactional
    @Async
    void create(CommitDto commit);

    List<CommitDto> getCommitsByAuthor(String name);

    List<CommitDto> getCommits();
}
