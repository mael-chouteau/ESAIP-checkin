package com.esaip.esaip_check_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.esaip.esaip_check_in.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Login extends AppCompatActivity {
    ImageView loginbtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        loginbtn = findViewById(R.id.loginbtn);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!= null){
            nagigateToMainactivity();
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    // On lance la connexion
    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    // Vérification de l'authentification, sinon on affiche un message qui dit que la connexion s'est mal passée.
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    nagigateToMainactivity();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Quelque chose s'est mal passé :/", Toast.LENGTH_LONG).show();
                }
            });

    // Navigation vers la main activity
    private void nagigateToMainactivity() {
        finish();
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}