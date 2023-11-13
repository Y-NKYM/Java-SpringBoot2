package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.constant.UrlConst;

@Controller
@RequestMapping()
public class HomeController {
	@GetMapping(UrlConst.MYPAGE)
	public String view() {
		return "mypage";
	}
}
