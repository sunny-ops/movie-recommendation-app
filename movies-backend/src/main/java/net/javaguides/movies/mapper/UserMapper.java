package net.javaguides.movies.mapper;

import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.User;

public class UserMapper {
    public static UserDto mapToEmployeeDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public static User mapToEmployee(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName()
        );
    }
}
