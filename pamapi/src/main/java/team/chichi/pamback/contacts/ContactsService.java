package team.chichi.pamback.contacts;

import java.util.List;

public interface ContactsService {

	// Create
	void insertContact(ContactsDTO contact);

    // Read
    List<ContactsDTO> getContactsByUserId(String u_Id);
    ContactsDTO getContactByPhoneAndUserId(String c_Phone, String u_Id);
    
    // Update
    void updateContact(ContactsDTO contact);

    // Delete
    //파라미터
    void deleteContact(String c_Phone, String u_Id);
    
    // DTO
    void deleteContact(ContactsDTO contact);
}
