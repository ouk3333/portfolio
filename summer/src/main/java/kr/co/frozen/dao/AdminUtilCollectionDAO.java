package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Select;

public interface AdminUtilCollectionDAO {

	public ArrayList<HashMap<String, Object>>		getDBTableNames();
	public ArrayList<HashMap<String, Object>>		getSelectDataBase( HashMap<String, Object> parameter );
	public ArrayList<HashMap<String, Object>>		getSelectDataBaseData( HashMap<String, Object> parameter );
}
