package com.nanam.domain.bookDB.xmlParsing;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@XmlRootElement(name = "paramData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParamData {

    @Setter
    @XmlElement(name = "kwd")
    private String kwd;

    @Setter
    @XmlElement(name = "category")
    private String category;

    @Setter
    @XmlElement(name = "pageNum")
    private Integer pageNum;

    @Setter
    @XmlElement(name = "pageSize")
    private Integer pageSize;


    @Setter
    @XmlElement(name = "total")
    private Integer total;



}
