package com.qris_payment.api.common;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZoneDateTimeHelper {

    private String zoneId;
    private String pattern;



    private ZoneDateTimeHelper(Builder builder) {
        this.zoneId = builder.zoneId;
        this.pattern = builder.pattern;
    }

    public static class Builder {
        private String zoneId;
        private String pattern;

        public Builder setZoneId(String zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        public Builder setPattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public ZoneDateTimeHelper build() {
            return new ZoneDateTimeHelper(this);
        }
    }

    public ZonedDateTime getZoneDateTime() {
        ZoneId zoneIdOf = ZoneId.of(zoneId);
        return ZonedDateTime.now(zoneIdOf);
    }

    public String getZoneDateTimeStr() {
        DateTimeFormatter xTimeStampFormatter = DateTimeFormatter.ofPattern(pattern);
        return getZoneDateTime().format(xTimeStampFormatter);
    }

    public long toEpochMilli(){
        return getZoneDateTime().toInstant().toEpochMilli();
    }

    public String toEpochMilliStr(){
        return String.valueOf(getZoneDateTime().toInstant().toEpochMilli());
    }

    public Timestamp toTimeStamp(){
        return new Timestamp(toEpochMilli());
    }

}
