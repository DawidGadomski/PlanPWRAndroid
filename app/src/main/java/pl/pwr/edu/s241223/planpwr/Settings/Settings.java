package pl.pwr.edu.s241223.planpwr.Settings;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class Settings implements Serializable {

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

    @SuppressLint("SimpleDateFormat")
    public Settings(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        tileWidth = WIDTH / 58;
        tileHeight = HEIGHT / 13;

        subjectWidth = 7*tileWidth;
        subjectHeight = tileHeight;

        textBounds = new Rect();

//      Colors
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
