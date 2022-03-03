package jfx;

import java.util.LinkedList;
import java.util.List;


public class Model {

    private int lineCount;
    private int digitCount;
    private int wordCount;
    private int charCount;

    private List<Listener> listeners = new LinkedList<>();


    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void notifyObservers(){
        listeners.stream().forEach(l->l.onChange(this));
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        if(lineCount < 0){
            throw new IllegalArgumentException("line count must be greater than zero");
        }
        this.lineCount = lineCount;
    }

    public int getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(int digitCount) {
        if(digitCount < 0){
            throw new IllegalArgumentException("digit count must be greater than zero");
        }
        this.digitCount = digitCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        if(wordCount < 0){
            throw new IllegalArgumentException("word count must be greater than zero");
        }
        this.wordCount = wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public void setCharCount(int charCount) {
        if(charCount < 0){
            throw new IllegalArgumentException("char count must be greater than zero");
        }
        this.charCount = charCount;
    }

}
