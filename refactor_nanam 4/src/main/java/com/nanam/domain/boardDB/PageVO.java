package com.nanam.domain.boardDB;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Accessors(chain = true)
public class PageVO {

    private int realEnd;

    private int startPage;

    private int endPage;

    private boolean prev, next;

    private int total;

    private int pageNum;

    private int amount;

    private int currentPage;

    public PageVO(int pageNum,int amount,int total){

        this.pageNum=pageNum;

        this.amount=amount;

        this.total=total;

        this.endPage= (int)(Math.ceil(pageNum/10.0))*10; //10

        this.startPage=this.endPage-9;//1

        this.currentPage=pageNum;

        this.realEnd=(int)(Math.ceil((total*1.0)/amount));


        if(this.endPage>this.realEnd){

            this.endPage=this.realEnd;

        }

        this.prev=this.startPage>1;

        this.next=this.endPage<this.realEnd;
    }


}
