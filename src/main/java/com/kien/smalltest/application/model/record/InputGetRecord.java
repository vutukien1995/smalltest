package com.kien.smalltest.application.model.record;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kienvt
 */
@Getter
@Setter
public class InputGetRecord {

    @Min(value = 0, message = "field.size.min.0")
    @NotNull(message = "field.not.null")
    private Integer page;

    @Min(value = 1, message = "field.size.min.1")
    @NotNull(message = "field.not.null")
    private Integer limit;

}
