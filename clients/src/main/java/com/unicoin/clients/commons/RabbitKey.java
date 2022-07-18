package com.unicoin.clients.commons;

public class RabbitKey {

    //routing key
    public static final String EXPORT_ORDER_ROUTING_KEYS = "export-order-routing-key";
    public static final String IMPORT_ORDER_ROUTING_KEYS = "import-order-routing-key";

    public static  final String DIRECT_EXCHANGE = "direct-exchange";


    //queue
    public static final String QUEUE_ADD_IMPORT_ORDER = "order-queue-import-order";
    public static final String QUEUE_ADD_EXPORT_ORDER = "order-queue-export-order";
    public static final String QUEUE_ADD_RETURN_ORDER = "order-queue-return-order";
}
