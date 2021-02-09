package ru.timofeev.home.matchingexcel.helper;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionHelperTest {

    @Test
    void substringWithZero() {
        var description = "Шины greckster 0";

        var result = DescriptionHelper.substringWithZero(description);

        assertEquals("Шины greckster", result);
    }

    @Test
    void subStringWithQuantityWithQuantity() {
        var description = "Шины Lonmadi 41";
        var quantity = Optional.of("41");

        var result = DescriptionHelper.subStringWithQuantity(description, quantity);

        assertEquals("Шины Lonmadi", result);
    }

    @Test
    void subStringWithQuantityWithoutQuantity() {
        var description = "Шины Lonmadi R 22";
        var quantity = Optional.of("52");

        var result = DescriptionHelper.subStringWithQuantity(description, quantity);

        assertEquals("Шины Lonmadi R 22", result);
    }

    @Test
    void subStringWithQuantityWithDash(){
        var description = "Шины Lonmadi R-22";
        var quantity = Optional.of("22");

        var result = DescriptionHelper.subStringWithQuantity(description, quantity);

        assertEquals("Шины Lonmadi R-22", result);
    }
}