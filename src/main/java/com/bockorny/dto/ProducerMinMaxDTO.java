package com.bockorny.dto;

import java.util.List;

public class ProducerMinMaxDTO {

    private final List<ProducerIntervalDTO> min, max;

    public ProducerMinMaxDTO(List<ProducerIntervalDTO> min, List<ProducerIntervalDTO> max) {
        this.min = min;
        this.max = max;
    }

    public List<ProducerIntervalDTO> getMin() {
        return min;
    }

    public List<ProducerIntervalDTO> getMax() {
        return max;
    }
}
