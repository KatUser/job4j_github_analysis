package ru.job4j.githubanalysis.model;

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
