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
        return result().closestSwatch.toString();
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

    public void telemetryOutput() {
        telemetry.addData("closestSwatch", closestSwatch());
        telemetry.addData("RGB", String.format("R %3d, G %3d, B %3d", getColor("red"), getColor("green"), getColor("blue")));
    }
}
