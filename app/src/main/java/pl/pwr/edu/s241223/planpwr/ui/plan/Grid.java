package pl.pwr.edu.s241223.planpwr.ui.plan;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;


public class Grid extends View {
    private boolean isInit;

    private MainWindowSettings mainWindowSettings;

//  Paints
    private Paint paint;
    private Paint gridPaint;
    private Paint workspacePaint;

    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    private void init(@Nullable AttributeSet set){
        mainWindowSettings = new MainWindowSettings(this);


//      Paints
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        workspacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        gridPaint.setColor(mainWindowSettings.getGridColor());
        workspacePaint.setColor(mainWindowSettings.getWorkspaceColor());


        this.setBackgroundColor(mainWindowSettings.getBackgroundColor());
    }

    public void initGrid(){
        mainWindowSettings = new MainWindowSettings(this);



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
        for (float x = mainWindowSettings.getWorkSurfacePosX();
             x <= getWidth();
             x += mainWindowSettings.getTileWidth()) {

                canvas.drawLine(x, 0, x, getHeight(), gridPaint);
        }
        for(float y=mainWindowSettings.getWorkSurfacePosY();
            y <= getHeight();
            y=2*mainWindowSettings.getTileHeight() + y){

                canvas.drawLine(0, y, getWidth(), y, gridPaint);
        }

//      Workspace
        canvas.drawRect(mainWindowSettings.getWorkspacePosX(),
                mainWindowSettings.getWorkspacePosY(),
                mainWindowSettings.getWIDTH(),
                mainWindowSettings.getHEIGHT(),
                workspacePaint);

//      Grid line
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
        canvas.drawLine(mainWindowSettings.getWorkspacePosX(),
                mainWindowSettings.getWorkspacePosY(),
                mainWindowSettings.getWIDTH(),
                mainWindowSettings.getWorkspacePosY(),
                paint);

//      Note line
        canvas.drawLine(mainWindowSettings.getWorkSurfacePosX(),
                mainWindowSettings.getWorkspacePosY(), mainWindowSettings.getWorkSurfacePosX(),
                mainWindowSettings.getHEIGHT(),
                paint);

    }
    private void drawText(Canvas canvas){
//      Note text
        canvas.save();
        canvas.rotate(-90, mainWindowSettings.getWorkspacePosX()/2,
                mainWindowSettings.getHEIGHT());
        paint.setTextSize(mainWindowSettings.getWorkspaceFontSize());
        mainWindowSettings.drawTextCentred(canvas, paint, "Workspace",
                mainWindowSettings.getWorkSurfacePosX()/2 + mainWindowSettings.getTileHeight(),
                mainWindowSettings.getHEIGHT());
        canvas.restore();

//      Draw days on grid
        paint.setTextSize(mainWindowSettings.getDaysFontSize());
        for(int index = 0; index<mainWindowSettings.getDays().length; index++){
            canvas.save();
            mainWindowSettings.drawTextCentred(canvas, paint, mainWindowSettings.getDays()[index],
                    mainWindowSettings.getDaysPosX(),
                    (mainWindowSettings.getDaysOffset() + mainWindowSettings.getDaysPosY() * index));
            canvas.restore();
        }

//      Draw time on grid
        Timestamp timestamp = new Timestamp(2020,0,1,7,30,0,0);
        String timeStamp = mainWindowSettings.getSdf().format(timestamp);
        paint.setTextSize(mainWindowSettings.getTimesFontSize());

        for(int index = 0; index < 55; index++){
            canvas.save();
            canvas.rotate(-90,
                    mainWindowSettings.getTimeTextOffset() + mainWindowSettings.getTileWidth()
                            / 2 + mainWindowSettings.getTimePosX() * index,
                    mainWindowSettings.getTimePosY()-(mainWindowSettings.getTileHeight()/2));
            mainWindowSettings.drawTextCentred(canvas, paint, timeStamp,
                    mainWindowSettings.getTimeTextOffset() + mainWindowSettings.getTileWidth()
                            / 2 + mainWindowSettings.getTimePosX() * index,
                    mainWindowSettings.getTimePosY()-(mainWindowSettings.getTileHeight()/2));
            canvas.restore();
            timestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(15));
            timeStamp = mainWindowSettings.getSdf().format(timestamp);
        }
    }


}
