package ru.clevertec.ecl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.entity.User;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.UserMapper;
import ru.clevertec.ecl.repository.UserRepository;
import ru.clevertec.ecl.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return userMapper.toDtoList(userRepository
                .findAll(pageable).toList());
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id)));
    }

    @Override
    public UserDto findByNameIgnoreCase(String userName) {
        return userMapper.toDto(userRepository.findByNameIgnoreCase(userName)
                .orElseThrow(() -> new EntityWithNameExistsException(User.class, userName)));
    }
}
