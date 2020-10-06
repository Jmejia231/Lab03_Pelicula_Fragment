package com.example.peliculafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Frag_Pelicula.OnFragmentInteractionListener {

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String FRAGMENT_STATE = "state of Fragment";
    private int mRadioButtonChoice = 2;
    private int votos[] = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if(isFragmentDisplayed){
                mButton.setText(R.string.close);
            }
        }

        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }

        });
    }

    private void closeFragment() {
        FragmentManager peliculaManager = getSupportFragmentManager();
        Frag_Pelicula peliculaFragment = (Frag_Pelicula) peliculaManager.findFragmentById(R.id.fragment_container);
        if (peliculaFragment != null) {
            peliculaManager.beginTransaction().remove(peliculaFragment).commit();
            mButton.setText(R.string.open);
            isFragmentDisplayed = false;
        }
    }

    private void displayFragment() {
        Frag_Pelicula peliculaFragment = Frag_Pelicula.newInstance(mRadioButtonChoice);
        FragmentManager peliculaManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = peliculaManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, peliculaFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(FRAGMENT_STATE, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void votarButton(int Choice) {
        if(Choice == 0) {
            votos[0]++;
        }else {
            votos[1]++;
        }
        Toast.makeText(this, "Rocky Tiene "+votos[0]+"\n"+"Rambo Tiene "+votos[1], Toast.LENGTH_SHORT).show();
    }
}