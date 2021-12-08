package com.progressSoft.taskProgressSoft.controllers;

import com.progressSoft.taskProgressSoft.dto.RequestCurrencyDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataBeanProvider {


    public static RequestCurrencyDto getRequestCurrencyDto(String fromCode, String toCode) {

        RequestCurrencyDto dto = new RequestCurrencyDto();

        dto.setAmount(new BigDecimal(15));
        dto.setDealTime(LocalDateTime.now());
        dto.setFromIsoCode(fromCode);
        dto.setToIsoCode(toCode);

        return dto;
    }

}
