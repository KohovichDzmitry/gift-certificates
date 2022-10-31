package ru.clevertec.ecl.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T, D> {

    List<D> findAll(Pageable pageable);

    D findById(Long id);

    D save(T readDto);

    D update(T readDto, Long id);

    void delete(Long id);
}
