package com.progressSoft.taskProgressSoft.dto.fetchAllCurrency;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseFetchAllCurrencyDto {

    private String fromIsoCode;

    private String toIsoCode;

    private String amount;

    private LocalDateTime dealTime;
}
