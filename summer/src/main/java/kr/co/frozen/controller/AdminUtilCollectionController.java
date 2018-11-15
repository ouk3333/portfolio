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

import kr.co.frozen.util.AdminUtilCollectionUtil;

@RequestMapping(value="/admin/utilCollection")
@Controller
public class AdminUtilCollectionController {

	private static final Logger logger = LoggerFactory.getLogger( AdminUtilCollectionController.class );
	
	@Resource(name="adminCollection")
	public AdminUtilCollectionUtil utilCollection;
	
	@RequestMapping(value="/getShortURL")
	public void getShortURL( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= utilCollection.getShortURL(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/getDBTableNames")
	public void getDBTableNames( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= utilCollection.getDBTableNames();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getSelectDataBase")
	public void getSelectDataBase( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= utilCollection.getSelectDataBase(request);
		
		out.print( json );
	}
}
