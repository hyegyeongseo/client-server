package team.chichi.pamback.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDAO usersMapper;

    @Override
    public UsersDTO getUserById(String u_Id) {
        return usersMapper.getUserById(u_Id);
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        return usersMapper.getAllUsers();
    }

    @Override
    public void insertUser(UsersDTO user) {
        usersMapper.insertUser(user);
    }

    @Override
    public void updateUser(UsersDTO user) {
        usersMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String u_Id) {
        usersMapper.deleteUser(u_Id);
    }

	@Override
	public void deleteUserDTO(UsersDTO user) {
		usersMapper.deleteUserDTO(user);
		
	}
}
