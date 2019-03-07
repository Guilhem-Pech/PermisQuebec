package ca.uqac.gpech.permisquebec;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private TextView sexeTextView;
    private TextView prenom;
    private TextView nom;
    private boolean messageBool = true;
    private String CHANNEL_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onStart() {
        super.onStart();
        sexeTextView = findViewById(R.id.sexetext);
        Switch sexeSwitch = findViewById(R.id.switch_sexe);
        final CheckBox message = findViewById(R.id.paiement_exige);
        prenom = findViewById(R.id.prenomtext);
        nom = findViewById(R.id.nomText);

        if(message != null)
        message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView messageText = findViewById(R.id.textmessage);
                if(isChecked){
                    messageText.setActivated(true);
                    messageBool = true;
                } else {
                    messageText.setActivated(false);
                    messageBool = false;
                }
            }
        });

        if(sexeSwitch != null)
        sexeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    sexeTextView.setText(getString(R.string._h));
                } else{
                    sexeTextView.setText(getString(R.string.f));
                }
            }
        });
    }

    public void miseAJour(View view) {
        EditText editNom = findViewById(R.id.textView_lastname);
        EditText editPrenom = findViewById(R.id.textView_firstname);



        if(editNom.getText().length() != 0){
            nom.setText(editNom.getText());
        }

        if(editPrenom.getText().length() != 0){
            prenom.setText(editPrenom.getText());
        }

    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("prenom", prenom.getText());
        outState.putCharSequence("nom", nom.getText());
        outState.putCharSequence("sexe", sexeTextView.getText());
        outState.putBoolean("message",messageBool);


    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        prenom.setText(savedInstanceState.getCharSequence("prenom"));
        nom.setText(savedInstanceState.getCharSequence("nom"));
        sexeTextView.setText(savedInstanceState.getCharSequence("sexe"));
        boolean m = savedInstanceState.getBoolean("message");
        messageBool = m;
        if(m){
            findViewById(R.id.textmessage).setActivated(true);
        }else{
            findViewById(R.id.textmessage).setActivated(false);
        }


    }

    public void createToast(View view) {
        Toast toast = Toast.makeText(this,getString(R.string.toastsphrase),
                Toast.LENGTH_SHORT);
        toast.show();
    }


    private void createNotificationChannel() {
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("default",
                "Channel name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel description");
        CHANNEL_ID = channel.getId();
        notificationManager.createNotificationChannel(channel);

    }
    
    public void createNotif(View view) {
        if( CHANNEL_ID.isEmpty())
            createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Notification test")
                .setContentText("HEYYYYYY")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }
}
