package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/** <b>Controls axis movement for a single axis on the claw game.</b>
 * <p>
 *     This object controls a {@link CRServo} and 3 {@link TouchSensor TouchSensors}.
 *     Two of the three touch sensors move the servo forwards and backwards.
 *     The final touch sensor acts as a limit touch sensor and prevents crashing.
 * </p>
 * @see org.firstinspires.ftc.teamcode.init.Submersible Submersible
 * @see Grabber
 */
public class SubmersibleServo {


    Telemetry telemetry; // The telemetry class object
    CRServo axisMovement; // The servo used for axis movement
    TouchSensor posTouch; // The touch sensor that moves the servo forwards
    TouchSensor negTouch; // The touch sensor that moves the servo backwards
    TouchSensor limitTouch; // The touch sensor at the limit of the servo's movement boundary
    String displayName; // The name of the axis to display on telemetry

    /** <b>This is the constructor for {@link SubmersibleServo}.</b>
     * <p>
     *     This is the main constructor called for SubmersibleServo.
     * </p>
     * @param axis Servo that moves across this axis
     * @param opMode The current OpMode (pass in "this")
     * @param PosTouch The touch sensor to move the servo forwards
     * @param NegTouch The touch sensor to move the servo backwards
     * @param LimitTouch The limit touch sensor
     * @param displayName The name of the axis (e.g., x axis, y axis, z axis)
     * @see SubmersibleServo
     * @see Grabber
     * @see org.firstinspires.ftc.teamcode.init.Submersible Submersible
     */
    public SubmersibleServo(CRServo axis, @NonNull OpMode opMode, TouchSensor PosTouch, TouchSensor NegTouch, TouchSensor LimitTouch, String displayName) {

        // Overrides the null-defined local variables with axis-specific values
        this.axisMovement = axis;
        this.telemetry = opMode.telemetry;
        this.posTouch = PosTouch;
        this.negTouch = NegTouch;
        this.limitTouch = LimitTouch;
        this.displayName = displayName;
    }

    /** Controls the servo movement on the axis.
     * <p>
     *     This method interacts with three touch sensors to determine how the servo should behave when the user wants to move along this axis.
     * </p>
     */
    public void servoMovement () {

        // IF the limit sensor is pressed...
        //  ...OR the positive touch sensor AND the negitive touch sensor is pressed...
        if (this.limitTouch.isPressed() || (this.posTouch.isPressed() && this.negTouch.isPressed())) {
            this.axisMovement.setPower(0);

        } else if (this.posTouch.isPressed()) {
            this.axisMovement.setPower(0.30);

        } else if (this.negTouch.isPressed()) {
            this.axisMovement.setPower(-0.30);
        } else {
            this.axisMovement.setPower(0);
        }

    }

    /** Outputs relevant information about this axis to the driver screen. */
    public void telemetryOutput() {

        this.telemetry.addData(this.displayName, "\n" +
                "Servo Speed: %.2f\n" +
                "Move Forwards: %b\n" +
                "Move Backwards: %b\n" +
                "Limit Sensor Touched: %b",
                this.axisMovement.getPower(), this.posTouch.isPressed(), this.negTouch.isPressed(), this.limitTouch.isPressed());
    }
}
