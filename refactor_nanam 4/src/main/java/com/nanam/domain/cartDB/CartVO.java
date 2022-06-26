package com.nanam.domain.cartDB;

import com.nanam.domain.bookDB.BookVO;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class CartVO extends BookVO{

    protected long id;
    protected String userId;
    protected long cartOfVolume;
    protected long cartOfTotalVolume;
    protected String cartBookTitle;
    protected String cartBookIsbn;
    protected Date dateOfCart;
    protected long cartPrice;
    protected long cartTotalPrice;
    protected String useYnOfService;
    protected long cartSeq;
    protected String cartDecide;


}
