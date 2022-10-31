package ru.clevertec.ecl.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final String id;

    public EntityNotFoundException(Class<?> object, Long id) {
        super(object.getSimpleName() + " with id:" + id + " not found");
        this.id = String.valueOf(id);
    }

    public EntityNotFoundException(String message) {
        super(message);
        this.id = null;
    }
}
