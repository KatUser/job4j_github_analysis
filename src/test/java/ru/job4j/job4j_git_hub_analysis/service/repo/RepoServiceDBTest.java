package ru.job4j.job4j_git_hub_analysis.service.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.job4j.job4j_git_hub_analysis.model.Repo;
import ru.job4j.job4j_git_hub_analysis.repository.repo.RepoRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepoServiceDBTest {

    @InjectMocks
    private RepoRepository mockRepoRepository = Mockito.mock(RepoRepository.class);

    @Mock
    private Repo mockRepo = Mockito.mock(Repo.class);

    private final RepoService repoService = new RepoServiceDB(mockRepoRepository);

    @Test
    void whenHaveRepoThenReturnRepo() {
        when(mockRepoRepository.findAll()).thenReturn(List.of(mockRepo));

        assertThat(repoService.getRepositories().contains(mockRepo)).isTrue();
    }

    @Test
    void whenHaveRepoThenCanGetRepoByName() {
        when(mockRepoRepository.findRepoByName(mockRepo.getName())).thenReturn(mockRepo);

        assertThat(repoService.getRepositoryByName(mockRepo.getName())).isSameAs(mockRepo);
    }

    @Test
    void whenHaveNoRepoThenReturnEmptyList() {

        assertThat(repoService.getRepositories().isEmpty()).isTrue();
    }

    @Test
    void whenHaveNoRepoThenCannotGetRepoByName() {

        assertThat(repoService.getRepositoryByName(mockRepo.getName())).isNull();
    }
}