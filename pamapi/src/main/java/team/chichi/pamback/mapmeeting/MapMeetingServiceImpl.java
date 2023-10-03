package team.chichi.pamback.mapmeeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MapMeetingServiceImpl implements MapMeetingService {

	@Autowired
    private MapMeetingDAO mapmeetingMapper;

    
	@Override
	public List<MapMeetingDTO> getMapMeetingByUserId(String u_Id) {
		return mapmeetingMapper.getMapMeetingByUserId(u_Id);
	}
	
	@Override
	public MapMeetingDTO getMapMeetingByUserIdAndPhone(String m_RecordNum, String u_Id, String c_Phone) {
		return mapmeetingMapper.getMapMeetingByUserIdAndPhone(m_RecordNum, u_Id, c_Phone);
	}

    @Override
    public void insertMapMeeting(MapMeetingDTO mapmeeting) {
    	mapmeetingMapper.insertMapMeeting(mapmeeting);
    }



    @Override
    public void updateMapMeeting(MapMeetingDTO mapmeeting) {
    	mapmeetingMapper.updateMapMeeting(mapmeeting);
    }

    @Override
    public void deleteMapMeeting(MapMeetingDTO mapmeeting) {
    	mapmeetingMapper.deleteMapMeeting(mapmeeting);
    }
}

