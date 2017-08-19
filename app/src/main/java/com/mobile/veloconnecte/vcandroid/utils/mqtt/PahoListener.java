package com.mobile.veloconnecte.vcandroid.utils.mqtt;

/**
 * Created by Pierre on 18/07/2017.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.mobile.veloconnecte.vcandroid.entities.Measurment;
import com.mobile.veloconnecte.vcandroid.entities.Ride;
import com.mobile.veloconnecte.vcandroid.utils.database.MeasurmentManager;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoListener implements MqttCallback {

    MqttClient client;
    private Context context;
    private Ride ride;

    public PahoListener(Context context, Ride ride) {
        this.context = context;
        this.ride = ride;
    }



    public void listenTopic() {
//        boolean isWifi = false;
//        while(!isWifi){
//
//            ConnectivityManager connManager =
//                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo current = connManager.getActiveNetworkInfo();
//            isWifi = current != null && current.getType() == ConnectivityManager.TYPE_WIFI;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        boolean submitted = false;
        while(!submitted){
            try {
                MemoryPersistence persistence = new MemoryPersistence();
                client = new MqttClient("tcp://192.168.1.2:1883", "AndroidClient", persistence);
                client.connect();
                client.setCallback(this);
                client.subscribe("velo");
                submitted = true;
            } catch (MqttException e) {
                e.printStackTrace();
            }
            if(!submitted){
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        System.out.println(message);
        //Toast toast = Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT);
        Measurment measurment = Measurment.getMeasurmentFromJson(message.toString(), this.ride, context);
        MeasurmentManager measurmentManager = new MeasurmentManager(context);
        measurmentManager.insertMeasurment(measurment);
    }

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub
        Toast toast = Toast.makeText(context, cause.toString(), Toast.LENGTH_SHORT);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }
}
