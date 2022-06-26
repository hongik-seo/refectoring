package com.nanam.domain.boardDB;


import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class BoardVO {


    private String id;

    private String userId;

    private String boardSubject;

    private String boardContent;

    private String boardList;

    private String boardSeq;

    private String insertDate;

    private String updateDate;

    private String deleteDate;

    private String rn;


}
