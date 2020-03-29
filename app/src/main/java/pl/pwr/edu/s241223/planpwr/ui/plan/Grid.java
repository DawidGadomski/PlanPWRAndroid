package pl.pwr.edu.s241223.planpwr.ui.plan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import pl.pwr.edu.s241223.planpwr.R;

import static java.lang.Math.round;

public class Grid extends View {

    private boolean isInit;

    private float WIDTH;
    private float HEIGHT;

    private Paint paint;
    private Paint gridPaint;
    private Paint workspacePaint;

    private int backgroundColor;
    private int gridColor;
    private int workspaceColor;

    private float workSurfacePosX, workSurfacePosY;
    private float workspacePosX, workspacePosY;
    private float tileWidth, tileHeight;

    private SimpleDateFormat sdf;
    private Rect textBounds;

//  Work surface
    private float timePosX;
    private float timePosY;
    private float timeTextOffset;
    private float daysPosX;
    private float daysPosY;
    private float daysOffset;

    private int workspaceFontSize;
    private int daysFontSize;
    private int timesFontSize;

    private String[] days;


    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @SuppressLint("SimpleDateFormat")
    private void init(@Nullable AttributeSet set){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        backgroundPaint.setColor(getResources().getColor(android.R.color.background));
        backgroundColor = Color.parseColor("#FF282828");

        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridColor = Color.parseColor("#FF808080");
        gridPaint.setColor(gridColor);

        workspacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        workspaceColor = Color.parseColor("#FFC8C8C8");
        workspacePaint.setColor(workspaceColor);

        sdf =  new SimpleDateFormat("HH.mm");
        textBounds = new Rect();

        this.setBackgroundColor(backgroundColor);
    }

    public void initGrid(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tileWidth = WIDTH / 58;
        tileHeight = HEIGHT / 13;
        workSurfacePosX = 3 * tileWidth;
        workSurfacePosY = tileHeight;
        workspacePosX = 0;
        workspacePosY = HEIGHT - 2 * tileHeight;

//      Work surface
        timePosX = tileWidth;
        timePosY = tileHeight;
        timeTextOffset = 3 * tileWidth;
        daysPosX = 3 * tileWidth / 2;
        daysPosY = (2 * tileHeight);
        daysOffset = 2 * tileHeight;

        workspaceFontSize = getResources().getDimensionPixelSize(R.dimen.workspace_font_size);
        daysFontSize = getResources().getDimensionPixelSize(R.dimen.days_font_size);
        timesFontSize = getResources().getDimensionPixelSize(R.dimen.times_font_size);

        days  = new String[]{"pn", "wt", "sr", "cz", "pt"};

        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(!isInit){
            initGrid();
        }
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
//      Grid
        for (float x = workSurfacePosX; x <= getWidth(); x += tileWidth) {
            canvas.drawLine(x, 0, x, getHeight(), gridPaint);
        }
        for(float y=workSurfacePosY; y <= getHeight(); y=2*tileHeight + y){
            canvas.drawLine(0, y, getWidth(), y, gridPaint);
        }
//      Workspace
        canvas.drawRect(workspacePosX, workspacePosY, WIDTH, HEIGHT, workspacePaint);

//      Grid line
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
        canvas.drawLine(workspacePosX, workspacePosY, WIDTH, workspacePosY, paint);

//      Note line
        canvas.drawLine(workSurfacePosX, workspacePosY, workSurfacePosX, HEIGHT, paint);

    }
    private void drawText(Canvas canvas){
//      Note text
        canvas.save();
        canvas.rotate(-90, workSurfacePosX/2, HEIGHT);
        paint.setTextSize(workspaceFontSize);
        drawTextCentred(canvas, paint, "Workspace", workSurfacePosX/2 + tileHeight, HEIGHT);
        canvas.restore();

//      Draw days on grid
        paint.setTextSize(daysFontSize);
        for(int index = 0; index<days.length; index++){
            canvas.save();
            drawTextCentred(canvas, paint, days[index], daysPosX,(daysOffset + daysPosY * index));
            canvas.restore();
        }

//      Draw time on grid
        Timestamp timestamp = new Timestamp(2020,0,1,7,30,0,0);
        String timeStamp = sdf.format(timestamp);
        paint.setTextSize(timesFontSize);

        for(int index = 0; index < 55; index++){
            canvas.save();
            canvas.rotate(-90, timeTextOffset + tileWidth / 2 + timePosX * index, timePosY-(tileHeight/2));
            drawTextCentred(canvas, paint, timeStamp, timeTextOffset + tileWidth / 2 + timePosX * index,timePosY-(tileHeight/2));
            canvas.restore();
            timestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(15));
            timeStamp = sdf.format(timestamp);
        }
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy){
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }
}
