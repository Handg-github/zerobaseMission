package mc;

import java.sql.Date;

public class MemberVO2 {
	private int id;
	private String lnt;
	private String lat;
	private Date search_date;
	
	public MemberVO2(int id, String lnt, String lat, Date search_date) {
		super();
		this.id = id;
		this.lnt = lnt;
		this.lat = lat;
		this.search_date = search_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLnt() {
		return lnt;
	}

	public void setLnt(String lnt) {
		this.lnt = lnt;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Date getSearch_date() {
		return search_date;
	}

	public void setSearch_date(Date search_date) {
		this.search_date = search_date;
	}
	
	
	
}
