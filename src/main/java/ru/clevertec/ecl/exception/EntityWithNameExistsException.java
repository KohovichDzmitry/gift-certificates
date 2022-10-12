package ru.clevertec.ecl.exception;

import lombok.Getter;

@Getter
public class EntityWithNameExistsException extends RuntimeException {

    private final String name;

    public EntityWithNameExistsException(Class<?> object, String name) {
        super(object.getSimpleName() + " with the same name:" + name + " already exists");
        this.name = String.valueOf(name);
    }
}
