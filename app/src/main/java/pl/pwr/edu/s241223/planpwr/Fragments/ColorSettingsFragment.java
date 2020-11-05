package pl.pwr.edu.s241223.planpwr.Fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import pl.pwr.edu.s241223.planpwr.R;

public class ColorSettingsFragment extends Fragment implements View.OnClickListener {
    private Button bMainColor;
    private Button bSecondColor;
    private Button bThirdColor;
    private Button bFourthColor;
    private Button bGridColor;
    private Button bTextColor;
    private Button bLabColor;
    private Button bProjectColor;
    private Button bSeminaryColor;
    private Button bLectureColor;
    private Button bColor;

    private int selectedColor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_colors_settings, container, false);



        bMainColor = view.findViewById(R.id.bMainColor);
        bSecondColor = view.findViewById(R.id.bSecondColor);
        bThirdColor = view.findViewById(R.id.bThirdColor);
        bFourthColor = view.findViewById(R.id.bFourthColor);
        bGridColor = view.findViewById(R.id.bGridColor);
        bTextColor = view.findViewById(R.id.bTextColor);
        bLabColor = view.findViewById(R.id.bLabColor);
        bProjectColor = view.findViewById(R.id.bProjectColor);
        bSeminaryColor = view.findViewById(R.id.bSeminaryColor);
        bLectureColor = view.findViewById(R.id.bLectureColor);

        bMainColor.setOnClickListener(this);
        bSecondColor.setOnClickListener(this);
        bThirdColor.setOnClickListener(this);
        bFourthColor.setOnClickListener(this);
        bGridColor.setOnClickListener(this);
        bLabColor.setOnClickListener(this);
        bProjectColor.setOnClickListener(this);
        bSeminaryColor.setOnClickListener(this);
        bLectureColor.setOnClickListener(this);
        bTextColor.setOnClickListener(this);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if(view == view.findViewById(R.id.bMainColor)){
            ColorPickerDialogBuilder.with(getContext()).setTitle("Choose color")
                    .initialColor(getContext().getColor(R.color.mainColor))
                    .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {

                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                            changeBackgroundColor(selectedColor);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .build()
                    .show();
        }
        else if(view == view.findViewById(R.id.bSecondColor)){

        }
    }
}
