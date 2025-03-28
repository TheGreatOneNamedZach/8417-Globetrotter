package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SubmersibleServo {


    Telemetry telemetry;
    CRServo axisMovement;
    TouchSensor posTouch;
    TouchSensor negTouch;
    TouchSensor limitTouch;

    public SubmersibleServo(CRServo axis, @NonNull OpMode opMode, TouchSensor PosTouch, TouchSensor NegTouch, TouchSensor LimitTouch) {
        this.axisMovement = axis;
        this.telemetry = opMode.telemetry;
        this.posTouch = PosTouch;
        this.negTouch = NegTouch;
        this.limitTouch = LimitTouch;
    }

    public void servoMovement () {
        if (this.limitTouch.isPressed() || (this.posTouch.isPressed() && this.negTouch.isPressed())) {
            this.axisMovement.setPower(0);

        } else if (this.posTouch.isPressed()) {
            this.axisMovement.setPower(0.05);

        } else if (this.negTouch.isPressed()) {
            this.axisMovement.setPower(-0.05);
        } else {
            this.axisMovement.setPower(0);
        }

    }
}
