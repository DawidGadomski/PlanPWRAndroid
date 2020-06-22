package pl.pwr.edu.s241223.planpwr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Map;
import java.util.TreeMap;

public class InputFormActivity extends AppCompatActivity{
    private AlertDialog.Builder builder;

    private EditText etName, etTime, etTerm, etRoom, etProf;
    private Button  bAccept, bCancel;
    private String name, term, time, prof, room, kind, week;
    private RadioButton rLab;
    private RadioButton rLect;
    private RadioButton rWeek;
    private RadioButton rOddWeek;
    private RadioButton rEvenWeek;

    private Map<String, Object> dataMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_input_subject);

        builder = new AlertDialog.Builder(this);
        builder.setMessage("Uzupełnij pole Nazwa oraz Czas")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setVisible(false);
                    }
                });


        etName = findViewById(R.id.edName);
        etTime = findViewById(R.id.edTime);
        etTerm = findViewById(R.id.edTerm);
        etRoom = findViewById(R.id.edRoom);
        etProf = findViewById(R.id.edProf);

        bAccept = findViewById(R.id.bAccept);
        bCancel = findViewById(R.id.bCancel);

        rWeek = findViewById(R.id.rbWeek);
        rEvenWeek = findViewById(R.id.rbEvenWeek);
        rOddWeek = findViewById(R.id.rbOddWeek);
        rLect = findViewById(R.id.rbLect);
        rLab = findViewById(R.id.rbLab);

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().equals("") | etTime.getText().toString().equals("")){
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    createDataOfSubject();
                    setVisible(false);
                }
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisible(false);
            }
        });
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setTerm(String t) {
        this.term = t;
    }

    public void setTime(String t) {
        this.time = t;
    }

    public void setProf(String p) {
        this.prof = p;
    }

    public void setRoom(String r) {
        this.room = r;
    }

    public void setKind(String t) {
        this.kind = t;
    }

    public void setWeek(String w) {
        this.week = w;
    }

    public String getName() {
        return this.name;
    }

    public String getTerm() {
        return this.term;
    }

    public String getTime() {
        return this.time;
    }

    public String getProf() {
        return this.prof;
    }

    public String getRoom() {
        return this.room;
    }

    public String getKind() {
        return this.kind;
    }

    public String getWeek() {
        return this.week;
    }

    /***
     * Zebranie danych z text area i dodanie ich do mapy która będzie zwracana przez formularz
     */
    public void createDataOfSubject(){
        setName(etName.getText().toString());
        setTerm(etTerm.getText().toString());
        setTime(etTime.getText().toString());
        setProf(etProf.getText().toString());
        setRoom(etRoom.getText().toString());
        if (rLab.isSelected()) {
            setKind("1");
        }
        if (rLect.isSelected()) {
            setKind("2");
        }
        if (rWeek.isSelected()) {
            setWeek("week");
        }
        if (rOddWeek.isSelected()) {
            setWeek("TN");
        }
        if (rEvenWeek.isSelected()) {
            setWeek("TP");
        }
        dataMap = new TreeMap<>();
        dataMap.put("name", getName());
        dataMap.put("term", getTerm());
        dataMap.put("time", getTime());
        dataMap.put("prof", getProf());
        dataMap.put("room", getRoom());
        dataMap.put("type", getKind());
        dataMap.put("week", getWeek());
    }

}
