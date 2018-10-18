package kr.co.frozen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

import kr.co.frozen.util.AdminContactUtil;
import kr.co.frozen.util.AdminOrderingUtil;

@RequestMapping(value="/admin/contact")
@Controller
public class AdminContactController {

	private static final Logger logger = LoggerFactory.getLogger( AdminContactController.class );
	
	@Resource(name="adminOrdering")
	public AdminOrderingUtil ordering;
	
	@Resource(name="adminContact")
	public AdminContactUtil contact;
	
	@RequestMapping(value="/getContactData")
	public void getContactData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= contact.getContactData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setContactData")
	public void setContactData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= contact.setContactData(request);
		
		out.print( json );
		
		return;
	}
	
}
