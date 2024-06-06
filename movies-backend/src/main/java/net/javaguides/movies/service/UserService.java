package net.javaguides.movies.service;

import net.javaguides.movies.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getEmployeeById(int employeeId);

    void deleteUser(int userId);
}
