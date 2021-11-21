package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HelloController extends BasicController {

	@GetMapping("/")
	public String index() {
        return "index";
    }
	
	@GetMapping("/hello")
	@ResponseBody
	public Object hello() {
		return "hello 666Miss~";
	}
}
