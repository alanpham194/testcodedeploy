package com.travala.democodedeploy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    String testController() {
        return "Hello Ngày Mới!!!!!!!!!!";
    }
}
