package ru.netology.service;

import static org.junit.Assert.*;

public class CashbackHackServiceTest {

    @org.junit.Test
    public void testRemain() {
        CashbackHackService service = new CashbackHackService ();
        int expected = 100;
        int result = service.remain (900);
        System.out.println (result);

        assertEquals (result, expected);
    }

    @org.junit.Test
    public void secTestRemain() {
        CashbackHackService service = new CashbackHackService ();
        int expected = 100;
        int result = service.remain (1500);
        System.out.println (result);

        assertEquals (result, expected);
    }
}