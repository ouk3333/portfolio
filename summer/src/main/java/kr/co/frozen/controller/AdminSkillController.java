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

import kr.co.frozen.util.AdminOrderingUtil;
import kr.co.frozen.util.AdminSkillUtil;

@RequestMapping(value="/admin/skill")
@Controller
public class AdminSkillController {

	private static final Logger logger = LoggerFactory.getLogger( AdminSkillController.class );
	
	@Resource(name="adminOrdering")
	public AdminOrderingUtil ordering;
	
	@Resource(name="adminSkill")
	public AdminSkillUtil skill;
	
	@RequestMapping(value="/getSkillAbilityData")
	public void getSkillAbility( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.getSkillAbilityData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getSkillAbilityStorageData")
	public void getSkillAbilityStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.getSkillAbilityStorageData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/insertSkillStorageData")
	public void insertSkillStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.insertSkillStorageData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/deleteSkillStorageData")
	public void deleteSkillStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.deleteSkillStorageData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/addSkillStorageData")
	public void addSkillStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.addSkillStorageData(request);
		
		out.print( json );
	}

	@RequestMapping(value="/getModifySkillStorageData")
	public void getModifySkillStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.getModifySkillStorageData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/setModifySkillStorageData")
	public void setModifySkillStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.setModifySkillStorageData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/removeSkillAbilityData")
	public void removeSkillAbilityData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.removeSkillAbilityData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/setSkillAbilityData")
	public void setSkillAbilityData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.setSkillAbilityData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/setSkillColorData")
	public void setSkillColorData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= skill.setSkillColorData(request);
		
		out.print( json );
	}
	
}
