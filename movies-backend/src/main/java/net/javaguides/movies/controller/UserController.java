package net.javaguides.movies.controller;

import lombok.AllArgsConstructor;
import net.javaguides.movies.dto.UserDto;
import net.javaguides.movies.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    // Build Add Employee REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto UserDto){
        UserDto savedUser = userService.createUser(UserDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getEmployeeById(@PathVariable("id") int userId) {
        UserDto userDto = userService.getEmployeeById(userId);
        return ResponseEntity.ok(userDto);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!.");
    }

}
