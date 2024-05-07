package com.webapp.pgadmiss.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {

    private String resultCode;

    private String resultMsg;

    private String apiPath;

    private LocalDateTime resultTime;

}
