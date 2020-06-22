package pl.pwr.edu.s241223.planpwr.Subjects;

/***
 * Obiekt zawierający dane notatek
 */
public class DataOfNote {
    private String text;
    private int posX;
    private int posY;

    /***
     * Konstruktor
     * @param text - tekst zawarty w notatce
     * @param posX - współrzędne X notatki
     * @param posY - współrzędne Y notatki
     */
    public DataOfNote(String text, int posX, int posY) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
    }

    //  Getters and Setters
    public String getText() {
        return text;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
