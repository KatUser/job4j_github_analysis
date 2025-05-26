package ru.job4j.githubanalysis.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.model.Author;
import ru.job4j.githubanalysis.model.Commit;
import ru.job4j.githubanalysis.model.Repo;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommitMapperTest {

    CommitMapper commitMapper = new CommitMapperImpl();

    @Mock
    Repo mockRepo = Mockito.mock(Repo.class);

    @Test
    void testCommitMapper() {
        var timeNow = LocalDateTime.now();
        var author = new Author("testname", "test@mail.com", timeNow);
        var commit = new Commit(author, "message", "url");
        var commitDto = new CommitDto(1L, "message", author.getName(), timeNow, mockRepo);

        var commitDtoResult = commitMapper.toCommitDto(commit);

        assertThat(commitDtoResult.getClass()).isEqualTo(CommitDto.class);
        assertEquals(commitDtoResult.getAuthor(), commitDto.getAuthor());
        assertEquals(commitDtoResult.getMessage(), commitDto.getMessage());
        assertEquals(commitDtoResult.getDate(), commitDto.getDate());
    }
}