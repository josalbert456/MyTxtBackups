@Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = getBrush();
        paint.setTextSize(fontSize);
        String result = "";
        int heightFactor = 0;
        int curPos = 0;
        paint.getTextBounds(text, 0, text.length(), bound);
        while(bound.width()>this.getWidth()){
            paint.getTextBounds(text.substring(0, curPos), 0, curPos, bound);
            curPos++;
            // replace 10 with right and left padding
            if(bound.width()>this.getWidth()-10){
                switch (langType){
                    case ALPHABETIC:
                        // we have incremented curPos once
                        curPos--;
                        // subtract 2 to deal with the problem when the char at curPos is space
                        result = text.substring(0, curPos-2);
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
            paint.getTextBounds(text, 0, text.length(), bound);
        }
        canvas.drawText(text, textPosX, textPosY + heightFactor * (fontSize + 3), paint);
    }
    // a raw padding function
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
