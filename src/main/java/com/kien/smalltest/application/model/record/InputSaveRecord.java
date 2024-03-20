package com.kien.smalltest.application.model.record;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kienvt
 */
@Getter
@Setter
public class InputSaveRecord {

    @NotEmpty(message = "field.not.empty")
    private String title;

    @NotEmpty(message = "field.not.empty")
    private String content;

}
