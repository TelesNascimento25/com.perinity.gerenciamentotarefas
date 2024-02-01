package org.perinity.model.DTO;

import java.time.LocalDate;

public class LocalDateParam {
    private LocalDate date;

    private LocalDateParam(LocalDate date) {
        this.date = date;
    }

    public static LocalDateParam fromString(String dateStr) {
        return new LocalDateParam(LocalDate.parse(dateStr));
    }

    public LocalDate getDate() {
        return date;
    }
}
