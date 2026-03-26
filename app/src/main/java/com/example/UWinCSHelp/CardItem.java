package com.example.UWinCSHelp;

public class CardItem {

    String text;
    String buttonText;
    int image;

    public CardItem(String text, String buttonText, int image) {
        this.buttonText = buttonText;
        this.image = image;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getImage() {
        return image;
    }
}
