package com.progressSoft.taskProgressSoft.repository;

import com.progressSoft.taskProgressSoft.entity.Currency;
import com.progressSoft.taskProgressSoft.enums.IsoCodeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {

    Currency save(Currency currency);

    List<Currency> findAll();

    Currency findCurrencyByAmountAndFromIsoCodeAndToIsoCode(BigDecimal amount, IsoCodeEnum fromCode, IsoCodeEnum toCode);
}
