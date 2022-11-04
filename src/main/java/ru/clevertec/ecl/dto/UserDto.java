package ru.clevertec.ecl.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserDto {

    Long id;
    String name;
}
