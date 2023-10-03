package team.chichi.pamback.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class UsersController {

    private final UsersDAO usersDao;

    public UsersController(UsersDAO usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/users/{u_Id}")
    public ResponseEntity<?> getUserById(@PathVariable String u_Id) {
        UsersDTO user = usersDao.getUserById(u_Id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with u_id " + u_Id);
        }
        
        return ResponseEntity.ok(user); 
    }
}
