package edu.mod8_skillbox_news.mapper;

import edu.mod8_skillbox_news.dto.NewsDTO;
import edu.mod8_skillbox_news.model.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDTO toDto(News news);

    News toEntity(NewsDTO dto);
}

