package org.firstinspires.ftc.teamcode.action;

import android.util.Size;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

public class Vision {

    Telemetry telemetry;
    PredominantColorProcessor colorSensor;
    VisionPortal portal;
    public void init(@NonNull OpMode opMode) {

        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        colorSensor = new PredominantColorProcessor.Builder()
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.1, 0.1, 0.1, -0.1))
                .setSwatches(
                        PredominantColorProcessor.Swatch.RED,
                        PredominantColorProcessor.Swatch.BLUE,
                        PredominantColorProcessor.Swatch.YELLOW,
                        PredominantColorProcessor.Swatch.BLACK,
                        PredominantColorProcessor.Swatch.WHITE)
                .build();

        portal = new VisionPortal.Builder()
                .addProcessor(colorSensor)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();
    }
    public PredominantColorProcessor.Result result() {
        return colorSensor.getAnalysis();
    }

    public String closestSwatch() {
        return (result().closestSwatch == null) ? "null" : result().closestSwatch.toString();
    }

    public int getColor(String color) {
        switch (color.toLowerCase()) {
            case "red":
                return Color.red(result().rgb);
            case "green":
                return Color.green(result().rgb);
            case "blue":
                return Color.blue(result().rgb);
        }
        return -1;
    }

    public double[] getHSVValue() {
        int red = Math.min(Math.max(getColor("red"), 0), 255);
        int green = Math.min(Math.max(getColor("green"), 0), 255);
        int blue = Math.min(Math.max(getColor("blue"), 0), 255);
        red /= 255;
        green /= 255;
        blue /= 255;
        telemetry.addData("red", red);
        int valueMax = Math.max(red, Math.max(blue, green));
        int valueMin = Math.min(red, Math.min(blue, green));
        int deltaValue = valueMax - valueMin;
        double hue = 0, saturation, value;
        if (deltaValue > 0) {
            if (valueMax == red) {
                hue = ((double) (green - blue) / deltaValue) % 6;
            } else if (valueMax == green) {
                hue = ((double) blue - red) / deltaValue + 2;
            } else {
                hue = ((double) red - green) / deltaValue + 4;
            }
            hue *= 60;
            if(hue < 0) {
                hue += 360;
            }
        }


        value = valueMax;

        saturation = (value == 0) ? 0 : deltaValue / value;
        saturation *= 100;
        value *= 100;
        return new double[] {hue, saturation, value};
    }

    public void telemetryOutput() {
        telemetry.addData("closestSwatch", closestSwatch());
        telemetry.addData("RGB", String.format("R %4d, G %4d, B %4d", getColor("red"), getColor("green"), getColor("blue")));
        telemetry.addData("HSV", String.format("H %4.0f, S %4.1f, V %4.1f", getHSVValue()[0], getHSVValue()[1], getHSVValue()[2]));
    }
}
