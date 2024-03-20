package com.kien.smalltest.infras.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kienvt
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private boolean success;
    private int total;
    private int page;
    private int limit;
    private T data;

}
