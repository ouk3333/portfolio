package kr.co.frozen.model;

import lombok.Data;

@Data
public class AdminIntroModel {
	
	private	int		uid;
	private	String	intro;
	private	String	write_time;
	private String	marker;
	private int		preserve;
	private int		delay;
	
}
