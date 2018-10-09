package kr.co.frozen.model;

import lombok.Data;

@Data
public class AdminTimelineModel {

	private int		uid;
	private String	event;
	private String	year;
	private String	month;
	private	String 	day;
	private String	remarks;
	private String	view_sw;
	private	int		order_no;
	
}
