package com.progressSoft.taskProgressSoft.controllers;

import com.progressSoft.taskProgressSoft.dto.RequestCurrencyDto;
import com.progressSoft.taskProgressSoft.enums.IsoCodeEnum;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerTest extends ApplicationTests {


    @Test
    public void testNormalFlowCurrency() throws Exception{

        RequestCurrencyDto currencyDto = DataBeanProvider.getRequestCurrencyDto(IsoCodeEnum.USD.getCode(), IsoCodeEnum.JOD.getCode());

        // call currency isoCode Service
        mockMvc.perform(MockMvcRequestBuilders.post("/currency/isoCode")
                        .content(asJsonString(currencyDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

}
