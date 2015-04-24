package com.corejava.files;

public class WordCountResult {
    private int lineCount;
    private int wordCount;
    private int characterCount;
    
    public WordCountResult(int lineCount, int wordCount, int characterCount){
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.characterCount = characterCount;
    }
    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

}
