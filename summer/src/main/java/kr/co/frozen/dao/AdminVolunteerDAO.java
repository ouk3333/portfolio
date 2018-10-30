package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminVolunteerModel;

public interface AdminVolunteerDAO {

	public ArrayList<AdminVolunteerModel>		getVolunteerData();
	public void									insertVolunteerData( HashMap<String, Object> parameter );
	public void									deleteVolunteerData( HashMap<String, Object> parameter );
	public AdminVolunteerModel					getVolunteerData_single( HashMap<String, Object> parameter );
	public void									updateVolunteerData( HashMap<String, Object> parameter );
}
