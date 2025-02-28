package org.firstinspires.ftc.teamcode.init;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.action.TankDrive;
import org.firstinspires.ftc.teamcode.action.Vision;

@TeleOp(name="TeleOp")
public class Teleop extends OpMode {

    org.firstinspires.ftc.teamcode.action.TankDrive tankDrive = new TankDrive(); // this creates a new tank drive
    org.firstinspires.ftc.teamcode.action.Vision vision = new Vision();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        tankDrive.init(this); // this makes tank drive reference Teleop as an opMode
        vision.init(this);
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        tankDrive.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x); // this tells tank drive to drive
        tankDrive.telemetryOutput(); // this tells tank drive to output telemetry
        vision.telemetryOutput();
    }
}

