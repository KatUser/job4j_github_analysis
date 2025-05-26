package ru.job4j.job4j_git_hub_analysis.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import ru.job4j.job4j_git_hub_analysis.dto.CommitDto;
import ru.job4j.job4j_git_hub_analysis.model.Author;
import ru.job4j.job4j_git_hub_analysis.model.Repo;
import ru.job4j.job4j_git_hub_analysis.service.commit.CommitService;
import ru.job4j.job4j_git_hub_analysis.service.repo.RepoService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class GitHubControllerTest {

    @InjectMocks
    private RepoService mockRepoService = Mockito.mock(RepoService.class);

    @InjectMocks
    private CommitService mockCommitService = Mockito.mock(CommitService.class);

    @Mock
    private Repo mockRepo = Mockito.mock(Repo.class);

    @Mock
    private CommitDto mockCommitDto = Mockito.mock(CommitDto.class);

    @Mock
    private Author mockAuthor = Mockito.mock(Author.class);

    private final GitHubController gitHubController = new GitHubController(
            mockRepoService, mockCommitService
    );

    @Test
    void whenGetAllReposThenReceiveAllRepos() {

        when(mockRepoService.getRepositories()).thenReturn(List.of(mockRepo));

        assertThat(gitHubController.getRepositories().contains(mockRepo)).isTrue();
    }

    @Test
    void whenThereAreNoReposThenReceiveEmpty() {

        when(mockRepoService.getRepositories()).thenReturn(List.of());

        assertThat(gitHubController.getRepositories().isEmpty()).isTrue();
    }

    @Test
    void whenGetAllCommitsByAuthorThenReceiveAllCommits() {

        when(mockCommitService.getCommitsByAuthor(mockAuthor.getName())).thenReturn(List.of(mockCommitDto));

        assertThat(gitHubController.getCommitsByAuthor(mockAuthor.getName()).contains(mockCommitDto)).isTrue();
    }

    @Test
    void whenThereAreNoCommitsByAuthorThenReceiveEmpty() {

        when(mockCommitService.getCommitsByAuthor(mockAuthor.getName())).thenReturn(List.of());

        assertThat(gitHubController.getCommitsByAuthor(mockAuthor.getName()).isEmpty()).isTrue();
    }

    @Test
    void whenCreateRepoThenCreateRepo() {

        doNothing().when(mockRepoService).create(mockRepo);

        assertThat(gitHubController.create(mockRepo)).isInstanceOf(ResponseEntity.class);
    }
}
