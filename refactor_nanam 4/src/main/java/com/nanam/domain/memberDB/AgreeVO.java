package com.nanam.domain.memberDB;

import lombok.*;
import lombok.experimental.Accessors;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class AgreeVO {

    private long id; //primary key
    private List<String> agreeYnList;//λμμ¬λΆ
    private Date agreeDate;

}
