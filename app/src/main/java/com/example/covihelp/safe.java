package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;

public class safe extends AppCompatActivity {
    MediaPlayer playerSafe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        final Vibrator vibe = (Vibrator) safe.this.getSystemService(Context.VIBRATOR_SERVICE);
        playerSafe = MediaPlayer.create(this, R.raw.piano);
        playerSafe.start();
        vibe.vibrate(300);


}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}