package ru.clevertec.ecl.integration.dataForTest;

import ru.clevertec.ecl.dto.ReadTagDto;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.entity.Tag;

import java.util.Arrays;
import java.util.List;

public class TagForTest {

    public static Tag tag1() {
        return Tag.builder()
                .id(1L)
                .name("sport")
                .build();
    }

    public static Tag tag2() {
        return Tag.builder()
                .id(2L)
                .name("fun")
                .build();
    }

    public static Tag tag3() {
        return Tag.builder()
                .id(3L)
                .name("mood")
                .build();
    }

    public static Tag tag4() {
        return Tag.builder()
                .id(4L)
                .name("summer")
                .build();
    }

    public static Tag tag5() {
        return Tag.builder()
                .id(5L)
                .name("nature")
                .build();
    }

    public static TagDto tagDto1() {
        return TagDto.builder()
                .id(1L)
                .name("sport")
                .build();
    }

    public static ReadTagDto readTagDto1() {
        return ReadTagDto.builder()
                .name("sport")
                .build();
    }

    public static TagDto tagDto2() {
        return TagDto.builder()
                .id(2L)
                .name("fun")
                .build();
    }

    public static TagDto tagDto3() {
        return TagDto.builder()
                .id(3L)
                .name("mood")
                .build();
    }

    public static TagDto tagDto4() {
        return TagDto.builder()
                .id(4L)
                .name("summer")
                .build();
    }

    public static ReadTagDto readTagDto4() {
        return ReadTagDto.builder()
                .name("summer")
                .build();
    }

    public static TagDto tagDto5() {
        return TagDto.builder()
                .id(5L)
                .name("nature")
                .build();
    }

    public static Tag tagForSave() {
        return Tag.builder()
                .name("scary")
                .build();
    }

    public static List<Tag> tags() {
        return Arrays.asList(tag1(), tag2(), tag3(), tag4(), tag5());
    }
}
