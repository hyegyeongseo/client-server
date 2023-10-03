package team.chichi.pamback.mapmeeting;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface MapMeetingService {

	// Create
	void insertMapMeeting(MapMeetingDTO mapmeeting);
    
	// Read
	List<MapMeetingDTO> getMapMeetingByUserId(String u_Id);
    MapMeetingDTO getMapMeetingByUserIdAndPhone(String m_RecordNum, String u_Id, String c_Phone);
    
    // Update
    void updateMapMeeting(MapMeetingDTO mapmeeting);
    
    // Delete
    void deleteMapMeeting(MapMeetingDTO mapmeeting);
}

