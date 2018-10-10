package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminProgramModel;

public interface AdminProgramDAO {

	public ArrayList<AdminProgramModel>			getProgramData();
	public void									addProgramData( HashMap<String, Object> parameter );
	public ArrayList<AdminProgramModel>			getProgramStorageData();
	public void									deleteProgramStorageData( HashMap<String, Object> parameter );
	public AdminProgramModel					getModifyProgramStorageData( HashMap<String, Object> parameter );
	public void									modifyProgramStorageData( HashMap<String, Object> parameter );
	public int									getMaxProgramStorageOrderNo();
	public void									insertProgramStoragedata( HashMap<String, Object> parameter );
	public void									afterInsertProgramStoragedata( HashMap<String, Object> parameter );
	public void									removeProgramData( HashMap<String, Object> parameter );
	public void									afterRemoveProgramData( HashMap<String, Object> parameter );
	
}
