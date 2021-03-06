package com.office.part_1.web;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Hello world!
 *
 */
@Controller
@RequestMapping("/app")
@EnableAutoConfiguration
public class App {

	@RequestMapping("/hello")
	@ResponseBody
	String home(){
		return "Hello World !";
	}
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
