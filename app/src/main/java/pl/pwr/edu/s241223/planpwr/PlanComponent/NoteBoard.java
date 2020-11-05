package pl.pwr.edu.s241223.planpwr.PlanComponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.Subject;
import pl.pwr.edu.s241223.planpwr.R;
import pl.pwr.edu.s241223.planpwr.Settings.MainWindowSettings;
import pl.pwr.edu.s241223.planpwr.AndroidArchitecture.NoteCard;

public class NoteBoard extends View {
    private int WIDTH;
    private int HEIGHT;
    private MainWindowSettings settings;

    private ArrayList<NoteCard> notesList;

    //  Paints
    private Paint paint;
    private Paint notePaint;
    private Paint textPaint;

    public NoteBoard(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        init(attrs);

        notesList = new ArrayList<NoteCard>();
    }

    public void setNotesList(ArrayList<NoteCard> notesList){
        this.notesList = notesList;
    }

    private void init(@Nullable AttributeSet set) {

//      Paints
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        notePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        settings = new MainWindowSettings(WIDTH, HEIGHT);
        notePaint.setColor(ContextCompat.getColor(getContext(), R.color.secondColor));
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));

        this.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.mainColor));

        for(NoteCard note : notesList) {
            Rect bounds = new Rect();
            textPaint.getTextBounds(note.getText(), 0, note.getText().length(), bounds);
            int height = bounds.height();
            int width = bounds.width();

            note.setWidth(width);
            note.setHeight(height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        for(NoteCard note : notesList){

            note.drawSubject(canvas, notePaint);
            if(note.isClickedFlag()){
                note.drawOutline(canvas, paint);
            }
        }
    }
}
