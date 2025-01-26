package com.adobe.roman_numeral_converter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RomanNumeralController {
    private final RomanNumeralService service;

    public RomanNumeralController(RomanNumeralService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Roman Numeral Converter API! Use /romannumeral/{number} to convert integers.";
    }

    @GetMapping("/romannumeral/{number}")
    public RomanNumeralEntity getRomanNumeral(@PathVariable int number) {
        return service.getOrSaveRomanNumeral(number);
    }

}
