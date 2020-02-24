package com.investasikita.test.kafka.constants;

public interface IKafkaConstants {

    public static String KAFKA_BROKERS = "192.168.2.100:9092";

    public static Integer MESSAGE_COUNT = 1000;

    public static String CLIENT_ID = "cr7";

    public static String TOPIC_NAME = "smstoken";

    public static String GROUP_ID_CONFIG = "qa-smstoken";

    public static Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;

    public static String OFFSET_RESET_LATEST = "latest";

    public static String OFFSET_RESET_EARLIER = "earliest";

    public static Integer MAX_POLL_RECORDS = 1;
    
}
