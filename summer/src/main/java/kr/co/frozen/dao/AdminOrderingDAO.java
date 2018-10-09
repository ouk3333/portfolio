package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminOrderingDAO {
	
	public void 								setDataOrdering( HashMap<String, Object> parameter );
	public ArrayList<HashMap<String, Object>> 	getSortingData( HashMap<String, Object> parameter );
	public void 								setSortingData( HashMap<String, Object> parameter );
	
}
