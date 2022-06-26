package com.nanam.domain.orderAndPayDB;

import com.nanam.domain.cartDB.CartVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderAndPayVO extends CartVO {

    private long oId;
    private	String orderId;
    private String orderName;
    private	String orderPhone;
    private	String orderZipcode;
    private	String orderAddress;
    private	String orderDetailAddress;
    private Date dateOfOrder;
    private String orderCall;
    private	String oYn;


}
