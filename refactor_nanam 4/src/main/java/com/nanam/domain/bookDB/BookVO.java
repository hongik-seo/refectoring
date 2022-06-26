package com.nanam.domain.bookDB;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nanam.domain.bookDB.xmlParsing.Item;
import com.nanam.domain.cartDB.CartVO;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.format.SignStyle;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class BookVO {


    private String adminId;
    private String userId;
    private String bookTitleInfo;
    private String bookTypeName;
    private String bookAuthorInfo;
    private String bookPubInfo;
    private String bookPubYearInfo;
    private String bookIsbn;
    private Integer pageNum;
    private Integer pageSize;
    private String bookList;
    private String bookPrice;
    private String bookContent;
    private String bookIndex;
    private String bookAuthorIntro;
    protected String bookImg;
    private String insertDate;
    private String updateDate;
    private String deleteDate;
    private String rn;
    private String bookSeq;
    private String flag;
    private long seq;




    /*public BookVO(String author_info, String isbn, String pub_info, String pub_year_info, String pub_title_info) {

        this.author_info= author_info;
        this.isbn = isbn;
        this.pub_info = pub_info;
        this.pub_year_info = pub_year_info;
        this.title_info = pub_title_info;
    }*/
}
