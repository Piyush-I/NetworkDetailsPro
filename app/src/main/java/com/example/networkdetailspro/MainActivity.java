package com.example.networkdetailspro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btncheck;
TextView varText;
String info;
String strphoneType = "";
static final int PERMISSION_READ_STATE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Check(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            myTelephonyManager();
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_PHONE_STATE},
            PERMISSION_READ_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){

            case PERMISSION_READ_STATE:{
                if(grantResults.length >= 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    myTelephonyManager();
                }else{
                    Toast.makeText(this,
                            "please providde necessary permissions",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void myTelephonyManager(){
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
        switch (phoneType){
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType = "NONE";
                break;
        }

        boolean isRoaming = manager.isNetworkRoaming();
        String PhoneType = strphoneType;
        String IMEINo = manager.getDeviceId();
        String SubscriberID = manager.getDeviceId();
        String SIMSerialNo = manager.getSimSerialNumber();
        String networkCountryISO = manager.getNetworkCountryIso();
        String SIMCountryISO = manager.getSimCountryIso();
        String softwareversion = manager.getDeviceSoftwareVersion();
        String voicemailNumber = manager.getVoiceMailNumber();

        info = "Phone Details: \n";
        info+="\n Phone Network Type:"+PhoneType;
        info+="\n IMEI Number:"+IMEINo;
        info+="\n Subscriber ID:"+SubscriberID;
        info+="\n SIM Serial Number:"+SIMSerialNo;
        info+="\n IMEI Number:"+IMEINo;
        info+="\n Network Country No:"+networkCountryISO;
        info+="\n SIM COuntry ISO:"+SIMCountryISO;
        info+="\n Software Version:"+softwareversion;
        info+="\n Voicemail Number:"+voicemailNumber;
        info+="\n Roaming:"+isRoaming;

        btncheck = (Button) findViewById(R.id.ButtonCheck);
        varText = (TextView) findViewById(R.id.infoView);
        varText.setText(info);



    }
    }
