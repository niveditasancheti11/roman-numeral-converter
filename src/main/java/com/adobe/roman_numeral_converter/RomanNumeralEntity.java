package com.adobe.roman_numeral_converter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RomanNumeralEntity {
    @Id
    private int input;
    private String output;
}
