package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testCalculateFineNoLateFee() {
        assertEquals(0.0, App.calculateFine(3, 5));
        assertEquals(0.0, App.calculateFine(1, 7));
    }

    @Test
    public void testCalculateFineWithLateFee() {
        assertEquals(2.0, App.calculateFine(1, 9)); // 2 late days
        assertEquals(6.0, App.calculateFine(3, 9)); // 3 books * 2 days
        assertEquals(10.0, App.calculateFine(2, 12)); // 2*5=10
    }
}
