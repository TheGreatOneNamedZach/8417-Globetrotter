package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp")
public class Teleop extends OpMode {
    public DcMotor left_drive;
    public DcMotor right_drive;
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        left_drive = hardwareMap.get(DcMotor.class, "Left Drive");
        right_drive = hardwareMap.get(DcMotor.class, "Right Drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");
        double leftPower = Range.clip(-gamepad1.left_stick_y + gamepad1.right_stick_x, -1.0, 1.0);
        double rightPower = Range.clip(-gamepad1.left_stick_y - gamepad1.right_stick_x, -1.0, 1.0);


        left_drive.setPower(leftPower);
        right_drive.setPower(rightPower);
        telemetry.addData("Left Drive", "%.2f", leftPower);
        telemetry.addData("Right Drive", "%.2f", rightPower);
    }
}
