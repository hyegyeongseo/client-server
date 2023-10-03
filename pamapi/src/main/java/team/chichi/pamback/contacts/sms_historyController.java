package team.chichi.pamback.contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sms_historyController {
    //@Autowired
    private sms_historyService sms_historyService;

    @PostMapping("/history/sms")
    public void addSmsHistoryList(@RequestBody List<sms_historyDTO> smsHistoryDataList) {
        sms_historyService.addSmsHistoryList(smsHistoryDataList);
    }
}
