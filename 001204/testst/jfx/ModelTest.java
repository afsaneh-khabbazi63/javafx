package jfx;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    private Model model;
    private Counter controller;
    private String inputText;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        controller = new Counter();
        inputText = "hi to all\n" +
                "i am .... ";
    }


    @Test(expected = IllegalArgumentException.class)
    public void setLineCount() {
        model.setLineCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDigitCount() {
        model.setDigitCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWordCount() {
        model.setWordCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCharCount() {
        model.setCharCount(-1);
    }

    @Test
    public void lineCount() {
        int count = controller.processLines(inputText,inputText.length());
        assertEquals(2,count);
    }

    @Test
    public void digitCount() {
        int count = controller.processDigits(inputText,inputText.length());
        assertEquals(0,count);
    }

    @Test
    public void wordCount() {
        int count = controller.processWords(inputText,inputText.length());
        assertEquals(5,count);
    }

}