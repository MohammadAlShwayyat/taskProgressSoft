package com.progressSoft.taskProgressSoft.config.swagger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  SwaggerTags {

    LOOKUP("Fill one of these in group field"),
    Menu("Fill one of these in group field");

    private final String description;
}
