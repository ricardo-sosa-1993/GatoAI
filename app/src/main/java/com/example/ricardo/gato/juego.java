package com.example.ricardo.gato;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class juego extends Activity {

    TextView celda[] = new TextView[9];
    Position position ;
    boolean inicio = true;
    boolean computadora;
    boolean disponible[] = new boolean[9];
    int puntos_computadora;
    int puntos_jugador;
    TextView puntuacion_computadora;
    TextView puntuacion_jugador;
    Bundle extras;

    String dataString ;
    String dataString2;

    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        puntos_computadora=0;
        puntos_jugador=0;

        for(int i = 0; i < 9; i++ ){
            disponible[i]=true;
        }

        Button boton = (Button) findViewById(R.id.button2);

        puntuacion_computadora= (TextView) findViewById(R.id.textView4);
        puntuacion_jugador= (TextView) findViewById(R.id.textView5);

        celda[0]=(TextView) findViewById(R.id.cell11);
        celda[1]=(TextView) findViewById(R.id.cell12);
        celda[2]=(TextView) findViewById(R.id.cell13);
        celda[3]=(TextView) findViewById(R.id.cell21);
        celda[4]=(TextView) findViewById(R.id.cell22);
        celda[5]=(TextView) findViewById(R.id.cell23);
        celda[6]=(TextView) findViewById(R.id.cell31);
        celda[7]=(TextView) findViewById(R.id.cell32);
        celda[8]=(TextView) findViewById(R.id.cell33);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());

            }



        });







//fetching extra data passed with intents in a Bundle type variable

            extras = getIntent().getExtras();

                      /* fetching the string passed with intent using ‘extras’*/

                dataString2 = extras.getString("Dificultad");


                if(dataString2.equals("Fácil")){
                    position = new Position(0);
                }
                if(dataString2.equals("Medio")){
                    position = new Position(1);
                }
                if(dataString2.equals("Difícil")){
                    position = new Position(3);
                }
                dataString = extras.getString("Jugador");

                if(dataString.equals("Computadora")){
                    computadora = true;
                    tira_computadora();
                }else{
                    computadora = false;
                }





        celda[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[0]) {
                    celda[0].setText(Character.toString(position.turn));
                    position.move(0);
                    disponible[0]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[1]) {
                    celda[1].setText(Character.toString(position.turn));
                    position.move(1);
                    disponible[1]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[2]) {
                    celda[2].setText(Character.toString(position.turn));
                    position.move(2);
                    disponible[2]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[3].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[3]) {
                    celda[3].setText(Character.toString(position.turn));
                    position.move(3);
                    disponible[3]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[4].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[4]) {
                    celda[4].setText(Character.toString(position.turn));
                    position.move(4);
                    disponible[4]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[5].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[5]) {
                    celda[5].setText(Character.toString(position.turn));
                    position.move(5);
                    disponible[5]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[6].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[6]) {
                    celda[6].setText(Character.toString(position.turn));
                    position.move(6);
                    disponible[6]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[7].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[7]) {
                    celda[7].setText(Character.toString(position.turn));
                    position.move(7);
                    disponible[7]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });

        celda[8].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!computadora && disponible[8]) {
                    celda[8].setText(Character.toString(position.turn));
                    position.move(8);
                    disponible[8]=false;
                    computadora = true;
                    tira_computadora();
                }
            }
        });


    }

    public void tira_computadora(){
        if(computadora){
            if(inicio){
                inicio=false;
                Random rnd = new Random();
                int mueve= rnd.nextInt(9);
                celda[mueve].setText(Character.toString(position.turn));
                position.move(mueve);
                disponible[mueve]=false;
                computadora = false;
            }else{
                if(!position.isGameEnd()){
                    int best = position.bestMove();
                    celda[best].setText(Character.toString(position.turn));
                    position.move(best);
                    disponible[best]=false;
                    computadora = false;

                }
            }
        }
        if(position.isGameEnd()){
            if(dataString.equals("Computadora")){
                message = position.isWinFor('x') ? "Perdiste" : position.isWinFor('o') ? "Ganaste" : "Nada";
            }else{
                message = position.isWinFor('x') ? "Ganaste" : position.isWinFor('o') ? "Perdiste" : "Nada";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensaje");
            builder.setMessage(message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    if(message.equals("Ganaste")){
                        puntos_jugador++;
                        puntuacion_jugador.setText("Tú: " + puntos_jugador);
                    }
                    if(message.equals("Perdiste")){
                        puntos_computadora++;
                        puntuacion_computadora.setText("Computadora: " + puntos_computadora);
                    }
                    reinicia();
                }
            });
            builder.create();
            builder.show();


        }
    }

    public void reinicia(){

        for(int i = 0; i < 9;i++){
            celda[i].setText(" ");
            disponible[i]=true;
        }
        if(dataString2.equals("Fácil")){
            position = new Position(0);


        }
        if(dataString2.equals("Medio")){
            position = new Position(1);
        }
        if(dataString2.equals("Difícil")){
            position = new Position(3);
        }
        dataString = extras.getString("Jugador");

        if(dataString.equals("Computadora")){
            computadora = true;
            tira_computadora();
        }else{
            computadora = false;
        }
    }



}
