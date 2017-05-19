package br.com.thiengo.rateapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.thiengo.rateapp.util.RateDialogManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RateDialogManager.showRateDialog(this, savedInstanceState);
    }
}
