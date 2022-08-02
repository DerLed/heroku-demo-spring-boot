package com.example.herokudemospringboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Valute {
    private String numCode;
    private String charCode;
    private BigDecimal nominal;
    private String name;
    private BigDecimal value;
}
