package team.chichi.pamback.contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactsServiceImpl implements ContactsService {

	@Autowired
    private ContactsDAO contactsMapper;

    @Override
    public List<ContactsDTO> getContactsByUserId(String u_Id) {
        return contactsMapper.getContactsByUserId(u_Id);
    }

    @Override
    public ContactsDTO getContactByPhoneAndUserId(String c_Phone, String u_Id) {
        return contactsMapper.getContactByPhoneAndUserId(c_Phone, u_Id);
    }

    @Override
    public void insertContact(ContactsDTO contact) {
        contactsMapper.insertContact(contact);
    }

    @Override
    public void updateContact(ContactsDTO contact) {
        contactsMapper.updateContact(contact);
    }

    @Override
    public void deleteContact(String c_Phone, String u_Id) {
        contactsMapper.deleteContact(c_Phone, u_Id);
    }

	@Override
	public void deleteContact(ContactsDTO contact) {
		contactsMapper.deleteContact(contact);
		
	}

}
