package sg.edu.np.mad.week3t01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
String title = "Main Activity2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.v(title, "Create");

        Intent myRecevIntent = getIntent();
        String recvUserName;
        String recvPassword;

        recvUserName = myRecevIntent.getStringExtra("UserName");
        recvPassword = myRecevIntent.getStringExtra("UserPassword");

        Log.v(title, "Recved UserName: " + recvUserName + " Password: " + recvPassword);
        myCountdownTimer();
    }
    private int getRandomNumber(){
        Random ran = new Random();
        int myRandomValue = ran.nextInt(100000);
        return myRandomValue;
    }
    CountDownTimer myCountdown;

    private void myCountdownTimer(){

        TextView myOTPNumber = findViewById(R.id.textView5);
        myOTPNumber.setText("Yout OTP is " + getRandomNumber());
        myCountdown = new CountDownTimer(10000, 10000) {
            @Override
            public void onTick(long l) {
                Log.v(title, "CountDown " + l/1000);
                Toast.makeText(getApplicationContext(), "Your OTP will expire in " + 1/1000 + "secs" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Log.v(title, "Finished COuntdown");
                myCountdown.cancel();
                queryOTP();
            }
        };
            myCountdown.start();
    };
    private void queryOTP(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New OTP?");
        builder.setMessage("Your OTP just expired. Do you want a new OTP?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(title, "User Accepts");
                myCountdownTimer();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(title, "User declines");
                myCountdown.cancel();
                finish();
            }
        });
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }



}