package com.myapp.service;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LotTest {
    private Lot lot;

    @Before
    public void setUp() throws Exception {
        lot = new Lot();
    }

    @Test
    public void test_cast() throws Exception {
        int cast1 = lot.cast();
        int cast2 = lot.cast();

        assertThat(cast1, is(not(equalTo(cast2))));
        assertThat(lot.getDequeue().contains(cast1), equalTo(true));
        assertThat(lot.getDequeue().contains(cast2), equalTo(true));
    }
}