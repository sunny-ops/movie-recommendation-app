package net.javaguides.movies.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.entity.User;
import net.javaguides.movies.exception.ResourceNotFoundException;
import net.javaguides.movies.mapper.UserMapper;
import net.javaguides.movies.repository.UserRepository;
import net.javaguides.movies.service.UserService;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto getEmployeeById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User is not exists with given id : " + userId));

        return UserMapper.mapToEmployeeDto(user);
    }
}
