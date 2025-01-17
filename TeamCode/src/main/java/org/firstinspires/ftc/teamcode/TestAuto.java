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


        tankDrive(0.75, 0.75, 5.0); // this makes the robot go forward for 5 seconds
        tankDrive(0.0, 0.0, 2.0); // this makes the robot stop for 2 seconds
        tankDrive(-0.75, -0.75, 5.0); // this makes the robot go backwards for 5 seconds


    }

    public void tankDrive(double leftPower, double rightPower, double seconds) {

        timer.reset(); // this resets the timer
        // this makes the following code run for a specified amount of seconds
        while(opModeIsActive() && (timer.seconds() <= seconds )) {

            // this sets the motors to a specified power
            left_drive.setPower(leftPower);
            right_drive.setPower(rightPower);

        }

    }

}
