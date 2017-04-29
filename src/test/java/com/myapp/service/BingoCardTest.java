package com.myapp.service;

import com.myapp.domain.Cell;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BingoCardTest {
    private BingoCard bingoCard;

    @Before
    public void setUp() throws Exception {
        bingoCard = new BingoCard();
    }

    @Test
    public void test_constructor() throws Exception {
        List<Integer> fieldNumList = bingoCard.getCells().stream()
                .map(cell -> cell.getFieldNum()).distinct().collect(toList());

        assertThat(fieldNumList.size(), equalTo(25));
    }

    @Test
    public void test_isExist_returnsTrue() throws Exception {
        boolean exist1 = bingoCard.isExist(0, 0);
        boolean exist2 = bingoCard.isExist(4, 4);

        assertThat(exist1, is(true));
        assertThat(exist2, is(true));
    }

    @Test
    public void test_isExist_returnsFalse() throws Exception {
        boolean exist1 = bingoCard.isExist(-1, 0);
        boolean exist2 = bingoCard.isExist(1, 5);

        assertThat(exist1, is(false));
        assertThat(exist2, is(false));
    }

    @Test
    public void test_getCell_returnsCell_onSuccess() throws Exception {
        Cell cell1 = bingoCard.getCell(2, 3).get();
        Cell cell2 = bingoCard.getCell(4, 0).get();

        assertThat(cell1.getVertical(), equalTo(2));
        assertThat(cell1.getHorizontal(), equalTo(3));
        assertThat(cell2.getVertical(), equalTo(4));
        assertThat(cell2.getHorizontal(), equalTo(0));
    }

    @Test
    public void test_getCell_returnsEmpty_onFailure() throws Exception {
        Optional<Cell> maybeCell1 = bingoCard.getCell(-1, 3);
        Optional<Cell> maybeCell2 = bingoCard.getCell(0, 5);

        assertThat(maybeCell1.isPresent(), is(false));
        assertThat(maybeCell2.isPresent(), is(false));
    }
}