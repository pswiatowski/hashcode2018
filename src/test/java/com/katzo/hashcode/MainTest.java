package com.katzo.hashcode;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pswiatowski on 2/20/18.
 */
public class MainTest {

    @Test
    public void test1() throws Exception {
        Assertions.assertThat(new Main().test()).isTrue();
    }

}