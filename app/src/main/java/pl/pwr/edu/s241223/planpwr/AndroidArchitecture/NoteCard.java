package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

public class NoteCard implements Serializable {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private String text;
    private boolean clickedFlag;



    public NoteCard(int posX, int posY, String text) {
        this.posX = posX;
        this.posY = posY;
        this.text = text;

        clickedFlag = false;
    }


    public void drawOutline(Canvas canvas, Paint paint){
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);

        canvas.drawRect(this.posX, this.posY, (this.posX+this.width), (this.posY + this.height), paint);

        canvas.restore();
    }

    /***
     * Funkcja sprawdzająca czy kursor jest nad obiektem
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     * @return True jeśli kursor jest nad obiektem else False
     */
    public boolean isOver(double mousePosX, double mousePosY){

        if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
            if (this.posY < mousePosY && mousePosY < (this.posY + height)) {
//                    this.setClickedFlag(true);
                return true;
            }
        }

        return false;
    }

    /***
     * Rysowanie przedmiotu
     * @param canvas - Graphics2d dostarczony z JComponentu
     */
    public void drawSubject(Canvas canvas, Paint paint) {
        canvas.save();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        canvas.drawRect(this.posX, this.posY, (this.posX + this.width), (this.posY + this.height), paint);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.text, (this.posX + this.width / 2),
                (this.posY + this.height / 2), paint);

        canvas.restore();

    }

    /***
     * Poruszanie przedmiotem na podstawie położenia kursora jeśli flaga jest ustawiona na true (LPM jest wcisnięty)
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     */
    public void move(double mousePosX, double mousePosY){
        if (this.clickedFlag){

            this.posX = (int) mousePosX - this.width / 2;
            this.posY = (int) mousePosY - this.height / 2;

            //Right
//            if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
//                this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
//            }
            //Left
            if (this.posX < 1){
                this.posX = 1;
            }
//            //Down
//            if (this.posY + this.height > 1){
//                this.posY = this.height - 1;
//            }
            //UP
            if (this.posY < 1){
                this.posY = 1;
            }
        }

    }


    public void setWidth(int width) {
        this.width = width + 10;
    }

    public void setHeight(int height){
        this.height = height + 10;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClickedFlag() {
        return clickedFlag;
    }

    public void setClickedFlag(boolean clickedFlag) {
        this.clickedFlag = clickedFlag;
    }
}
