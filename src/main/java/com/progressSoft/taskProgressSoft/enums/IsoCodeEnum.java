package com.progressSoft.taskProgressSoft.enums;

import lombok.Getter;

@Getter
public enum IsoCodeEnum {

    USD("USD"),
    QAR("QAR"),
    SAR("SAR"),
    JOD("JOD"),
    EUR("EUR");

    private String code;

    IsoCodeEnum(String code) {
        this.code = code;
    }

    public static IsoCodeEnum findByCode(String code) {
        for (IsoCodeEnum e : IsoCodeEnum.values()) {
            if (e.getCode().equals(code))
                return e;
        }

        return null;
    }
}
