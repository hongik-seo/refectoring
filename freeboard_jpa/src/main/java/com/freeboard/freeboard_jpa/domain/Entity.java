package com.freeboard.freeboard_jpa.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Table(name="freeboard")
@SequenceGenerator(
        name = "numSeq",
        sequenceName = "NUM",
        initialValue = 1,
        allocationSize = 1)
public class Entity {

    @Id
    @NotNull
    @GeneratedValue(strategy =GenerationType.SEQUENCE,
            generator = "numSeq")
    private Long num;
    private String codeType;
    private String name;
    private String title;
    private String content;
    private LocalDateTime regdate;

}
