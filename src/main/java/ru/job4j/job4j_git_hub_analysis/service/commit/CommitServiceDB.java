package ru.job4j.job4j_git_hub_analysis.service.commit;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;
import ru.job4j.job4j_git_hub_analysis.repository.commit.CommitRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CommitServiceDB implements CommitService {

    private final CommitRepository commitRepository;

    @Override
    @Transactional
    @Async
    public void create(CommitDto commit) {
        commitRepository.save(commit);
    }

    @Override
    public List<CommitDto> getCommitsByAuthor(String name) {
        return commitRepository.getCommitByAuthor(name);
    }

    @Override
    public List<CommitDto> getCommits() {
        return commitRepository.findAll();
    }
}
