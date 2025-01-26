package com.adobe.roman_numeral_converter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/romannumeral")
public class RomanNumeralController {
    private final RomanNumeralService service;

    public RomanNumeralController(RomanNumeralService service) {
        this.service = service;
    }

    @GetMapping("/{number}")
    public RomanNumeralEntity getRomanNumeral(@PathVariable int number) {
        return service.getOrSaveRomanNumeral(number);
    }

}
