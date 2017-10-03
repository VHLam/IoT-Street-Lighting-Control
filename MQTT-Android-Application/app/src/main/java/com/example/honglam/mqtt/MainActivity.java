package com.example.honglam.mqtt;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    MqttAndroidClient client;
    TextView light1;
    TextView light2;
    TextView light3;
    TextView light4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light1 = (TextView) findViewById(R.id.textView);
        light2 = (TextView) findViewById(R.id.textView2);
        light3 = (TextView) findViewById(R.id.textView4);
        light4 = (TextView) findViewById(R.id.textView3);

        String clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Connected!",Toast.LENGTH_LONG).show();
                    subscribe();
                    start();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Connection Fail!",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if (message.toString().equals("0"))
                    light1.setTextColor(Color.GREEN);
                else if (message.toString().equals("1"))
                    light1.setTextColor(Color.RED);
                else if (message.toString().equals("2"))
                    light2.setTextColor(Color.GREEN);
                else if (message.toString().equals("3"))
                    light2.setTextColor(Color.RED);
                else if (message.toString().equals("4"))
                    light3.setTextColor(Color.GREEN);
                else if (message.toString().equals("5"))
                    light3.setTextColor(Color.RED);
                else if (message.toString().equals("6"))
                    light4.setTextColor(Color.GREEN);
                else if (message.toString().equals("7"))
                    light4.setTextColor(Color.RED);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    public void pub0(View v){
        String topic = "smartlighting";
        String msg = "0";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub1(View v){
        String topic = "smartlighting";
        String msg = "1";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pub2(View v){
        String topic = "smartlighting";
        String msg = "2";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub3(View v){
        String topic = "smartlighting";
        String msg = "3";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pub4(View v){
        String topic = "smartlighting";
        String msg = "4";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub5(View v){
        String topic = "smartlighting";
        String msg = "5";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pub6(View v){
        String topic = "smartlighting";
        String msg = "6";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pub7(View v){
        String topic = "smartlighting";
        String msg = "7";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        String topic = "smartlighting";
        String msg = "a";
        try {
            client.publish(topic, msg.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe() {
        String topic = "smartlighting";
        try {
            client.subscribe(topic, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
