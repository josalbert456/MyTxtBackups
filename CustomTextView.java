    public static enum LangType{
        ALPHABETIC, CHARACTER
    }
    LangType langType;

    public void setText(String text){
        this.text = text;
        postInvalidate();
    }

    public void setText(String text, LangType langType){
        this.langType = langType;
        setText(text);
    }
    int MAX_CHAR_IN_ONE_LINE = 200;
    private void printShortText(String text, Canvas canvas, Paint paint, int heightFactor){
        int end = text.indexOf('\n');
        while(end>=0){
            canvas.drawText(text.substring(0, end), textPosX, textPosY + heightFactor * (fontSize+3), paint);
            heightFactor++;
            text = text.substring(end+1);
            end = text.indexOf('\n');
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = getBrush();
        paint.setTextSize(fontSize);
        String result = "";
        int heightFactor = 0;
        int curPos = 0;
        if(text.length()>=MAX_CHAR_IN_ONE_LINE){
            paint.getTextBounds(text.substring(0, MAX_CHAR_IN_ONE_LINE), 0, MAX_CHAR_IN_ONE_LINE, bound);
        }else {
            paint.getTextBounds(text, 0, text.length(), bound);
        }
        int maxLines = this.getWidth()/bound.height();
        //canvas.drawText(""+maxLines, 100, 100, paint);
        if(bound.width()<this.getWidth()){
            printShortText(text, canvas, paint, heightFactor);
        }
        while(bound.width()>this.getWidth()){
            if(heightFactor>=maxLines)break;/*{
                paint.setColor(Color.rgb(255, 255, 255));
                canvas.drawText(""+heightFactor, 100, 100, paint);
                paint.setColor(Color.rgb(0, 0, 0));
                return;
            }*/
            paint.getTextBounds(text.substring(0, curPos), 0, curPos, bound);
            curPos++;
            // replace 10 with right and left padding
            if(bound.width()>this.getWidth()-10){
                switch (langType){
                    case ALPHABETIC:
                        // we have incremented curPos once
                        curPos--;
                        // subtract 2 to deal with the problem when the char at curPos is space
                        result = text.substring(0, curPos-3);
                        result = result.substring(0, result.lastIndexOf(' '));
                        drawPadding(canvas, result, paint, heightFactor);
                        //canvas.drawText(result, textPosX, textPosY + (fontSize + 3) * heightFactor, paint);
                        heightFactor++;
                        text = text.substring(result.length()+1);
                        curPos = 0;
                        break;
                    case CHARACTER:
                        // we have incremented curPos once
                        curPos--;
                        if(text.substring(0, curPos-2).indexOf(' ')==0){
                            result = text.substring(0, curPos-3);
                        }else{
                            result = text.substring(0, curPos-2);
                        }
                        canvas.drawText(result, textPosX, textPosY + (fontSize + 3) * heightFactor, paint);
                        heightFactor++;
                        text = text.substring(result.length());
                        curPos = 0;
                        break;
                }
            }else if(text.substring(0, curPos).indexOf('\n')>=0){
                // if there is a '\n', we don't need to pad the words
                canvas.drawText(text.substring(0, curPos), textPosX, textPosY + (fontSize + 3) * heightFactor, paint);
                text = text.substring(curPos);

                curPos = 0;
                heightFactor++;
            }

            // this is where the performance stuff works,
            // we set a max_char_in_one_line field instead of calculate each time
            // the entire string, which for a very long string is a great improvement
            // in performance
            if(text.length()>=MAX_CHAR_IN_ONE_LINE){
                paint.getTextBounds(text.substring(0, MAX_CHAR_IN_ONE_LINE), 0, MAX_CHAR_IN_ONE_LINE, bound);
            }else {
                paint.getTextBounds(text, 0, text.length(), bound);
            }

            if(bound.width()<this.getWidth()){
                printShortText(text, canvas, paint, heightFactor);
            }
        }
        // the remained chars are invisible, so we don't display them
        //canvas.drawText(text, textPosX, textPosY + heightFactor * (fontSize + 3), paint);
    }
    private void drawPadding(Canvas canvas, String text, Paint paint, int heightFactor){
        Rect bound = new Rect();
        paint.getTextBounds(text, 0, text.length(), bound);
        String str = new String(text);
        int spaceCounts = 0;
        while(true){
            if(str.indexOf(' ')>=0){
                str = str.substring(str.indexOf(' ') + 1);
                spaceCounts++;
            }else{
                break;
            }
        }
        float spaceWidth = ((float)this.getWidth() - bound.width())/spaceCounts;
        float curXPos = textPosX;

        while(true){
            if(text.indexOf(' ')>0){
                String tmp = text.substring(0, text.indexOf(' '));

                canvas.drawText(tmp, curXPos, textPosY + heightFactor * (fontSize + 3), paint);
                paint.getTextBounds(tmp, 0, tmp.length(), bound);
                curXPos += bound.width() + spaceWidth;
                text = text.substring(text.indexOf(' ') + 1);
            }else break;
        }
        canvas.drawText(text, curXPos, textPosY + heightFactor * (fontSize + 3), paint);
    }
