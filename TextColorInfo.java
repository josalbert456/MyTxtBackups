package com.example.root.mytextviewone;

import android.graphics.Color;

public class TextColorInfo {
    int textColorStart = 0;
    int textColorLength = 0;
    public boolean colorSetFinishedFlag = false;
    public boolean colorInfoAssignFlag = false;
    int textColor = Color.rgb(0, 0, 0);
    public TextColorInfo(int textColorStart, int textColorLength, int textColor){
        this.textColor = textColor;
        this.textColorLength = textColorLength;
        this.textColorStart = textColorStart;
    }
}
