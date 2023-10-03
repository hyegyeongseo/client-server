package team.chichi.pamback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 정적페이지는 특정 조건에 맞는 경우에 웹뷰로 보여주기. 경로:static, templates 예) 에러페이지

// 데이터만보낼것인지 아니면 데이터를 가지고 직접 화면을 구성하는 역할까지 할 것인지

// 일정 기간마다 연락빈도 기준으로 who레벨을 자동으로 조정해줘야 한다. 이를 위한 갱신 데이터를 Back쪽에서 DB에 넣어주고 안드로이드 스튜디오에서는 이를 호출하여 사용.
// 1년 동안 연락을 하지 않은 사람에 대한 알람을 위해 그 사람과의 마지막으로 연락한지 얼마나 지났는지 달마다 체크하여, 1년이 지났으면 알람을 울리도록 한다. 이를 위한 갱신 데이터를 Back쪽에서 DB에 넣어주고 안드로이드 스튜디오에서는 이를 호출하여 사용. 


@Controller //server-side-rendering. client쪽에서 데이터를 보내면, 화면에 필요한 데이터 처리를 Back단에서 진행하고, 처리된 결과를 client쪽에서 받아서 화면에 뿌리기만 하는 경우.
public class ReferenceController{

    @GetMapping("/Hello11") // RestController인 경우 client-side-rendering. client가 데이터를 받아서 화면에 필요한 데이터를 알아서 처리하고 화면에 뿌리는 경우.
    public String haai(){
        return "Hello";
    }

    //Controller에 @ResponseBody를 붙여서 REST API로 MySQL DB에 있는 call history 테이블에서 연락 유형(sorting_Callhistory), 연락량(c_time) 데이터를 가져와서 데이터를 service 클래스에서 계산하고 나온 값을 반환값으로 하여, 처리된 데이터를 DB에 저장하는 코드를 만들고 싶어요.

    
    


}