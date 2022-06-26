package com.nanam.domain.bookDB.xmlParsing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


    @XmlRootElement(name = "root")
    @XmlAccessorType(XmlAccessType.FIELD)
    public class Root {

        @Getter
        @Setter
        @XmlElement(name = "paramData")
        private ParamData paramData;

        @Getter
        @Setter
        @XmlElement(name="result")
        private Result result;



    }
