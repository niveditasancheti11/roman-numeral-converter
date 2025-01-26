package com.adobe.roman_numeral_converter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RomanNumeralRepository extends JpaRepository<RomanNumeralEntity, Integer> {
}
