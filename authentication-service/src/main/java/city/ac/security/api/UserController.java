package city.ac.security.api;


import city.ac.security.dto.User;
import city.ac.security.dto.UserCreate;
import city.ac.security.service.UserService;
import city.ac.security.util.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Endpoints.USERS)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreate request) {
        return ResponseEntity.ok(userService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> geUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

}
