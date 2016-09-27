package com.example.root.mytextviewone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.root.mytextviewone.R;

public class MyViewTwo extends View implements View.OnClickListener{
    Context context;
    public MyViewTwo(Context context){
        super(context);
        this.context = context;
        initCircleView();
    }
    public MyViewTwo(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        initCircleView(t);
    }
    public MyViewTwo(Context context, AttributeSet attrs, int def){
        super(context, attrs, def);
        this.context = context;
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyView, def, 0);
        initCircleView(t);
    }
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
    public void initCircleView(TypedArray t){

        defRadius = t.getInt(R.styleable.MyView_defRadius, defRadius);
        textPosX = t.getInt(R.styleable.MyView_textPosX, textPosX);
        textPosY = t.getInt(R.styleable.MyView_textPosY, textPosY);
        paddingTop = t.getInt(R.styleable.MyView_paddingTop, paddingTop);
        paddingBottom = t.getInt(R.styleable.MyView_paddingBottom, paddingBottom);
        paddingLeft = t.getInt(R.styleable.MyView_paddingLeft, paddingLeft);
        paddingRight = t.getInt(R.styleable.MyView_paddingRight, paddingRight);
        t.recycle();

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
    Rect bound;
    String text = " Agrorum qualitates sunt tres: una agri divisi et adsignati, altera mensura per extremitatem conprehensi, tertia arcifini, qui nulla mensura continetur. Ager ergo divisus adsignatus est coloniarum. Hic habet condiciones duas: unam qua plerumque limitibus continetur, alteram qua per proximos possessionum rigores adsignatum est, sicut in Campania Suessae A<u>runcae. Quidquid autem secundum hanc condicionem in longitudinem est delimitatum, per strigas appellatur; quidquid per <la>titudinem, per scamna. Ager ergo limitatus hac similitudine decimanis et cardinibus continetur. Ager per strigas et per scamna divisus et adsignatus est more antiquo in hanc similitudinem, qua in provinciis arva publica coluntur. Ager est mensura conprehensus, cuius modus universus civitati est adsignatus, sicut in Lusitania Salma<n>ticensibus aut Hispania citeriore Pala<n>tinis et in conpluribus provinciis tributarium solum per universitatem populis est definitum. Eadem ratione et privatorum agrorum mensurae aguntur. Hunc agrum multis locis mensores, quamvis extremum mensura conprehenderint, in formam in modum limit<ati> condiderunt.\n" +
            "\n" +
            "Ager est arcifinius, qui nulla mensura continetur. Finitur secundum antiquam observationem fluminibus, fossis, montibus, viis, arboribus ante missis, aquarum divergiis et si qua loca ante a possessore potuerunt optineri. Nam ager arcifinius, sicut ait Varro, ab arcendis hostibus est appellatus: qui postea interventu litium per ea loca quibus finit terminos accipere coepit. In his agris nullum ius subsicivorum intervenit. Subsicivum est, quod a subsecante linea nomen accepit [subsicivum]. Subsicivorum genera sunt duo: unum quod in extremis adsignatorum agrorum finibus centuria expleri non potuit; aliud genus subsicivorum, quod in mediis adsignationibus et integris centuriis intervenit. Quidquid enim inter IIII limites minus quam intra clusum est fuerit adsignatum, in hac remanet appellatione, ideo quod is modus, qui adsignationi superest, linea cludatur et subsecetur. Nam et reliquarum mensurarum actu quidquid inter normalem lineam et extremitatem interest subsicivum appellamus.\n" +
            "\n" +
            "Est et ager similis subsicivorum condicioni extra clusus et non adsignatus; qui si rei publicae populi Romani aut ipsius coloniae, cuius fine circumdatur, sive peregrinae urbis aut locis sacris aut religiosis a<ut> quae ad populum Romanum pertinent datus non est, iure subsicivorum in eius qui adsignare potuerit remanet potestate.\n" +
            "\n" +
            "Ager extra clusus est <et> qui int<er> finitimam lineam et centurias interiacet; ideoque extra clusus, quia ultra limites finitima linea cludatur. ";
    public void setText(String text){

        this.text = text;
        postInvalidate();
    }
    public static enum LangType{
        ALPHABETIC, CHARACTER
    }
    LangType langType = LangType.ALPHABETIC;
    public void setText(String text, LangType langType){
        this.langType = langType;
        setText(text);
    }

    protected int hGetMaximumHeight(){
        return defRadius * 2;
    }
    protected int hGetMaximumWidth(){
        return defRadius * 2;
    }
    private int defRadius;
    private int strokeWidth = 1, strokeColor = Color.rgb(0, 0, 0);
    private int paddingTop, paddingLeft, paddingRight, paddingBottom;
    int fontSize = 24;
    int textPosX, textPosY;
    float drawYBase, drawYTop, drawXLeft;
    float baseWidth, baseHeight;
    int lineHeightFactor;
    int BOUND_CALC_CHAR_NUMBER = 200;
    int maxDisplayLines;
    int textLength;
    public void initDraw(){
        textLength = text.length();
        drawYTop = textPosY + paddingTop;
        drawYBase = fontSize * 1.2f;
        drawXLeft = textPosX + paddingLeft;
        baseWidth = this.getWidth() - paddingLeft - paddingRight - textPosX;
        baseHeight = this.getHeight() - drawYTop - paddingBottom;
        lineHeightFactor = 1;
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        initDraw();
        Paint paint = getBrush();
        paint.setTextSize(fontSize);
        Rect textBound = new Rect();
        if(textLength<BOUND_CALC_CHAR_NUMBER){
            paint.getTextBounds(text, 0, textLength, textBound);
        }else{
            paint.getTextBounds(text.substring(0, BOUND_CALC_CHAR_NUMBER), 0, BOUND_CALC_CHAR_NUMBER, textBound);
        }
        maxDisplayLines = (int)(baseHeight/drawYBase);
        canvas.drawLine(baseWidth+drawXLeft, 0, baseWidth+drawXLeft, this.getHeight(), paint);
        if(textBound.width()<baseWidth){
            drawShortText(text, canvas, paint, lineHeightFactor);
        }else{
            String curText = "";
            int curPos = 0;
            while(true){
                curPos++;
                curText = text.substring(0, curPos);
                paint.getTextBounds(curText, 0, curPos, textBound);
                if(textBound.width() >= baseWidth || curText.indexOf('\n')>=0){
                    if(textBound.width()>=baseWidth){
                        if(langType==LangType.ALPHABETIC){
                            int end = curText.lastIndexOf(' ');
                            if(end>=0){
                                curText = curText.substring(0, curText.lastIndexOf(' '));
                                curText +=' ';
                            }
                            canvas.drawText(curText, drawXLeft, drawYBase * lineHeightFactor + drawYTop, paint);
                        }else if(langType==LangType.CHARACTER){
                            curText = curText.substring(0, curText.length()-3);
                            if(isNextNewLineInCharType){
                                canvas.drawText(curText, drawXLeft-fontSize/2, drawYBase * lineHeightFactor + drawYTop, paint);
                                isNextNewLineInCharType = false;
                            }else{
                                canvas.drawText(curText, drawXLeft, drawYBase * lineHeightFactor + drawYTop, paint);
                            }
                        }
                    }else{
                        if(langType==LangType.CHARACTER){
                            isNextNewLineInCharType = true;
                        }
                        canvas.drawText(curText, drawXLeft, drawYBase * lineHeightFactor + drawYTop, paint);
                    }
                    text = text.substring(curText.length());

                    curPos = 0;
                    lineHeightFactor++;
                }
                if(text.length()<BOUND_CALC_CHAR_NUMBER){
                    paint.getTextBounds(text, 0, text.length(), textBound);
                    if(textBound.width()<baseWidth){
                        drawShortText(text, canvas, paint, lineHeightFactor);
                        break;
                    }
                }
                if(maxDisplayLines<lineHeightFactor)break;
            }
        }
    }
    boolean isNextNewLineInCharType = false;
    // padding needs more optimization
    private void paddingDraw(Canvas canvas, String text, Paint paint){
        Rect bound = new Rect();
        paint.getTextBounds(text, 0, text.length(), bound);
        float diff = baseWidth - bound.width();
        if(diff<15.0f){
            canvas.drawText(text, drawXLeft, drawYBase * lineHeightFactor + drawYTop, paint);
            return;
        }

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

        float spaceWidth = diff/spaceCounts;

        float curXPos = drawXLeft;

        while(true){
            if(text.indexOf(' ')>0){
                String tmp = text.substring(0, text.indexOf(' '));

                canvas.drawText(tmp, curXPos, drawYBase * lineHeightFactor + drawYTop, paint);
                paint.getTextBounds(tmp, 0, tmp.length(), bound);
                curXPos += bound.width() + spaceWidth;
                text = text.substring(text.indexOf(' ') + 1);
            }else break;
        }
        canvas.drawText(text, curXPos, drawYBase * lineHeightFactor + drawYTop, paint);
    }
    private void drawShortText(String text, Canvas canvas, Paint paint, int heightFactor){
        int end = text.indexOf('\n');
        while(end>=0){

            canvas.drawText(text.substring(0, end), textPosX, drawYBase * lineHeightFactor + drawYTop, paint);
            lineHeightFactor++;
            text = text.substring(end+1);
            end = text.indexOf('\n');
        }
        canvas.drawText(text, textPosX, drawYBase * lineHeightFactor + drawYTop, paint);
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
