package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CounterDTO {
    private final LocalDateTime date;
    private final int value;

    public CounterDTO(LocalDateTime date, int counter)
    {
        this.date = date;
        this.value = counter;
    }

    public int getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
