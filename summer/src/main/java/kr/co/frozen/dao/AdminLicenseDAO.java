package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminLicenseModel;

public interface AdminLicenseDAO {

	public ArrayList<AdminLicenseModel>		getLicenseData();
	public void								addLicenseData( HashMap<String, Object> parameter );
	
	public ArrayList<AdminLicenseModel>		getLicenseStorageData();

	public AdminLicenseModel				getLicenseStorageData_single( HashMap<String, Object> parameter );
	public int								getMaxLicenseStorageOrderNo();
	public void								addLicenseStorageData( HashMap<String, Object> parameter );
	public void								afterAddLicenseStorageData( HashMap<String, Object> parameter );
	public void								removeLicenseData( HashMap<String, Object> parameter );
	public void								afterRemoveLicenseData( HashMap<String, Object> parameter );
	public void								deleteLicenseStorageData( HashMap<String, Object> parameter );
	public void								setModifyLicenseData( HashMap<String, Object> parameter );
}
