//package com.example.demo;

import static java.time.Instant.EPOCH;
import static java.time.Instant.MAX;
import static java.time.Instant.MIN;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class InstantT {

    public static void main(String[] args) throws InterruptedException {
        // 获取当前时间戳
        Instant timestamp = Instant.now();
        long ts = timestamp.toEpochMilli();

        // ts 转为 Instant对象
        long ts2 = 1584700633000L;
        Instant instant = Instant.ofEpochMilli(ts2);
        // instant 可以转化为LocalDateTime 表示当地的时间
        LocalDateTime ldt3 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(ldt3);

        // LocalDateTime 可以进一步转化为ZonedDateTime，加上了时区的信息
        ZonedDateTime zonedDateTime = ZonedDateTime.of(ldt3, ZoneId.systemDefault());
        // ZonedDateTime 又可以转换从Instant
        Instant l = zonedDateTime.toInstant();
        System.out.println(l);
        boolean equals = instant.equals(l);
        System.out.println(equals);

        Instant epoch = EPOCH;
        System.out.println(epoch);
        System.out.println(MIN); //-1000000000-01-01T00:00:00Z
        System.out.println(MAX); //+1000000000-12-31T23:59:59.999999999Z

        // 一小时以后
        Instant oneHourLater = timestamp.plus(1, ChronoUnit.HOURS);
        LocalDateTime ldt = LocalDateTime.ofInstant(oneHourLater, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());

        // 两天前
        Instant twoDaysAgo = timestamp.minus(2, ChronoUnit.DAYS);
        LocalDateTime ldt2 = LocalDateTime.ofInstant(twoDaysAgo, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt2.getMonth(), ldt2.getDayOfMonth(),
                ldt2.getYear(), ldt2.getHour(), ldt2.getMinute());


        Instant instant1 = Instant.now();
        Thread.sleep(1099);
        Instant instant2 = instant.now();

        System.out.println(instant2.getEpochSecond());
        System.out.println(instant2.getEpochSecond()- instant1.getEpochSecond());
        System.out.println(instant2.getNano());
        System.out.println(instant1.getNano());

    }

}