package com.bleu.bhu.spidergame.game;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

/**
 * Created by Bhuvanesh on 1/2/2016.
 */
public class UserInput implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    //float vibrateThreshold;
    //Vibrator v;
    private float deltaX,deltaY,deltaZ;
    private float lastX,lastY,lastZ;

    public UserInput() {
        deltaX=0;deltaY=0;deltaZ=0;
        lastX=0;lastY=0;lastZ=0;
    }

    public float[] getAccelerometerValues(){
        float[] values = new float[3];
        values[0] = lastX; values[1] = lastY; values[2] = lastZ;
        return values;
    }

    public void onPause(){
        sensorManager.unregisterListener(this);
    }

    public void onResume(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public boolean Init(Context context){
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // success! we have an accelerometer
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            //vibrateThreshold = accelerometer.getMaximumRange() / 2;
            //v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            return true;
        } else {
            // fai! we dont have an accelerometer!
            System.out.println("No accelerometer found!");
            return false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        deltaX = lastX - event.values[0];
        deltaY = lastY - event.values[1];
        deltaZ = lastZ - event.values[2];
        // if the change is below 2, it is just plain noise
        if (Math.abs(deltaX) < 2)
            deltaX = 0;
        if (Math.abs(deltaY) < 2)
            deltaY = 0;
        if (Math.abs(deltaZ) < 2)
            deltaZ = 0;
        lastX = event.values[0];
        lastY = event.values[0];
        lastZ = event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
