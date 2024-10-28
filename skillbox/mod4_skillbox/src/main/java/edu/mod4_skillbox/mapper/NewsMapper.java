package edu.mod4_skillbox.mapper;

import edu.mod4_skillbox.dto.NewsDTO;
import edu.mod4_skillbox.model.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDTO toDto(News news);

    News toEntity(NewsDTO dto);
}

