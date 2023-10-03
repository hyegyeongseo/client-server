package team.chichi.pamback.contacts;

import java.math.BigInteger;

import lombok.Data;

@Data
public class sms_historyDTO {
    private BigInteger sms_Id;
    private int sms_Type;
    private String sms_Contents;
    private String sms_Time;
}
