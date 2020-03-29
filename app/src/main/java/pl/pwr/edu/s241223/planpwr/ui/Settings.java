package pl.pwr.edu.s241223.planpwr.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.security.auth.Subject;

import pl.pwr.edu.s241223.planpwr.ui.plan.DataOfNote;
import pl.pwr.edu.s241223.planpwr.ui.plan.DataOfSubject;
import pl.pwr.edu.s241223.planpwr.ui.plan.Grid;

public class Settings {
//      Settings
    protected float WIDTH;
    protected float HEIGHT;
    protected float tileWidth, tileHeight;
    private float subjectWidth;
    private float subjectHeight;
    protected SimpleDateFormat sdf;
    private Rect textBounds;

    private int backgroundColor;
    private int gridColor;
    private int workspaceColor;


//  Data
    private ArrayList<Subject> subjects;
    private ArrayList<DataOfSubject> subjectsList;
    private ArrayList<DataOfNote> notesList;
//    protected ArrayList<Note> notes;


    @SuppressLint("SimpleDateFormat")
    public Settings(Grid grid) {
        WIDTH = grid.getWidth();
        HEIGHT = grid.getHeight();

        tileWidth = WIDTH / 58;
        tileHeight = HEIGHT / 13;

        subjectWidth = 7*tileWidth;
        subjectHeight = tileHeight;

        textBounds = new Rect();

//      Colors
//        backgroundPaint.setColor(getResources().getColor(android.R.color.background));
        backgroundColor = Color.parseColor("#FF282828");
        gridColor = Color.parseColor("#FF808080");
        workspaceColor = Color.parseColor("#FFC8C8C8");


        sdf =  new SimpleDateFormat("HH.mm");
    }

    public float getWIDTH() {
        return WIDTH;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getGridColor() {
        return gridColor;
    }

    public int getWorkspaceColor() {
        return workspaceColor;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<DataOfSubject> getSubjectsList() {
        return subjectsList;
    }

    public ArrayList<DataOfNote> getNotesList() {
        return notesList;
    }

    public float getSubjectWidth() {
        return subjectWidth;
    }

    public float getSubjectHeight() {
        return subjectHeight;
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy){
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }
}
