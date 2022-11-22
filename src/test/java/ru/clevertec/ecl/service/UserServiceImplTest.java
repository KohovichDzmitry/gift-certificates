package ru.clevertec.ecl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.mapper.UserMapper;
import ru.clevertec.ecl.repository.UserRepository;
import ru.clevertec.ecl.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.clevertec.ecl.dataForTest.UserForTest.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllTest() {
        doReturn(new PageImpl<>(users()))
                .when(userRepository).findAll(pageable());
        doReturn(usersDto())
                .when(userMapper).toDtoList(users());
        List<UserDto> actual = userService.findAll(pageable());
        List<UserDto> expected = usersDto();
        assertEquals(expected, actual);
        verify(userRepository).findAll(pageable());
        verify(userMapper).toDtoList(users());
    }

    @Test
    void findByIdTest() {
        doReturn(Optional.of(user1()))
                .when(userRepository).findById(1L);
        doReturn(userDto1())
                .when(userMapper).toDto(user1());
        UserDto actual = userService.findById(1L);
        UserDto expected = userDto1();
        assertEquals(expected, actual);
        verify(userRepository).findById(1L);
        verify(userMapper).toDto(user1());
    }

    @Test
    void findByIdNegativeTest() {
        doReturn(Optional.empty())
                .when(userRepository).findById(1L);
        assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    void findByNameTest() {
        doReturn(Optional.of(user1()))
                .when(userRepository).findByNameIgnoreCase("Qwerty");
        doReturn(userDto1())
                .when(userMapper).toDto(user1());
        UserDto actual = userService.findByNameIgnoreCase("Qwerty");
        UserDto expected = userDto1();
        assertEquals(expected, actual);
        verify(userRepository).findByNameIgnoreCase("Qwerty");
        verify(userMapper).toDto(user1());
    }

    @Test
    void findByNameNegativeTest() {
        doReturn(Optional.empty())
                .when(userRepository).findByNameIgnoreCase("Qwerty");
        assertThrows(EntityWithNameExistsException.class, () -> userService.findByNameIgnoreCase("Qwerty"));
    }

    @Test
    void saveTest() {
        doReturn(Optional.empty())
                .when(userRepository).findByNameIgnoreCase(any(String.class));
        doReturn(user1WithoutId())
                .when(userMapper).toEntity(readUserDto1());
        doReturn(user1())
                .when(userRepository).save(user1WithoutId());
        doReturn(userDto1())
                .when(userMapper).toDto(user1());
        UserDto actual = userService.save(readUserDto1());
        UserDto expected = userDto1();
        assertEquals(expected, actual);
        verify(userRepository).findByNameIgnoreCase(any(String.class));
        verify(userMapper).toEntity(readUserDto1());
        verify(userRepository).save(user1WithoutId());
        verify(userMapper).toDto(user1());
    }

    @Test
    void saveWithExistsNameTest() {
        doReturn(Optional.empty())
                .when(userRepository).findByNameIgnoreCase(any(String.class));
        doReturn(user1WithoutId())
                .when(userMapper).toEntity(readUserDto1());
        doThrow(EntityWithNameExistsException.class)
                .when(userRepository).save(user1WithoutId());
        assertThrows(EntityWithNameExistsException.class, () -> userService.save(readUserDto1()));
    }
}
