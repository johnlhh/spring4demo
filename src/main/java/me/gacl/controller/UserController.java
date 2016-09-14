package me.gacl.controller;

import me.gacl.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by luohh on 2016/8/22.
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceI userServiceI;
}
