package org.firstinspires.ftc.teamcode.init;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.action.Grabber;
import org.firstinspires.ftc.teamcode.action.SubmersibleServo;

/** <b>Operates the claw game designed for the 2025 FTC World Competition.</b>
 * <p>
 *     This OpMode controls the various mechanisms on the claw game.
 *     First, it uses the {@link SubmersibleServo} object class to control axis movement along 2 axis.
 *     Second, this OpMode uses the {@link Grabber} object class to control grabbing an object from within the game.
 * </p>
 * @see SubmersibleServo
 * @see Grabber
 */
@TeleOp(name="Submersible Game")
public class Submersible extends OpMode {

    CRServo upAndDown = null; // Servo that controls up and down movement
    CRServo leftAndRight = null; // Servo that controls left and right movement
    CRServo magnetClaw = null; // Servo that controls the magnetic claw
    TouchSensor xAxisLimitTouch; // The limit touch sensor for the X axis
    TouchSensor zAxisLimitTouch; // The limit touch sensor for the Z axis
    TouchSensor xAxisPosTouch; // The forward button
    TouchSensor zAxisPosTouch; // The left button
    TouchSensor xAxisNegTouch; // The down button
    TouchSensor zAxisNegTouch; // The right button
    TouchSensor grab; // The grab button

    org.firstinspires.ftc.teamcode.action.SubmersibleServo xAxis; // The X axis object
    org.firstinspires.ftc.teamcode.action.SubmersibleServo zAxis; // The Z axis object
    org.firstinspires.ftc.teamcode.action.Grabber grabber; // The grabber/claw object


    @Override
    public void init() {

        // Hardware maps the servos & sensors
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

        // Constructs the objects
        xAxis = new SubmersibleServo(upAndDown, this, xAxisPosTouch, xAxisNegTouch, xAxisLimitTouch, "X Axis", 1);
        zAxis = new SubmersibleServo(leftAndRight, this, zAxisPosTouch, zAxisNegTouch, zAxisLimitTouch, "Z Axis", 0.75);
        grabber = new Grabber(magnetClaw, this, grab);
    }

    @Override
    public void loop() {

        // Moves things
        xAxis.servoMovement();
        zAxis.servoMovement();
        grabber.clawMovement();

        // Outputs telemetry
        grabber.telemetryOutput();
        xAxis.telemetryOutput();
        zAxis.telemetryOutput();
    }
}
