package pl.pwr.edu.s241223.planpwr.PlanComponent;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.R;
import pl.pwr.edu.s241223.planpwr.Settings.MainWindowSettings;


public class Grid extends View {
    private int WIDTH;
    private int HEIGHT;
    private MainWindowSettings settings;

    private List<Subject> subjects;


//  Paints
    private Paint paint;
    private Paint gridPaint;
    private Paint workspacePaint;
    private Paint subjectPaint;

    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init(@Nullable AttributeSet set){

//      Paints
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        workspacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        subjectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        gridPaint.setColor(ContextCompat.getColor(getContext(), R.color.thirdColor));
        workspacePaint.setColor(ContextCompat.getColor(getContext(), R.color.fourthColor));

        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gridBackgroundColor));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        settings = new MainWindowSettings(WIDTH, HEIGHT);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        drawBackground(canvas);
        drawText(canvas);

        if(subjects != null){

            for(Subject subject : subjects){
                subject.setSettings(settings);
                subject.setWidth();
                subject.setHeight();

                subject.drawSubject(canvas, subjectPaint);
                if(subject.getClickedFlag()){
                    subject.drawOutline(canvas, subjectPaint);
                }
            }
        }
    }

    private void drawBackground(Canvas canvas) {
//      Grid
        for (float x = settings.getWorkSurfacePosX();
             x <= getWidth();
             x += settings.getTileWidth()) {

                canvas.drawLine(x, 0, x, getHeight(), gridPaint);
        }
        for(float y=settings.getWorkSurfacePosY();
            y <= getHeight();
            y=2*settings.getTileHeight() + y){

                canvas.drawLine(0, y, getWidth(), y, gridPaint);
        }

//      Workspace
        canvas.drawRect(settings.getWorkspacePosX(),
                settings.getWorkspacePosY(),
                settings.getWIDTH(),
                settings.getHEIGHT(),
                workspacePaint);

//      Grid line
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
        canvas.drawLine(settings.getWorkspacePosX(),
                settings.getWorkspacePosY(),
                settings.getWIDTH(),
                settings.getWorkspacePosY(),
                paint);

//      Note line
        canvas.drawLine(settings.getWorkSurfacePosX(),
                settings.getWorkspacePosY(), settings.getWorkSurfacePosX(),
                settings.getHEIGHT(),
                paint);

    }

    private void drawText(Canvas canvas){
//      Note text
//        canvas.save();
//        canvas.rotate(-90, settings.getWorkspacePosX()/2,
//                settings.getHEIGHT());
//        paint.setTextSize(settings.getWorkspaceFontSize());
//        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText("Workspace", settings.getWorkSurfacePosX()/2 + settings.getTileHeight(),
//                settings.getHEIGHT(), paint);
//
//        canvas.restore();

//      Draw days on grid
        paint.setTextSize(settings.getDaysFontSize());
        paint.setTextAlign(Paint.Align.CENTER);
        for(int index = 0; index<settings.getDays().length; index++){
            canvas.save();
            canvas.drawText(settings.getDays()[index], settings.getDaysPosX(),
                    (settings.getDaysOffset() + settings.getDaysPosY() * index), paint);
            canvas.restore();
        }

//      Draw time on grid
        Timestamp timestamp = new Timestamp(2020,0,1,7,30,0,0);
        String timeStamp = settings.getSdf().format(timestamp);
        paint.setTextSize(settings.getTimesFontSize());
        paint.setTextAlign(Paint.Align.CENTER);

        for(int index = 0; index < 55; index++){
            canvas.save();
            canvas.rotate(-90,
                    settings.getTimeTextOffset() + settings.getTileWidth()
                            / 2 + settings.getTimePosX() * index,
                    settings.getTimePosY()-(settings.getTileHeight()/2));

            canvas.drawText(timeStamp, settings.getTimeTextOffset() + settings.getTileWidth()
                            / 2 + settings.getTimePosX() * index,
                    settings.getTimePosY()-(settings.getTileHeight()/2), paint);

            canvas.restore();
            timestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(15));
            timeStamp = settings.getSdf().format(timestamp);
        }
    }

    public void setSubjectsList(List<Subject> subjectsList){
        this.subjects = subjectsList;
    }



}
