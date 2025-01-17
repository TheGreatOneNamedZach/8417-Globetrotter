package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Robot: Auto Drive By Time", group="Robot")
public class TestAuto extends LinearOpMode {

    public DcMotor left_drive; // This is the left drive train motor
    public DcMotor right_drive; // This is the right drive train motor

    public ElapsedTime timer = new ElapsedTime(); // this creates a new timer

    @Override
    public void runOpMode() throws InterruptedException {

        // This assigns the motors to the hardware map
        left_drive = hardwareMap.get(DcMotor.class, "Left Drive");
        right_drive = hardwareMap.get(DcMotor.class, "Right Drive");

        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart(); // This means it is waiting for the code to start to run this line

        timer.reset(); // This resets the timer

        // this makes the following code run for 5 seconds
        while(opModeIsActive() && (timer.seconds() <= 5.0 )) {

            // this sets the motors to 75% power
            left_drive.setPower(0.75);
            right_drive.setPower(0.75);

        }

        timer.reset(); // This resets the timer

        // this makes the following code run for 2 seconds
        while(opModeIsActive() && (timer.seconds() <= 2.0 )) {

            // this sets the motors to 0% power
            left_drive.setPower(0.00);
            right_drive.setPower(0.00);

        }

        timer.reset(); // This resets the timer

        // this makes the following code run for 5 seconds
        while(opModeIsActive() && (timer.seconds() <= 5.0 )) {

            // this sets the motors to -75% power
            left_drive.setPower(-0.75);
            right_drive.setPower(-0.75);

        }
    }
}
