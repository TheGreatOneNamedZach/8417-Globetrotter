package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.action.SubmersibleServo;

@TeleOp(name="Submersible Game")
public class Submersible extends OpMode {

    CRServo upAndDown = null;
    CRServo leftAndRight = null;
    CRServo magnetClaw = null;
    TouchSensor xAxisLimitTouch;
    TouchSensor zAxisLimitTouch;
    TouchSensor xAxisPosTouch;
    TouchSensor zAxisPosTouch;
    TouchSensor xAxisNegTouch;
    TouchSensor zAxisNegTouch;
    TouchSensor grab;

    org.firstinspires.ftc.teamcode.action.SubmersibleServo xAxis;
    org.firstinspires.ftc.teamcode.action.SubmersibleServo zAxis;


    @Override
    public void init() {
        upAndDown = hardwareMap.get(CRServo.class, "Up and Down");
        leftAndRight = hardwareMap.get(CRServo.class, "Left and Right");
        magnetClaw = hardwareMap.get(CRServo.class, "Magnet Claw");
        xAxisLimitTouch = hardwareMap.get(TouchSensor.class, "X Limit");
        xAxisPosTouch = hardwareMap.get(TouchSensor.class, "X Pos");
        xAxisNegTouch = hardwareMap.get(TouchSensor.class, "X Neg");
        zAxisLimitTouch = hardwareMap.get(TouchSensor.class, "Z Limit");
        zAxisPosTouch = hardwareMap.get(TouchSensor.class, "Z Pos");
        zAxisNegTouch = hardwareMap.get(TouchSensor.class, "Z Neg");
        grab = hardwareMap.get(TouchSensor.class, "Grab");
        xAxis = new SubmersibleServo(upAndDown, this, xAxisPosTouch, xAxisNegTouch, xAxisLimitTouch);
        zAxis = new SubmersibleServo(leftAndRight, this, zAxisPosTouch, zAxisNegTouch, zAxisLimitTouch);
    }

    @Override
    public void loop() {
        xAxis.servoMovement();
        zAxis.servoMovement();
    }
}
