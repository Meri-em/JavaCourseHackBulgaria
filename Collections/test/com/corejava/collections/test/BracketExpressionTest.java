package com.corejava.collections.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.corejava.collections.BracketExpression;

public class BracketExpressionTest {

    @Test
    public void bracketExpressionTest(){
        BracketExpression expr1 = new BracketExpression("(");
        assertFalse(expr1.isCorrect());
        BracketExpression expr2 = new BracketExpression("()");
        assertTrue(expr2.isCorrect());
        BracketExpression expr3 = new BracketExpression("()()())))((())(");
        assertFalse(expr3.isCorrect());
        BracketExpression expr4 = new BracketExpression("()()(()())");
        assertTrue(expr4.isCorrect());
    }
}
