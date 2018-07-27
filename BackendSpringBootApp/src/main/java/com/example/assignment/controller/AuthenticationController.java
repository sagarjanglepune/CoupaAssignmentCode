package com.example.assignment.controller;

import com.example.assignment.model.Login;
import com.example.assignment.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    LoginRepository loginRepository;

    /**
     * This is the test method for this Controller.
     * @return String
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = { MediaType.TEXT_PLAIN_VALUE })
    public @ResponseBody String test() {
        return "Test Method Success : Service up and Running";
    }

    /**
     * This method is used to fetch the user's login details from database.
     * It also validates the password and returns the response as SUCCESS / FALSE.
     *
     * @param  payload Map<String, String>
     * @return response String
     */
    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.TEXT_PLAIN_VALUE })
    public @ResponseBody String login(@RequestBody Map<String, String> payload)
            throws Exception {
        String response = "EXCEPTION";

        Login user = loginRepository.findOneByUsername(payload.get("username"));

        if (null != user) {
            if (payload.get("password").trim().equalsIgnoreCase(user.getPassword())) {
                response = "VALID USER : PASSWORD MATCHED";
            } else {
                // Password doesn't match sent in request.
                response = "INVALID USER : PASSWORD NOT MATCHED.";
            }
        } else {
            // User Not Found in Database.
            response = "INVALID USER : USER NOT FOUND IN DB.";
        }
        return response;
    }

    /**
     * This method is used to add a new user details in the login database.
     * @param  newUser Login
     * @return response String
     */
    @RequestMapping(value = "/addNewUser",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.TEXT_PLAIN_VALUE })
    public @ResponseBody String addNewUser(@Valid @RequestBody Login newUser) {
        String response = "EXCEPTION";
        Login newUserCreated = loginRepository.saveAndFlush(newUser);
        if (null != newUserCreated) {
            response = "VALID USER : NEW USER CREATED IN DB : UserName --> ." + newUserCreated.getUsername();
        } else {
            response = "EXCEPTION : UNABLE TO CREATE THE USER IN DB.";
        }
        return response;
    }
}