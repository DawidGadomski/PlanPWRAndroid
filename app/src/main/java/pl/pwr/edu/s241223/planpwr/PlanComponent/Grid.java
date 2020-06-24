package pl.pwr.edu.s241223.planpwr.PlanComponent;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.Settings.MainWindowSettings;


public class Grid extends View {
    private int WIDTH;
    private int HEIGHT;
    private MainWindowSettings settings;
    private GestureDetector gestureDetector;

    private List<Subject> subjects;


//  Paints
    private Paint paint;
    private Paint gridPaint;
    private Paint workspacePaint;
    private Paint subjectPaint;

    public Grid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());


    }

    private void init(@Nullable AttributeSet set){

//      Paints
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        workspacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        subjectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);





    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        settings = new MainWindowSettings(WIDTH, HEIGHT);
        gridPaint.setColor(settings.getGridColor());
        workspacePaint.setColor(settings.getWorkspaceColor());

//        for(Subject subject : subjects){
//            subject.setSettings(settings);
//        }
        this.setBackgroundColor(settings.getBackgroundColor());

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        drawBackground(canvas);
        drawText(canvas);


        for(Subject subject : subjects){
            subject.setSettings(settings);
            subject.setWidth();
            subject.setHeight();

            subject.drawSubject(canvas, paint);
            if(subject.getClickedFlag()){
                subject.drawOutline(canvas, subjectPaint);
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
        canvas.save();
        canvas.rotate(-90, settings.getWorkspacePosX()/2,
                settings.getHEIGHT());
        paint.setTextSize(settings.getWorkspaceFontSize());
        settings.drawTextCentred(canvas, paint, "Workspace",
                settings.getWorkSurfacePosX()/2 + settings.getTileHeight(),
                settings.getHEIGHT());
        canvas.restore();

//      Draw days on grid
        paint.setTextSize(settings.getDaysFontSize());
        for(int index = 0; index<settings.getDays().length; index++){
            canvas.save();
            settings.drawTextCentred(canvas, paint, settings.getDays()[index],
                    settings.getDaysPosX(),
                    (settings.getDaysOffset() + settings.getDaysPosY() * index));
            canvas.restore();
        }

//      Draw time on grid
        Timestamp timestamp = new Timestamp(2020,0,1,7,30,0,0);
        String timeStamp = settings.getSdf().format(timestamp);
        paint.setTextSize(settings.getTimesFontSize());

        for(int index = 0; index < 55; index++){
            canvas.save();
            canvas.rotate(-90,
                    settings.getTimeTextOffset() + settings.getTileWidth()
                            / 2 + settings.getTimePosX() * index,
                    settings.getTimePosY()-(settings.getTileHeight()/2));
            settings.drawTextCentred(canvas, paint, timeStamp,
                    settings.getTimeTextOffset() + settings.getTileWidth()
                            / 2 + settings.getTimePosX() * index,
                    settings.getTimePosY()-(settings.getTileHeight()/2));
            canvas.restore();
            timestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(15));
            timeStamp = settings.getSdf().format(timestamp);
        }
    }

    public void setSubjectsList(List<Subject> subjectsList){
        this.subjects = subjectsList;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        for (Subject subject : subjects) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            if (subject.isOver(x, y)) {
                gestureDetector.onTouchEvent(motionEvent);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        System.out.println("up");
                        subject.setClickedFlag(true);
                        return true;
                    case (MotionEvent.ACTION_MOVE):
                        System.out.println("move");
                        subject.move(x, y);
                        postInvalidate();
                        return true;
                    case (MotionEvent.ACTION_UP):
                        System.out.println("down");
                        subject.setClickedFlag(false);
                        postInvalidate();
                        return true;
                    case (MotionEvent.ACTION_CANCEL):

                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):

                        return true;
                    default:
                        gestureDetector.onTouchEvent(motionEvent);
                        return true;
                }
            }
        }
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent event) {

            System.out.println("dziala");
            return false;

        }

        @Override
        public void onLongPress(MotionEvent e) {
            System.out.println("long");

        }
    }
}
