package pl.pwr.edu.s241223.planpwr.AndroidArchitecture;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Map;

import pl.pwr.edu.s241223.planpwr.Settings.MainWindowSettings;

/***
 * Klasa odpowiadająca za tworzenie i rysyowanie obiektu typu przedmiot
 * Dane przedmiotu składowane są w obiekcie DataOfSubject
 */
@Entity(tableName = "subject_table")
public class Subject implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private float width;
    private float height;
    private float posX;
    private float posY;
    private String name;
    private String term;
    private int time;
    private String prof;
    private String room;
    private int type;
    private String week;
    private int absences;
    private int allAbsences;

    @Ignore
    private MainWindowSettings settings;
    @Ignore
    private int color;
    @Ignore
    private boolean clickedFlag;

//    private Map<String, Object> dataMap;
//    private ArrayList<Note> noteArrayList;
//    private ArrayList<String> testList;


    public Subject(float width, float height, float posX, float posY, String name, String term, int time, String prof,
                   String room, int type, String week, int absences, int allAbsences) {
        //      Init

//        this.noteArrayList = new ArrayList<Note>();
//        this.testList = new ArrayList<String>();

        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.term = term;
        this.time = time;
        this.prof = prof;
        this.room = room;
        this.type = type;
        this.week = week;
        this.absences = absences;
        this.allAbsences = allAbsences;

        selectColor();
    }

    /***
     * Tworzenie nowego przedmiotu
     * @param map - mapa dostarczona przez formularz tworzenia przedmiotu (InputForm) zawierająca dane do stworzenia przedmiotu
     */
    public Subject(Map<String, Object> map){
//      Init

//        this.noteArrayList = new ArrayList<Note>();
//        this.testList = new ArrayList<String>();

        this.color = Color.parseColor("#FFFF00FF");

//      Data of Subject

        this.posX = 150;
        this.posY = 150;
        this.name = String.valueOf(map.get("name"));
        this.term = String.valueOf(map.get("term"));
        this.time = Integer.parseInt(String.valueOf(map.get("time")));
        this.prof = String.valueOf(map.get("prof"));
        this.room = String.valueOf(map.get("room"));
        this.type = Integer.parseInt(String.valueOf(map.get("type")));
        this.week = String.valueOf(map.get("week"));
        this.absences = 0;
        this.allAbsences = 1;

//      Update color (based on data of subject)
        selectColor();

//        this.dataMap = map;

    }

    public void updateSubject(Map<String, Object> dataMap){
        this.name = String.valueOf(dataMap.get("name"));
        this.term = String.valueOf(dataMap.get("term"));
        this.time = Integer.parseInt(String.valueOf(dataMap.get("time")));
        this.prof = String.valueOf(dataMap.get("prof"));
        this.room = String.valueOf(dataMap.get("room"));
        this.type = Integer.parseInt(String.valueOf(dataMap.get("type")));
        this.week = String.valueOf(dataMap.get("week"));
    }

    public String getName(){
        return this.name;
    }
    public String getTerm(){
        return this.term;
    }
    public String getProf(){
        return this.prof;
    }
    public String getRoom(){
        return this.room;
    }
    public String getWeek(){
        return this.week;
    }
    public int getTime(){
        return this.time;
    }
    public int getType(){
        return this.type;
    }


    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public int getAllAbsences() {
        return allAbsences;
    }

    public void setAllAbsences(int allAbsences) {
        this.allAbsences = allAbsences;
    }

    public void setClickedFlag(){
        this.clickedFlag = !this.clickedFlag;
    }

    public void setClickedFlag(boolean flag){
        this.clickedFlag = flag;
    }

    public boolean getClickedFlag(){
        return this.clickedFlag;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getColor() {
        return color;
    }

    public void setSettings(MainWindowSettings settings){this.settings = settings;}

    //  Functions of Subject


    /***
     * Funkcja wyznaczająca kolor przedmiotu na podstawie typu
     * Lab - czerwony
     * Wykład - niebieski
     */
    public void selectColor() {
        if (this.type == 1){
            setColor(Color.parseColor("#FFFF0000"));
        }
        else if (this.type == 2){
            setColor(Color.parseColor("#FF0000FF"));
        }
    }

    /***
     * Funkcja wyznaczająca szerokość przedmiotu na podstawie czasu
     */
    public void setWidth() {
        this.width = this.time / 15f * settings.getTileWidth();
    }

    public void setHeight(){
        this.height = this.settings.getTileHeight()*2;
    }

    /***
     * Rysowanie obramowania w momęcie najechania na przedmiot kursorem
     * @param canvas - Graphics2D dostarczony z JComponentu
     */
    public void drawOutline(Canvas canvas, Paint paint){
        canvas.save();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        if (this.week.equals("TN")){
            canvas.drawRect(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height / 2),paint);
        }
        else if (this.week.equals("TP")){
            canvas.drawRect(this.posX, (this.posY + settings.getTileHeight()),
                    (this.posX+this.width), (this.posY+this.height), paint);
        }
        else{
            canvas.drawRect(this.posX, this.posY, (this.posX+this.width), (this.posY + this.height), paint);
        }
        canvas.restore();
    }

    /***
     * Funkcja sprawdzająca czy kursor jest nad obiektem
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     * @return True jeśli kursor jest nad obiektem else False
     */
    public boolean isOver(double mousePosX, double mousePosY){
        if (this.week.equals("TN")) {
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY < mousePosY && mousePosY < (this.posY + this.height / 2)) {
                    this.setClickedFlag(true);
                    return true;
                }
            }
        }
        else if (this.week.equals("TP")) {
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY + this.height / 2 < mousePosY && mousePosY < (this.posY + height)) {
                    this.setClickedFlag(true);
                    return true;

                }
            }
        }
        else{
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY < mousePosY && mousePosY < (this.posY + height)) {
//                    this.setClickedFlag(true);
                    return true;
                }
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
        paint.setColor(this.color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        if (this.week.equals("TN")){
            canvas.drawRect(this.posX, this.posY, (this.posX+this.width), (this.posY+this.height / 2), paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.name, (this.posX + this.width / 2),
                    (this.posY + this.height / 4), paint);
        }
        else if (this.week.equals("TP")){
            canvas.drawRect(this.posX, (this.posY + settings.getTileHeight()),
                    (this.posX+this.width), (this.posY+this.height), paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.name, (this.posX + this.width / 2),
                    (this.posY + this.height * 3 / 4), paint);
        }
        else {
            canvas.drawRect(this.posX, this.posY, (this.posX + this.width), (this.posY + this.height), paint);
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.name, (this.posX + this.width / 2),
                    (this.posY + this.height / 2), paint);
        }
        canvas.restore();

    }

    /***
     * Poruszanie przedmiotem na podstawie położenia kursora jeśli flaga jest ustawiona na true (LPM jest wcisnięty)
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     */
    public void move(double mousePosX, double mousePosY){
        if (this.clickedFlag){
            if (this.week.equals("TN")){
                this.posX = (float) mousePosX - this.width / 2;
                this.posY = (float) mousePosY - this.height / 4;
                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
            else if (this.week.equals("TP")){
                this.posX = (int) mousePosX - this.width / 2;
                this.posY = (int) mousePosY - this.height * 3 / 4;
                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
            else{
                this.posX = (int) mousePosX - this.width / 2;
                this.posY = (int) mousePosY - this.height / 2;

                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
        }
    }
}
