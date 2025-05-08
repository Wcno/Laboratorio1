package com.example.laboratorio_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnImprimir = null;
    EditText txtMensaje = null;
    CheckBox check = null;
    CalendarView miCalendario = null;
    String miFecha = "";
    ImageButton imgButton = null;
    String msgMensaje = "";

    @SuppressLint({"MissingInflatedId","SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm");
        String hora = formatoFecha.format(new Date());

        btnImprimir = findViewById(R.id.btnHola);
        txtMensaje = findViewById(R.id.editTextNombre);
        check = findViewById(R.id.checkBox);
        imgButton = (ImageButton) findViewById(R.id.imageButton);

        miCalendario = findViewById(R.id.calendarView);
        long eventOccursOn = miCalendario.getDate();
        Date temporary = new Date(eventOccursOn);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");


        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msgMensaje += " "+txtMensaje.getText();
                msgMensaje += ", "+check.isChecked();
                msgMensaje += ", "+hora;
                msgMensaje += ", "+miFecha;
                Toast.makeText(getApplicationContext(), msgMensaje, Toast.LENGTH_LONG).show();

                msgMensaje = "";


            }
        });

        miCalendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                miFecha = dayOfMonth + "/" + (month+1) + "/" + year;

            }
        });
//boton de imagen para ir a la otra pestaña
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puente = new Intent(getApplicationContext()
                        , MainActivity2.class);
                startActivity(puente);
            }
        });

    }
}