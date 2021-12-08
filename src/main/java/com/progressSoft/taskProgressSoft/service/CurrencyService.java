package com.progressSoft.taskProgressSoft.service;

import com.progressSoft.taskProgressSoft.config.BundleResourceKey;
import com.progressSoft.taskProgressSoft.dto.RequestCurrencyDto;
import com.progressSoft.taskProgressSoft.dto.ResponseCurrencyDto;
import com.progressSoft.taskProgressSoft.dto.fetchAllCurrency.ResponseFetchAllCurrencyDto;
import com.progressSoft.taskProgressSoft.entity.Currency;
import com.progressSoft.taskProgressSoft.enums.IsoCodeEnum;
import com.progressSoft.taskProgressSoft.exception.BusinessException;
import com.progressSoft.taskProgressSoft.repository.CurrencyRepo;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Log4j2
@Transactional(rollbackFor = BusinessException.class, propagation = Propagation.NOT_SUPPORTED)
public class CurrencyService {

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * this service to get iso code from requestDto and amount
     * and save on DB
     *
     * @param locale
     * @param requestDto
     * @return ResponseCurrencyDto (result)
     */
    //    @Transactional(rollbackFor = BusinessException.class, propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseCurrencyDto isoCode(RequestCurrencyDto requestDto, Locale locale) {

        log.info("Begin of Convert Iso Code service In CurrencyService Class with request " + requestDto.toString());

//        Currency currency = Currency.getInstance(requestDto.getFromIsoCode());

        if (IsoCodeEnum.findByCode(requestDto.getFromIsoCode()) == null || IsoCodeEnum.findByCode(requestDto.getToIsoCode()) == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, BundleResourceKey.Not_Found_Iso_Code, locale);
        }

        BigDecimal result = null;

        //get amount from request
        BigDecimal amount = requestDto.getAmount();

        Currency findOldCurrency = currencyRepo.findCurrencyByAmountAndFromIsoCodeAndToIsoCode
                (amount, IsoCodeEnum.valueOf(requestDto.getFromIsoCode()), IsoCodeEnum.valueOf(requestDto.getToIsoCode()));

        if (findOldCurrency != null) {

            result = calculateAmount(amount, requestDto.getFromIsoCode(), requestDto.getToIsoCode());

            return new ResponseCurrencyDto(result.toString());
        }


        if (amount.compareTo(new BigDecimal("0.00")) == 0) {
            log.error("error of CurrencyService Class For Amount Zero " + amount);
            throw new BusinessException(HttpStatus.BAD_REQUEST, BundleResourceKey.Bad_Request_Amount, locale);
        }

        if ((requestDto.getFromIsoCode().equals(IsoCodeEnum.USD.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.JOD.getCode())) ||
                requestDto.getFromIsoCode().equals(IsoCodeEnum.JOD.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.USD.getCode())) {

            result = calculateAmount(amount, requestDto.getFromIsoCode(), requestDto.getToIsoCode());

            if (result == null) {
                throw new BusinessException(HttpStatus.NOT_FOUND, BundleResourceKey.Not_Found, locale);
            }

        }

        if ((requestDto.getFromIsoCode().equals(IsoCodeEnum.USD.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.EUR.getCode())) ||
                requestDto.getFromIsoCode().equals(IsoCodeEnum.EUR.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.USD.getCode())) {

            result = calculateAmount(amount, requestDto.getFromIsoCode(), requestDto.getToIsoCode());

            if (result == null) {
                throw new BusinessException(HttpStatus.NOT_FOUND, BundleResourceKey.Not_Found, locale);
            }

        }

        if ((requestDto.getFromIsoCode().equals(IsoCodeEnum.QAR.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.SAR.getCode())) ||
                requestDto.getFromIsoCode().equals(IsoCodeEnum.SAR.getCode()) && requestDto.getToIsoCode().equals(IsoCodeEnum.QAR.getCode())) {

            result = calculateAmount(amount, requestDto.getFromIsoCode(), requestDto.getToIsoCode());

            if (result == null) {
                throw new BusinessException(HttpStatus.NOT_FOUND, BundleResourceKey.Not_Found, locale);
            }

        }

        Currency currency = new Currency();

        modelMapper.map(requestDto, currency);

        currencyRepo.save(currency);

        log.info("End of Convert Iso Code service In CurrencyService Class with response " + result.toString());

        return new ResponseCurrencyDto(result.toString());

    }

    public BigDecimal calculateAmount(BigDecimal amount, String fromCode, String toCode) {

        BigDecimal result = null;

        switch (fromCode + toCode) {

            case "JODUSD":
            case "USDJOD":
                result = amount.multiply(BigDecimal.valueOf(0.709));
                break;

            case "USDEUR":
            case "EURUSD":
                result = amount.multiply(BigDecimal.valueOf(0.89));
                break;

            case "QARSAR":
            case "SARQAR":
                result = amount.multiply(BigDecimal.valueOf(1.03));
                break;

        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ResponseFetchAllCurrencyDto> fetchAll(Locale locale) {

        List<Currency> currencies = currencyRepo.findAll();

        List<ResponseFetchAllCurrencyDto> currencyDtos = new ArrayList<>();

        if (currencies != null) {
            currencies.stream().forEach(currency -> {

                ResponseFetchAllCurrencyDto dto = modelMapper.map(currency, ResponseFetchAllCurrencyDto.class);
                currencyDtos.add(dto);

            });
        }

        return currencyDtos;
    }


}
