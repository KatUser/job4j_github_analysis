package ru.job4j.job4j_git_hub_analysis.dto;

import jakarta.persistence.*;
import lombok.*;
import ru.job4j.job4j_git_hub_analysis.model.Repo;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "commit")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommitDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String message;

    private String author;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "repository_id")
    private Repo repository;
}
