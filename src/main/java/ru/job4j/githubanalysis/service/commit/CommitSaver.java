package ru.job4j.githubanalysis.service.commit;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.model.Repo;
import ru.job4j.githubanalysis.service.repo.RepoService;

import java.util.List;

@AllArgsConstructor
@Component
public class CommitSaver {

    @Autowired
    private final RepoService repositoryService;

    @Autowired
    private final CommitService commitService;

    public void saveCommits(List<CommitDto> commitDtos, Repo repo) {
        for (CommitDto commitDto : commitDtos) {
            if (!commitService.getCommits().contains(commitDto)) {
                commitService.create(new CommitDto(
                        commitDto.getId(),
                        commitDto.getMessage(),
                        commitDto.getAuthor(),
                        commitDto.getDate(),
                        repositoryService.getRepositoryByName(repo.getName())));
            }
        }
    }
}
