package team.chichi.pamback.mapmeeting;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class MapMeetingDTO {

	private int m_RecordNum;
	private String u_Id;
	private String c_Phone;
	private String m_Title;
	private String m_Contents;
	private Date m_date;
	private Timestamp m_time;
	private String m_place;
	private String HashTag;
	private String m_Check;
	
	
}
