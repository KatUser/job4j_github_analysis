package ru.job4j.job4j_git_hub_analysis.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Commit {

    public Author author;

    public String message;

    public String url;

}
