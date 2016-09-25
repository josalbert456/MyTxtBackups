// because the space stuffs, this method does not apply to some languages
String result = "";
int heightFactor = 0;
int curPos = 0;
paint.getTextBounds(text, 0, text.length(), bound);
int viewWidth = this.getWidth();
while(bound.width()>viewWidth){
    paint.getTextBounds(text.substring(0, curPos), 0, curPos, bound);
    curPos++;
    if(bound.width()>viewWidth){
        // we have incremented curPos once
        curPos--;
        // subtract 2 to deal with the problem when the char at curPos is space
        result = text.substring(0, curPos-2);
        result = result.substring(0, result.lastIndexOf(' '));
        canvas.drawText(result, textPosX, textPosY + fontSize * heightFactor, paint);
        heightFactor++;
        text = text.substring(result.length()+1);
        curPos = 0;
    }else if(text.substring(0, curPos).indexOf('\n')>=0){
        canvas.drawText(text.substring(0, curPos), textPosX, textPosY+ fontSize * heightFactor, paint);
        text = text.substring(curPos);

        curPos = 0;
        heightFactor++;
    }
    paint.getTextBounds(text, 0, text.length(), bound);
}
canvas.drawText(text, textPosX, textPosY + heightFactor * fontSize, paint);
