package com.progressSoft.taskProgressSoft.entity;


import com.progressSoft.taskProgressSoft.enums.IsoCodeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Currency")
@Getter
@Setter
public class Currency {


    @GenericGenerator(
            name = "Currency_Seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "CURRENCY_SEQ", value = "Currency_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "Currency_Seq")
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FromIsoCode")
    private IsoCodeEnum fromIsoCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "ToIsoCode")
    private IsoCodeEnum toIsoCode;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "DealTime")
    private LocalDateTime dealTime;


}
