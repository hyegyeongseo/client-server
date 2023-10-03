package team.chichi.pamback.schedule;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ScheduleDTO {
	
	private String s_Num;
	private String u_Id;
	private String c_Phone;
	private String s_Title;
	private Date s_Date;
	private Timestamp s_Time;
	private String s_Contents;
	private String s_Memo;
	private String s_Type;
	private String s_Place;
	

}
