package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminProgramModel;

public interface AdminProgramDAO {

	public ArrayList<AdminProgramModel>			getProgramData();
	public void									addProgramData( HashMap<String, Object> parameter );
	public ArrayList<AdminProgramModel>			getProgramStorageData();
	
}
