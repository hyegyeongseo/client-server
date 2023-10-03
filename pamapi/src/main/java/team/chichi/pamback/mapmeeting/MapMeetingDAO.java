package team.chichi.pamback.mapmeeting;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MapMeetingDAO {
	
	// Create
	void insertMapMeeting(MapMeetingDTO mapmeeting);
    
	// Read
	List<MapMeetingDTO> getMapMeetingByUserId(String u_Id);
    MapMeetingDTO getMapMeetingByUserIdAndPhone(@Param("m_RecordNum")String m_RecordNum, @Param("u_Id")String u_Id, @Param("c_Phone")String c_Phone);
    
    // Update
    void updateMapMeeting(MapMeetingDTO mapmeeting);
    
    // Delete
    void deleteMapMeeting(MapMeetingDTO mapmeeting);
}
