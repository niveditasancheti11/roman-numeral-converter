package com.adobe.roman_numeral_converter;

import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class RomanNumeralService {
    private final RomanNumeralRepository repository;

    public RomanNumeralService(RomanNumeralRepository repository) {
        this.repository = repository;
    }

    private static final TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number out of range (1-3999)");
        }
        int l = map.floorKey(number);
        if (number == l)
            return map.get(number);
        return map.get(l) + toRoman(number - l);
    }

    public RomanNumeralEntity getOrSaveRomanNumeral(int number) {
        return repository.findById(number).orElseGet(() -> {
            String roman = toRoman(number);
            RomanNumeralEntity entity = new RomanNumeralEntity(number, roman);
            repository.save(entity);
            return entity;
        });
    }
}
