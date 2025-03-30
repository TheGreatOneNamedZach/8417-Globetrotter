package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber {

    Telemetry telemetry;
    CRServo magnet;
    TouchSensor grab;
    Boolean clawMovesUp = true;
    Boolean wasPressed = false;

    public Grabber(CRServo grabber, @NonNull OpMode opMode, TouchSensor grabButton) {
        this.magnet = grabber;
        this.grab = grabButton;
        this.telemetry = opMode.telemetry;
    }

    public void clawMovement () {

        this.magnet.setPower(grab.getValue() * (clawMovesUp ? -0.30 : 0.30));

        if (this.wasPressed && !this.grab.isPressed()) {
            this.clawMovesUp = !this.clawMovesUp;
        }
        this.wasPressed = this.grab.isPressed();

    }

    public void telemetryOutput() {

        telemetry.addData("Grabber", "\nClaw Moves Up: %b\nWas Pressed: %b\nMagnet: %.2f\nGrab: %b", this.clawMovesUp, this.wasPressed, this.magnet.getPower(), this.grab.isPressed());
    }
}
