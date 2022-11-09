package ru.clevertec.ecl.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.ecl.entity.User;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.clevertec.ecl.dataForTest.UserForTest.user1;
import static ru.clevertec.ecl.dataForTest.UserForTest.users;

@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void findAllTest() {
        List<User> actual = userRepository.findAll(PageRequest.of(0, 20)).getContent();
        assertEquals(users(), actual);
    }

    @Test
    void findByIdTest() {
        Optional<User> optional = userRepository.findById(1L);
        optional.ifPresent(user -> assertEquals(user1(), user));
    }

    @Test
    void findByNameTest() {
        Optional<User> optional = userRepository.findByNameIgnoreCase("Qwerty");
        optional.ifPresent(user -> assertEquals(user1(), user));
    }
}
