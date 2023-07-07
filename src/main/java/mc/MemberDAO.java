package mc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mariadb");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> wifiList() {
		List<MemberVO> membersList = new ArrayList<MemberVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from wifi_info order by distance limit 20";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String distance = rs.getString("distance");
				String x_swifi_mgr_no = rs.getString("x_swifi_mgr_no");
				String x_swifi_wrdofc = rs.getString("x_swifi_wrdofc");
				String x_swifi_main_nm = rs.getString("x_swifi_main_nm");
				String x_swifi_adres1 = rs.getString("x_swifi_adres1");
				String x_swifi_adres2 = rs.getString("x_swifi_adres2");
				String x_swifi_instl_floor = rs.getString("x_swifi_instl_floor");
				String x_swifi_instl_ty = rs.getString("x_swifi_instl_ty");
				String x_swifi_instl_mby = rs.getString("x_swifi_instl_mby");
				String x_swifi_svc_se = rs.getString("x_swifi_svc_se");
				String x_swifi_cmcwr = rs.getString("x_swifi_cmcwr");
				String x_swifi_cnstc_year = rs.getString("x_swifi_cnstc_year");
				String x_swifi_inout_door = rs.getString("x_swifi_inout_door");
				String x_swifi_remars3 = rs.getString("x_swifi_remars3");
				String lnt = rs.getString("lnt");
				String lat = rs.getString("lat");
				String work_dttm = rs.getString("work_dttm");
				MemberVO memberVO = new MemberVO(distance, x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1
						, x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, 
						x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lnt, lat, work_dttm);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return membersList;
	}
	
	public List<MemberVO2> historyList() {
		List<MemberVO2> historyList = new ArrayList<MemberVO2>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from history order by id desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String lnt = rs.getString("lnt");
				String lat = rs.getString("lat");
				Date search_date = rs.getDate("search_date");
				MemberVO2 memberVO2 = new MemberVO2(id, lnt, lat, search_date);
				historyList.add(memberVO2);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}
	
	public int addWifi() {
		int size = 0;
		try {
			conn = dataFactory.getConnection();
			
			URL url = this.getClass().getResource("wifiInfo.json");
//			Reader reader = new FileReader(path.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			// reader를 Object로 parse
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(input);
        
			// obj를 우선 JSONObject에 담음
			JSONObject jsonMain = (JSONObject)obj;
        
			JSONArray jsonArr = (JSONArray)jsonMain.get("DATA");
			size = jsonArr.size();
     	  // jsonArr에서 하나씩 JSONObject로 cast해서 사용
			if (jsonArr.size() > 0){
				for(int i=0; i< jsonArr.size(); i++){ 
					JSONObject jsonObj = (JSONObject)jsonArr.get(i);
                
					 
					String distance = String.valueOf(Math.floor(Math.sqrt(Math.pow((37.5544069 - Double.parseDouble((String)jsonObj.get("lnt")) ), 2) + Math.pow((126.8998666 - Double.parseDouble((String) jsonObj.get("lat"))), 2)) * 10000) / 10000.0);
					String x_swifi_mgr_no = (String) jsonObj.get("x_swifi_mgr_no");
					String x_swifi_wrdofc = (String) jsonObj.get("x_swifi_wrdofc");
					String x_swifi_main_nm = (String) jsonObj.get("x_swifi_main_nm");
					String x_swifi_adres1 = (String) jsonObj.get("x_swifi_adres1");
					String x_swifi_adres2= (String) jsonObj.get("x_swifi_adres2");
					String x_swifi_instl_floor= (String) jsonObj.get("x_swifi_instl_floor");
					String x_swifi_instl_ty= (String) jsonObj.get("x_swifi_instl_ty");
					String x_swifi_instl_mby= (String) jsonObj.get("x_swifi_instl_mby");
					String x_swifi_svc_se= (String) jsonObj.get("x_swifi_svc_se");
					String x_swifi_cmcwr= (String) jsonObj.get("x_swifi_cmcwr");
					String x_swifi_cnstc_year= (String) jsonObj.get("x_swifi_cnstc_year");
					String x_swifi_inout_door= (String) jsonObj.get("x_swifi_inout_door");
					String x_swifi_remars3= (String) jsonObj.get("x_swifi_remars3");
					String lnt= (String) jsonObj.get("lnt");
					String lat= (String) jsonObj.get("lat");
					String work_dttm= String.valueOf((Long)jsonObj.get("work_dttm"));
					System.out.println(distance + " " + x_swifi_mgr_no + " " + x_swifi_wrdofc + " " + x_swifi_main_nm + " " + x_swifi_adres1 + " " + x_swifi_adres2 + " " + x_swifi_instl_floor);
					
					String query = "insert into wifi_info"
							+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
							+ " on duplicate key update x_swifi_mgr_no=?";
					System.out.println(query);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, distance);
					pstmt.setString(2, x_swifi_mgr_no);
					pstmt.setString(3, x_swifi_wrdofc);
					pstmt.setString(4, x_swifi_main_nm);
					pstmt.setString(5, x_swifi_adres1);
					pstmt.setString(6, x_swifi_adres2);
					pstmt.setString(7, x_swifi_instl_floor);
					pstmt.setString(8, x_swifi_instl_ty);
					pstmt.setString(9, x_swifi_instl_mby);
					pstmt.setString(10, x_swifi_svc_se);
					pstmt.setString(11, x_swifi_cmcwr);
					pstmt.setString(12, x_swifi_cnstc_year);
					pstmt.setString(13, x_swifi_inout_door);
					pstmt.setString(14, x_swifi_remars3);
					pstmt.setString(15, lnt);
					pstmt.setString(16, lat);
					pstmt.setString(17, work_dttm);
					pstmt.setString(18, x_swifi_mgr_no);
					pstmt.executeUpdate();
					pstmt.close();
				}
				conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return size;
	}
	
	public void addHistory(String lat, String lnt, int no) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into history(id, lat, lnt)"
					+ " values(?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no + 1);
			pstmt.setString(2, lat);
			pstmt.setString(3, lnt);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void delHistory(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from history where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countHistory() {
		int count =0;
		try {
			conn = dataFactory.getConnection();
			String query = "select count(*) as count from history";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count");
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
