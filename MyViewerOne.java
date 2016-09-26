
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class MyView extends View implements View.OnClickListener{
    Context context;
    public MyView(Context context){
        super(context);
        this.context = context;
        initCircleView();
    }
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        defRadius = t.getInt(R.styleable.MyView_defRadius, defRadius);
        textPosX = t.getInt(R.styleable.MyView_textPosX, textPosX);
        textPosY = t.getInt(R.styleable.MyView_textPosY, textPosY);
        fontSize = t.getInt(R.styleable.MyView_fontSize, fontSize);
        paddingTop = t.getInt(R.styleable.MyView_paddingTop, paddingTop);
        paddingBottom = t.getInt(R.styleable.MyView_paddingBottom, paddingBottom);
        paddingLeft = t.getInt(R.styleable.MyView_paddingLeft, paddingLeft);
        paddingRight = t.getInt(R.styleable.MyView_paddingRight, paddingRight);
        textPosY += paddingTop;
        textPosX += paddingLeft;
        t.recycle();
        initCircleView();
    }
    public MyView(Context context, AttributeSet attrs, int def){
        super(context, attrs, def);
        this.context = context;
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyView, def, 0);
        defRadius = t.getInt(R.styleable.MyView_defRadius, defRadius);
        textPosX = t.getInt(R.styleable.MyView_textPosX, textPosX);
        textPosY = t.getInt(R.styleable.MyView_textPosY, textPosY);
        paddingTop = t.getInt(R.styleable.MyView_paddingTop, paddingTop);
        paddingBottom = t.getInt(R.styleable.MyView_paddingBottom, paddingBottom);
        paddingLeft = t.getInt(R.styleable.MyView_paddingLeft, paddingLeft);
        paddingRight = t.getInt(R.styleable.MyView_paddingRight, paddingRight);
        t.recycle();
        initCircleView();
    }
    private int defRadius;
    private int strokeWidth = 1, strokeColor = Color.rgb(0, 0, 0);
    private int paddingTop, paddingLeft, paddingRight, paddingBottom;
    public void onClick(View view){
        /*defRadius *= 1.2;
        fontSize++;
        textPosY++;
        adjustMinimumHeight();
        requestLayout();
        invalidate();*/
    }
    public void adjustMinimumHeight(){
        this.setMinimumHeight(defRadius * 2);
        this.setMinimumWidth(defRadius * 2);
    }
    public void initCircleView(){
        this.setMinimumHeight(defRadius * 2);
        this.setMinimumWidth(defRadius * 2);

        this.setOnClickListener(this);
        this.setClickable(true);

        this.setSaveEnabled(true);
        bound = new Rect();
        langType = LangType.ALPHABETIC;
    }
    private Paint getBrush(){
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStrokeWidth(strokeWidth);
        p.setColor(strokeColor);
        p.setStyle(Paint.Style.FILL);
        return p;
    }
    int fontSize = 24;
    Rect bound;
    int textPosX, textPosY;
    String text = "solvitur sexus. 5 Ita autem quoad vixerint, late palantur, ut alibi mulier nubat," +
            " in loco pariat alio liberosque procul educat nulla copia quiescendi permissa. " +
            "6 Victus universis caro ferina est lactisque abundans copia qua sustentantur, " +
            "et herbae multiplices et siquae alites capi per aucupium possint, et plerosque mos " +
            "vidimus frumenti usum et vini penitus ignorantes.\n" +
            "\n" +
            "7 Hactenus de natione perniciosa. Nunc ad textum propositum revertamur.\n" +
            "\n" +
            "Magnentianorum supplicia.\n" +
            "\n" +
            "[5] 1 Dum haec in oriente aguntur, Arelate hiemem agens Constantius post" +
            " theatralis ludos atque circenses ambitioso editos apparatu diem sextum idus" +
            " Octobres, qui imperii eius annum tricensimum terminabat, insolentiae pondera " +
            "gravius librans, siquid dubium deferebatur aut falsum, pro liquido accipiens et" +
            " conperto, inter alia excarnificatum Gerontium Magnentianae comitem partis exulari" +
            " maerore multavit. 2 ";
    public void setText(String text){

        this.text = text;
        postInvalidate();
    }
    public static enum LangType{
        ALPHABETIC, CHARACTER
    }
    LangType langType;
    public void setText(String text, LangType langType){
        this.langType = langType;
        setText(text);
    }
    int MAX_CHAR_IN_ONE_LINE = 200;
    private void printShortText(String text, Canvas canvas, Paint paint, int heightFactor){
        int end = text.indexOf('\n');
        while(end>=0){

            canvas.drawText(text.substring(0, end), textPosX, textPosY + heightFactor * drawYBase, paint);
            heightFactor++;
            text = text.substring(end+1);
            end = text.indexOf('\n');
        }
        canvas.drawText(text, textPosX, textPosY + heightFactor * drawYBase, paint);
    }
    public void setTextColor(int textStart, int textEnd, int color){

    }
    public void setTextColor(float startPos, float endPos, int color){

    }
    float drawYBase = fontSize * 1.2f;
    float baseWidth = 0;
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        baseWidth = this.getWidth() - 30;
        Paint paint = getBrush();
        paint.setTextSize(fontSize);
        String result = "";
        int heightFactor = 0;
        int curPos = 0;
        paint.setStrokeWidth(0.32f);
        if(text.length()>=MAX_CHAR_IN_ONE_LINE){
            paint.getTextBounds(text.substring(0, MAX_CHAR_IN_ONE_LINE), 0, MAX_CHAR_IN_ONE_LINE, bound);
        }else {
            paint.getTextBounds(text, 0, text.length(), bound);
        }
        int maxLines = this.getHeight()/bound.height();
        //canvas.drawText(""+maxLines, 100, 100, paint);
        if(bound.width()<baseWidth){
            printShortText(text, canvas, paint, heightFactor);

        }
        while(bound.width()>baseWidth){
            if(heightFactor>=maxLines)break;


            paint.getTextBounds(text.substring(0, curPos), 0, curPos, bound);
            curPos++;
            /*if(heightFactor==5){
                paint.setColor(Color.rgb(0, 200, 210));

            }else if(heightFactor==6){
                paint.setColor(Color.rgb(0, 0, 0));
            }*/
            // replace 10 with right and left padding
            if(bound.width()>=baseWidth){
                switch (langType){
                    case ALPHABETIC:
                        // we have incremented curPos once
                        curPos--;
                        // subtract 2 to deal with the problem when the char at curPos is space
                        result = text.substring(0, curPos-3);
                        result = result.substring(0, result.lastIndexOf(' '));
                        drawPadding(canvas, result, paint, heightFactor);


                        heightFactor++;
                        text = text.substring(result.length()+1);
                        curPos = 0;
                        break;
                    case CHARACTER:
                        // we have incremented curPos once
                        curPos--;

                        // a character is 3 char int Unicode?
                        result = text.substring(0, curPos - 3);
                        if(isNextNewLineInCharType){
                            canvas.drawText(result, textPosX-fontSize/2, textPosY + drawYBase * heightFactor, paint);
                            isNextNewLineInCharType = false;
                        }else{
                            canvas.drawText(result, textPosX, textPosY + drawYBase * heightFactor, paint);
                        }
                        heightFactor++;
                        text = text.substring(result.length());
                        curPos = 0;
                        break;
                }
            }else if(text.substring(0, curPos).indexOf('\n')>=0){
                // if there is a '\n', we don't need to pad the words
                canvas.drawText(text.substring(0, curPos), textPosX, textPosY + drawYBase * heightFactor, paint);
                text = text.substring(curPos);
                if(langType==LangType.CHARACTER){
                    isNextNewLineInCharType = true;
                }
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

            if(bound.width()<baseWidth){
                printShortText(text, canvas, paint, heightFactor);
            }
        }
        // the remained chars are invisible, so we don't display them
        if(text.length()<MAX_CHAR_IN_ONE_LINE){
            printShortText(text, canvas, paint, heightFactor);
        }else{
            canvas.drawText(text, textPosX, textPosY + heightFactor * drawYBase, paint);
        }
    }
    boolean isNextNewLineInCharType = false;
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
        float spaceWidth = (this.getWidth() - bound.width())/spaceCounts;
        if(spaceWidth<1.0f){
            canvas.drawText(text, textPosX, textPosY + heightFactor * drawYBase, paint);
            return;
        }
        float curXPos = textPosX;

        while(true){
            if(text.indexOf(' ')>0){
                String tmp = text.substring(0, text.indexOf(' '));

                canvas.drawText(tmp, curXPos, textPosY + heightFactor * drawYBase, paint);
                paint.getTextBounds(tmp, 0, tmp.length(), bound);
                curXPos += bound.width() + spaceWidth;
                text = text.substring(text.indexOf(' ') + 1);
            }else break;
        }
        canvas.drawText(text, curXPos, textPosY + heightFactor * drawYBase, paint);
    }
    protected int hGetMaximumHeight(){
        return defRadius * 2;
    }
    protected int hGetMaximumWidth(){
        return defRadius * 2;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        setMeasuredDimension(getImprovedDefaultWidth(widthMeasureSpec),
                getImprovedDefaultHeight(heightMeasureSpec));
    }
    private int getImprovedDefaultHeight(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED: return hGetMaximumHeight();
            case MeasureSpec.EXACTLY: return specSize;
            case MeasureSpec.AT_MOST: return hGetMinimumHeight();
        }
        return specSize;
    }
    private int getImprovedDefaultWidth(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode){
            case MeasureSpec.UNSPECIFIED: return hGetMaximumWidth();
            case MeasureSpec.EXACTLY: return specSize;
            case MeasureSpec.AT_MOST: return hGetMinimumWidth();
        }
        return specSize;
    }
    protected int hGetMinimumHeight(){
        return this.getSuggestedMinimumHeight();
    }
    protected int hGetMinimumWidth(){
        return this.getSuggestedMinimumWidth();
    }
}
