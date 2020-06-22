package pl.pwr.edu.s241223.planpwr.Settings;


import pl.pwr.edu.s241223.planpwr.PlanComponent.Grid;
import pl.pwr.edu.s241223.planpwr.R;

public class MainWindowSettings extends Settings {
//  Data
    private String[] days;

//  Grid Positions
    private float workSurfacePosX, workSurfacePosY;
    private float workspacePosX, workspacePosY;
    private float workSurfaceWidth;
    private float workSurfaceHeight;

    private float timePosX;
    private float timePosY;
    private float timeTextOffset;

    private float daysPosX;
    private float daysPosY;
    private float daysOffset;

//  Fonts
    private int workspaceFontSize;
    private int daysFontSize;
    private int timesFontSize;

    public MainWindowSettings(int width, int height) {
        super(width, height);

        days  = new String[]{"pn", "wt", "sr", "cz", "pt"};

        workSurfacePosX = 3 * tileWidth;
        workSurfacePosY = tileHeight;
        workspacePosX = 0;
        workspacePosY = HEIGHT - 2 * tileHeight;
        workSurfaceWidth = WIDTH - workSurfacePosX;
        workSurfaceHeight = HEIGHT - workSurfacePosY;

//      Work surface
        timePosX = tileWidth;
        timePosY = tileHeight;
        timeTextOffset = 3 * tileWidth;
        daysPosX = 3 * tileWidth / 2;
        daysPosY = (2 * tileHeight);
        daysOffset = 2 * tileHeight;

//        workspaceFontSize = R.dimen.workspace_font_size;
//        daysFontSize = R.dimen.days_font_size;
//        timesFontSize = R.dimen.times_font_size;
        workspaceFontSize  = 10;
        daysFontSize = 10;
        timesFontSize = 10;
    }

    public String[] getDays() {
        return days;
    }

    public float getWorkSurfacePosX() {
        return workSurfacePosX;
    }

    public float getWorkSurfacePosY() {
        return workSurfacePosY;
    }

    public float getWorkspacePosX() {
        return workspacePosX;
    }

    public float getWorkspacePosY() {
        return workspacePosY;
    }

    public float getTimePosX() {
        return timePosX;
    }

    public float getTimePosY() {
        return timePosY;
    }

    public float getTimeTextOffset() {
        return timeTextOffset;
    }

    public float getDaysPosX() {
        return daysPosX;
    }

    public float getDaysPosY() {
        return daysPosY;
    }

    public float getDaysOffset() {
        return daysOffset;
    }

    public int getWorkspaceFontSize() {
        return workspaceFontSize;
    }

    public int getDaysFontSize() {
        return daysFontSize;
    }

    public int getTimesFontSize() {
        return timesFontSize;
    }

    public float getWorkSurfaceWidth() {
        return workSurfaceWidth;
    }

    public float getWorkSurfaceHeight() {
        return workSurfaceHeight;
    }
}
