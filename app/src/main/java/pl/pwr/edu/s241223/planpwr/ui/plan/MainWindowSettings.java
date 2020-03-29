package pl.pwr.edu.s241223.planpwr.ui.plan;

import pl.pwr.edu.s241223.planpwr.R;
import pl.pwr.edu.s241223.planpwr.ui.Settings;

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

    public MainWindowSettings(Grid grid) {
        super(grid);

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

        workspaceFontSize = grid.getResources().getDimensionPixelSize(R.dimen.workspace_font_size);
        daysFontSize = grid.getResources().getDimensionPixelSize(R.dimen.days_font_size);
        timesFontSize = grid.getResources().getDimensionPixelSize(R.dimen.times_font_size);
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
