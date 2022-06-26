package com.freeboard.freeboard_jpa.dto;
import lombok.*;
import lombok.experimental.Accessors;


import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
public class DTO {

    private Long num;

    private String codeType;

    private String name;

    private String title;

    private String content;

    private LocalDateTime regdate;

}
