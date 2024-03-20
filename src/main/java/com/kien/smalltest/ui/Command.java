package com.kien.smalltest.ui;

import lombok.SneakyThrows;

public interface Command<I, O, U> {

    void validate(I request);

    O process(I request, U principal);

    @SneakyThrows
    default O execute(I request, U principal) {
        validate(request);
        return process(request, principal);
    }
}
