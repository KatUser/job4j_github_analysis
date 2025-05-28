package ru.job4j.githubanalysis.dto;

import jakarta.persistence.*;
import lombok.*;
import ru.job4j.githubanalysis.model.Repo;

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
