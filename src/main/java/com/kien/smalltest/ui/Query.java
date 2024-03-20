package com.kien.smalltest.ui;

import lombok.SneakyThrows;

public interface Query<I, O>  {

    void validate(I request);

    O process(I request);

    @SneakyThrows
    default O execute(I request) {
        validate(request);
        return process(request);
    }

}
