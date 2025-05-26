package ru.job4j.githubanalysis.service.saver;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.service.commit.CommitService;

import java.util.List;

@AllArgsConstructor
@Component
public class CommitSaver implements DataSaver<CommitDto> {

    @Autowired
    private final CommitService commitService;

    @Override
    public void saveData(List<CommitDto> commitDtos) {
        for (CommitDto commitDto : commitDtos) {
            if (!commitService.getCommits().contains(commitDto)) {
                commitService.create(commitDto);
            }
        }
    }
}
