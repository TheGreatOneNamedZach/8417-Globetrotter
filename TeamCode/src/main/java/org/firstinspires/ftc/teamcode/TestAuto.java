package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")
public class TestAuto extends LinearOpMode {

    public DcMotor left_drive;
    public DcMotor right_drive;

    public ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        left_drive = hardwareMap.get(DcMotor.class, "Left Drive");
        right_drive = hardwareMap.get(DcMotor.class, "Right Drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        timer.reset();

        while(opModeIsActive() && (timer.seconds() <= 5.0 )) {

            left_drive.setPower(0.75);
            right_drive.setPower(0.75);

        }

        left_drive.setPower(0.0);
        right_drive.setPower(0.0);

    }
}
