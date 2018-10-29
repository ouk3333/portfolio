package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminSkillModel;

public interface AdminSkillDAO {

	public ArrayList<AdminSkillModel>		getSkillAbilityData();
	public ArrayList<AdminSkillModel>		getSkillAbilityStorageData();
	public void								insertSkillStorageData( HashMap<String, Object> parameter );
	public void								deleteSkillStorageData( HashMap<String, Object> parameter );
	public AdminSkillModel					getSkillAbilityStorageData_single( HashMap<String, Object> parameter );
	public void								addSkillStorageData( HashMap<String, Object> parameter );
	public void								afterAddSkillStorageData( HashMap<String, Object> parameter );
	public void								setModifySkillStorageData( HashMap<String, Object> parameter );
	public void								removeSkillAbilityData( HashMap<String, Object> parameter );
	public void								afterRemoveSkillAbilityData( HashMap<String, Object> parameter );
	public void								setSkillAbilityData( HashMap<String, Object> parameter );
	public void								setSkillColorData( HashMap<String, Object> parameter );
}
