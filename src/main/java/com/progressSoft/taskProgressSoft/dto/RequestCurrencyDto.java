package com.progressSoft.taskProgressSoft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RequestCurrencyDto {

    @NotNull(message = "{FROM.NOT.NULL}")
    @NotBlank(message = "{FROM.NOT.EMPTY}")
    private String fromIsoCode;

    @NotNull(message = "{TO.NOT.NULL}")
    @NotBlank(message = "{TO.NOT.EMPTY}")
    private String toIsoCode;

    @NotNull(message = "{AMOUNT.NOT.NULL}")
    private BigDecimal amount;

    private LocalDateTime dealTime;
}
