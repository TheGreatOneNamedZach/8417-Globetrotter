package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TankDrive {

    double leftPower;
    double rightPower;
    Telemetry telemetry;
    DcMotor left_drive;
    DcMotor right_drive;

    public static double rotationSpeed = 0.75; // This changes the rotation speed in percent

    public void init(@NonNull OpMode opMode) {

        HardwareMap hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        // This assigns the motors to the hardware map
        left_drive = hardwareMap.get(DcMotor.class, "Left Drive");
        right_drive = hardwareMap.get(DcMotor.class, "Right Drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE); // This reverses the left motor
    }

    /** Moves the robot along the ground.
     * @param movingJoystick Joystick used for forward/backward movement.
     * @param rotatingJoystick Joystick used for rotating movement.
     */
    public void drive(double movingJoystick, double rotatingJoystick) {


        // this calculates the power the motors run at when moving and rotating at the same time
        leftPower = Range.clip(movingJoystick + (rotatingJoystick * rotationSpeed), -1.0, 1.0);
        rightPower = Range.clip(movingJoystick - (rotatingJoystick * rotationSpeed), -1.0, 1.0);

        // This tells the motors how fast to go
        left_drive.setPower(leftPower);
        right_drive.setPower(rightPower);
    }

    public void telemetryOutput() {

        telemetry.addData("Left Drive", "%.2f", leftPower);
        telemetry.addData("Right Drive", "%.2f", rightPower);

    }
}
