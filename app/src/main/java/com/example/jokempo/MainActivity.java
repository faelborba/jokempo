package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1, jogador2;
    ImageButton botaoPedra, botaoPapel, botaoTesoura;

    Animation some, aparece;

    int jogada1=0;
    int jogada2=0;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alex_play);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        botaoPapel = findViewById(R.id.botaoPapel);
        botaoPedra = findViewById(R.id.botaoPedra);
        botaoTesoura = findViewById(R.id.botaoTesoura);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);
        some.setDuration(1500);
        aparece.setDuration(100);
        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaInimigo();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
                verificaJogada();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public void tocouBotao(View view){
        tocaSom();
        jogador1.setScaleX(-1);
        switch (view.getId()){
            case(R.id.botaoPedra):
                jogador1.setImageResource(R.drawable.pedra);
                jogada1 = 1;
                break;
            case(R.id.botaoPapel):
                jogador1.setImageResource(R.drawable.papel);
                jogada1 = 2;
                break;
            case(R.id.botaoTesoura):
                jogador1.setImageResource(R.drawable.tesoura);
                jogada1 = 3;
                break;
        }
        jogador2.setImageResource(R.drawable.interrogacao);
        jogador2.startAnimation(some);
    }

    public void sorteiaJogadaInimigo(){
        Random r = new Random();
        int sort = r.nextInt(3) + 1;
        if (sort == 1){
            jogador2.setImageResource(R.drawable.pedra);
        }else if ( sort == 2){
            jogador2.setImageResource(R.drawable.papel);
        }else if (sort == 3){
            jogador2.setImageResource(R.drawable.tesoura);
        }
        jogada2 = sort;
    }
    public void verificaJogada(){
        if (jogada1 == jogada2){
            Toast.makeText(this,"Empate!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ((jogada1==1 && jogada2 == 3)||(jogada1==2 && jogada2 == 1)||(jogada1==3 && jogada2 == 2)){
            Toast.makeText( this,"Você venceu!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ((jogada1==1 && jogada2 == 2)||(jogada1==2 && jogada2 == 3)||(jogada1==3 && jogada2 == 1)){
            Toast.makeText( this,"Você perdeu!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void tocaSom(){
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
}
