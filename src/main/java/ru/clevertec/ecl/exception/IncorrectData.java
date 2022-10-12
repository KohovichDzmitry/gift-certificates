package ru.clevertec.ecl.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncorrectData {

    private String message;
    private String code;
}
