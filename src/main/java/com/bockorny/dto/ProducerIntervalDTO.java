package com.bockorny.dto;

public class ProducerIntervalDTO {
    private final String producer;
    private final int interval, previousWin, followingWin;

    public ProducerIntervalDTO(final String producer, final int interval, final int previousWin, final int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public int getInterval() {
        return interval;
    }

    public int getPreviousWin() {
        return previousWin;
    }

    public int getFollowingWin() {
        return followingWin;
    }
}
