package com.unicoin.order.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "import_orders")
public class ImportOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMPORT_ORDER_ID", nullable = false)
    private Long id;

    @Column(name = "USER_PHONE_NUMBER" , nullable = false )
    private String userPhoneNumber;

    @Column(name = "REGIST_STAMP" , nullable = false)
    private Timestamp registStamp;

    @Column(name = "STATUS" , nullable = false)
    private Integer status;
}
