package ru.job4j.job4j_git_hub_analysis.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitResponce {

    private String sha;

    private Commit commit;
}
