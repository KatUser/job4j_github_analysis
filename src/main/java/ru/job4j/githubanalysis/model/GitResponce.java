package ru.job4j.githubanalysis.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitResponce {

    private String sha;

    private Commit commit;
}
