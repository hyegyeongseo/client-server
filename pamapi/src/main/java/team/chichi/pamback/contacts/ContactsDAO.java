package team.chichi.pamback.contacts;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ContactsDAO {

	// Create
	void insertContact(ContactsDTO contact);

    // Read
    List<ContactsDTO> getContactsByUserId(String u_Id);
    ContactsDTO getContactByPhoneAndUserId(@Param("c_Phone")String c_Phone, @Param("u_Id")String u_Id);
    
    // Update
    void updateContact(ContactsDTO contact);

    // Delete
    //파라미터
    void deleteContact(String c_Phone, String u_Id);
    
    // DTO
    void deleteContact(ContactsDTO contact);
	
}
