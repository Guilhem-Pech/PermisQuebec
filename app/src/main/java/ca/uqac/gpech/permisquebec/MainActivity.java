package ca.uqac.gpech.permisquebec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Switch sexeSwitch = findViewById(R.id.switch_sexe);
        CheckBox message = findViewById(R.id.paiement_exige);

        if(message != null)
        message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView messageText = findViewById(R.id.textmessage);
                if(isChecked){
                    messageText.setActivated(true);
                } else {
                    messageText.setActivated(false);
                }
            }
        });

        if(sexeSwitch != null)
        sexeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView v = findViewById(R.id.sexetext);
                if(isChecked){
                    v.setText(getString(R.string._h));
                } else{
                    v.setText(getString(R.string.f));
                }
            }
        });
    }

    public void misaAJour(View view) {
        EditText editNom = findViewById(R.id.textView_lastname);
        EditText editPrenom = findViewById(R.id.textView_firstname);

        TextView prenom = findViewById(R.id.prenomtext);
        TextView nom = findViewById(R.id.nomText);

        if(editNom.getText().length() != 0){
            nom.setText(editNom.getText());
        }

        if(editPrenom.getText().length() != 0){
            prenom.setText(editPrenom.getText());
        }

    }




}
