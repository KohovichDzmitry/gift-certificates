package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.ecl.dto.ReadTagDto;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.entity.Tag;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    Tag toEntity(ReadTagDto readTagDto);

    Tag toUpdateEntity(ReadTagDto tagDto, @MappingTarget Tag tag);

    TagDto toDto(Tag tag);

    List<TagDto> toDtoList(List<Tag> tagList);
}
