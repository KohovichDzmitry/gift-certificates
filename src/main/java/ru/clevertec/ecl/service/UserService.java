package ru.clevertec.ecl.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.ReadUserDto;
import ru.clevertec.ecl.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll(Pageable pageable);

    UserDto findById(Long id);

    UserDto findByNameIgnoreCase(String userName);

    UserDto save(ReadUserDto readUserDto);
}
