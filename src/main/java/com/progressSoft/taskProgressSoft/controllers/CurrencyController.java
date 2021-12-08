package com.progressSoft.taskProgressSoft.controllers;

import com.progressSoft.taskProgressSoft.config.BundleResourceKey;
import com.progressSoft.taskProgressSoft.config.MessageBody;
import com.progressSoft.taskProgressSoft.dto.RequestCurrencyDto;
import com.progressSoft.taskProgressSoft.dto.ResponseCurrencyDto;
import com.progressSoft.taskProgressSoft.dto.fetchAllCurrency.ResponseFetchAllCurrencyDto;
import com.progressSoft.taskProgressSoft.service.CurrencyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements Controllable {

    @Autowired
    private CurrencyService currencyService;


    /**
     * isoCode
     *
     * @param request
     * @param requestDto
     * @return
     */

    @ApiOperation(value = "View a list of available groups", response = ResponseCurrencyDto.class, responseContainer = "String")
    @PostMapping("/isoCode")
    public ResponseEntity<MessageBody> isoCode(HttpServletRequest request, @Valid @RequestBody RequestCurrencyDto requestDto) {

        //get locale from request
        Locale locale = request.getLocale();

        ResponseCurrencyDto response = currencyService.isoCode(requestDto, locale);

        return responseMessage(BundleResourceKey.Ok, HttpStatus.OK, response, locale);
    }

    @ApiOperation(value = "View a list of available groups", response = ResponseFetchAllCurrencyDto.class, responseContainer = "List")
    @GetMapping("/fetchAll")
    public ResponseEntity<MessageBody> fetchAll(HttpServletRequest request) {

        Locale locale = request.getLocale();

        List<ResponseFetchAllCurrencyDto> response = currencyService.fetchAll(locale);

        return responseMessage(BundleResourceKey.Ok, HttpStatus.OK, response, locale);
    }
}
