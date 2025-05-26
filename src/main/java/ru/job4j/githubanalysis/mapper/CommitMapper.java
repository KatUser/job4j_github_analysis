package ru.job4j.githubanalysis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.githubanalysis.dto.CommitDto;
import ru.job4j.githubanalysis.model.Commit;

@Mapper(componentModel = "spring")
public interface CommitMapper {
    @Mapping(target = "author", source = "commit.author.name")
    @Mapping(target = "message", source = "commit.message")
    @Mapping(target = "date", source = "commit.author.date")
    CommitDto toCommitDto(Commit commit);
}
