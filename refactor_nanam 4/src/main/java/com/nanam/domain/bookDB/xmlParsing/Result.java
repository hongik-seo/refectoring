package com.nanam.domain.bookDB.xmlParsing;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    @XmlElement(name = "item")
    private List<Item> itemList;


}
