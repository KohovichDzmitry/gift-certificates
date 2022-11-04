package ru.clevertec.ecl.dataForTest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.entity.User;

import java.util.Arrays;
import java.util.List;

public class UserForTest {

    public static User user1() {
        return User.builder()
                .id(1L)
                .name("Qwerty")
                .build();
    }

    public static User user2() {
        return User.builder()
                .id(2L)
                .name("Solomon")
                .build();
    }

    public static User user3() {
        return User.builder()
                .id(3L)
                .name("Ronaldo")
                .build();
    }

    public static UserDto userDto1() {
        return UserDto.builder()
                .id(1L)
                .name("Qwerty")
                .build();
    }

    public static UserDto userDto2() {
        return UserDto.builder()
                .id(2L)
                .name("Solomon")
                .build();
    }

    public static UserDto userDto3() {
        return UserDto.builder()
                .id(3L)
                .name("Ronaldo")
                .build();
    }

    public static List<User> users() {
        return Arrays.asList(user1(), user2(), user3());
    }

    public static List<UserDto> usersDto() {
        return Arrays.asList(userDto1(), userDto2(), userDto3());
    }

    public static Pageable pageable() {
        return PageRequest.of(0, 20);
    }
}
