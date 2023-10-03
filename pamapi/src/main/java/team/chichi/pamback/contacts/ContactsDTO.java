package team.chichi.pamback.contacts;

import lombok.Data;

//DB에 저장되어 있는 관련 정보들의 이동을 위한 DTO를 만들어요.

@Data
public class ContactsDTO {

	private String c_Phone;
	private String u_Id;
	private String c_Name;
	private String c_Address;
	private String c_Email;
	private String c_Memo;    
}
