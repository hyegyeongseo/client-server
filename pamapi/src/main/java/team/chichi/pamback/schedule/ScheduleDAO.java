package team.chichi.pamback.schedule;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;



@Repository
@Mapper
public interface ScheduleDAO {

		// Create
		void insertSchedule(ScheduleDTO schedule);

	    // Read
	    List<ScheduleDTO> getScheduleListByUserId(String u_Id);
	 
	    // Update
	    void updateSchedule(ScheduleDTO schedule);

	    // Delete
	    void deleteSchedule(ScheduleDTO schedule);

	
}
