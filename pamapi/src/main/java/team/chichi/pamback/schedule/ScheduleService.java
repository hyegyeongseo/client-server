package team.chichi.pamback.schedule;

import java.util.List;

public interface ScheduleService {

	// Create
    void insertSchedule(ScheduleDTO schedule);
    
    // Read
    List<ScheduleDTO> getScheduleListByUserId(String u_Id);
    
    // Update
    void updateSchedule(ScheduleDTO schedule);

    // Delete
    void deleteSchedule(ScheduleDTO schedule);
}
