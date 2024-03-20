package com.kien.smalltest.infras.handle;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kienvt3
 */
@Data
public class BadRequestException extends RuntimeException {

    private String message;

    public BadRequestException(String message) {
        this.message = message;
    }

}
