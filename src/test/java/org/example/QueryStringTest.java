package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {

    @Test
    void createTest(){
        QueryString queryString = new QueryString("operand1", "11");
        assertThat(queryString).isNotNull();
    }
}
