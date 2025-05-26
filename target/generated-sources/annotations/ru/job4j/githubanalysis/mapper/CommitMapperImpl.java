package ru.job4j.githubanalysis.mapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.model.Author;
import ru.job4j.githubanalysis.model.Commit;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T18:10:40+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Eclipse Adoptium)"
)
@Component
public class CommitMapperImpl implements CommitMapper {

    @Override
    public CommitDto toCommitDto(Commit commit) {
        if ( commit == null ) {
            return null;
        }

        CommitDto commitDto = new CommitDto();

        commitDto.setAuthor( commitAuthorName( commit ) );
        commitDto.setMessage( commit.getMessage() );
        commitDto.setDate( commitAuthorDate( commit ) );

        return commitDto;
    }

    private String commitAuthorName(Commit commit) {
        if ( commit == null ) {
            return null;
        }
        Author author = commit.getAuthor();
        if ( author == null ) {
            return null;
        }
        String name = author.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private LocalDateTime commitAuthorDate(Commit commit) {
        if ( commit == null ) {
            return null;
        }
        Author author = commit.getAuthor();
        if ( author == null ) {
            return null;
        }
        LocalDateTime date = author.getDate();
        if ( date == null ) {
            return null;
        }
        return date;
    }
}
