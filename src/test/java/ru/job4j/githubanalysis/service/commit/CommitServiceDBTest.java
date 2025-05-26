package ru.job4j.githubanalysis.service.commit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.model.Author;
import ru.job4j.githubanalysis.repository.commit.CommitRepository;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommitServiceDBTest {

    @InjectMocks
    private CommitRepository mockCommitRepository = Mockito.mock(CommitRepository.class);

    @Mock
    private final CommitDto mockCommitDto = Mockito.mock(CommitDto.class);

    @Mock
    private final Author mockAuthor = Mockito.mock(Author.class);

    private final CommitService commitService =  new CommitServiceDB(mockCommitRepository);

    @Test
    void whenHaveCommitThenReturnCommitByAuthor() {
       when(mockCommitRepository.getCommitByAuthor(mockAuthor.getName()))
               .thenReturn(List.of(mockCommitDto));

       assertThat(commitService.getCommitsByAuthor(mockAuthor.getName()))
               .isEqualTo(List.of(mockCommitDto));
    }

    @Test
    void whenHaveNoCommitThenReturnEmptyListByAuthor() {

        assertThat(commitService.getCommitsByAuthor(mockAuthor.getName()))
                .isEqualTo(emptyList());
    }


    @Test
    void whenHaveCommitThenReturnCommit() {
        when(mockCommitRepository.findAll()).thenReturn(List.of(mockCommitDto));

        assertThat(commitService.getCommits().contains(mockCommitDto)).isTrue();
    }

    @Test
    void whenHaveNoCommitThenReturnEmptyListWhenFindAll() {

        assertThat(commitService.getCommits()).isEqualTo(emptyList());
    }

  
}