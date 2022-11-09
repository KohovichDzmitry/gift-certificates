package ru.clevertec.ecl.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.clevertec.ecl.dataForTest.UserForTest.userDto1;
import static ru.clevertec.ecl.dataForTest.UserForTest.usersDto;

@RequiredArgsConstructor
public class UserServiceImplTest extends IntegrationTestBase {

    private final UserService userService;

    @Test
    void findAllTest() {
        List<UserDto> actual = userService.findAll(PageRequest.of(0, 20));
        assertEquals(usersDto(), actual);
    }

    @Test
    void findByIdTest() {
        UserDto actual = userService.findById(1L);
        assertEquals(userDto1(), actual);
    }

    @Test
    void findByIdNegativeTest() {
        assertThrows(EntityNotFoundException.class, () -> userService.findById(8L));
    }

    @Test
    void findByNameTest() {
        UserDto actual = userService.findByNameIgnoreCase("Qwerty");
        assertEquals(userDto1(), actual);
    }

    @Test
    void findByNameNegativeTest() {
        assertThrows(EntityWithNameExistsException.class, () -> userService.findByNameIgnoreCase("Qwertu"));
    }
}
