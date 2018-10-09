package kr.co.frozen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/error")
public class ErrorController {
	
	@RequestMapping(value="/error404")
	public String error400page( HttpServletRequest request, HttpServletResponse response ) {

		response.setStatus( HttpServletResponse.SC_OK );
		
		return "error/404";
	}
	
	@RequestMapping(value="/error500")
	public String error500page( HttpServletResponse response, HttpServletRequest request ) {

		response.setStatus( HttpServletResponse.SC_OK );
		
		return "error/500";
	}
	
}
