package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp")
public class Teleop extends OpMode {
    public DcMotor left_drive; // This is the left drive train motor
    public DcMotor right_drive; // This is the right drive train motor
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // This assigns the motors to the hardware map
        left_drive = hardwareMap.get(DcMotor.class, "Left Drive");
        right_drive = hardwareMap.get(DcMotor.class, "Right Drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE); // This reverses the left motor
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        double rotationSpeed = 0.75; // This changes the rotation speed in percent

        // this calculates the power the motors run at when moving and rotating at the same time
        double leftPower = Range.clip(-gamepad1.left_stick_y + (gamepad1.right_stick_x * rotationSpeed), -1.0, 1.0);
        double rightPower = Range.clip(-gamepad1.left_stick_y - (gamepad1.right_stick_x * rotationSpeed), -1.0, 1.0);

        // This tells the motors how fast to go
        left_drive.setPower(leftPower);
        right_drive.setPower(rightPower);

        telemetry.addData("Left Drive", "%.2f", leftPower);
        telemetry.addData("Right Drive", "%.2f", rightPower);
    }
}
