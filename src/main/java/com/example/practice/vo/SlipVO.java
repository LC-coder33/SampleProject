package com.example.practice.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SlipVO {
    int pvCode = 0;
    String pvslipCode = null;
    String pvName = null;
    String pvDate = null;
    String pvslipName = null;
    String pvCmpy = null;
    int pvPrice = 0;
    int pvFee = 0;
    String pvPay = null;
    String pvComment = null;

}
