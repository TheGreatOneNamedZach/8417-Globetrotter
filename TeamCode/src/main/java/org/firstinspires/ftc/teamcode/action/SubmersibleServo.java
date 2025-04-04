package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/** <b>Controls axis movement for a single axis on the claw game.</b>
 * <p>
 *     This object controls a {@link CRServo} and 3 {@link TouchSensor TouchSensors}.
 *     Two of the three touch sensors move the servo forwards and backwards.
 *     The final touch sensor acts as a limit touch sensor and prevents crashing.
 *     The "backwards" movement of the servo should move towards the limit touch sensor.
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
    double axisSpeedMult; // The axis-specific speed multiplier
    ElapsedTime timer; // The axis timer for counting accumulated time
    double timeFromHome; // The time traveled from home (the limit sensor)
    boolean wasMoving; // Tracks if the servo was moving in the previous loop iteration
    boolean wasMovingPositive; // Tracks the direction the servo was last moving

    /** <b>This is the constructor for {@link SubmersibleServo}.</b>
     * <p>
     *     This is the main constructor for SubmersibleServo.
     * </p>
     * @param axis Servo that moves across this axis
     * @param opMode The current OpMode (pass in "this")
     * @param PosTouch The touch sensor to move the servo forwards
     * @param NegTouch The touch sensor to move the servo backwards
     * @param LimitTouch The limit touch sensor
     * @param displayName The name of the axis (e.g., x axis, y axis, z axis)
     * @param axisSpeedMult The axis-specific speed multiplier
     * @see SubmersibleServo
     * @see Grabber
     * @see org.firstinspires.ftc.teamcode.init.Submersible Submersible
     */
    public SubmersibleServo(CRServo axis, @NonNull OpMode opMode, TouchSensor PosTouch, TouchSensor NegTouch, TouchSensor LimitTouch, String displayName, double axisSpeedMult) {

        // Overrides the null-defined local variables with axis-specific values
        this.axisMovement = axis;
        this.telemetry = opMode.telemetry;
        this.posTouch = PosTouch;
        this.negTouch = NegTouch;
        this.limitTouch = LimitTouch;
        this.displayName = displayName;
        this.axisSpeedMult = axisSpeedMult;

        this.timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS); // Creates a new timer to count elapsed time traveled from home (limit sensor)

        // Sets default values
        this.timeFromHome = 0;
        this.wasMoving = false;
        this.wasMovingPositive = true;
    }

    /** <b>Controls the servo movement on the axis.</b>
     * <p>
     *     This method interacts with three touch sensors to determine how the servo should behave when the user wants to move along this axis.
     * </p>
     */
    public void servoMovement () {

        // IF the limit sensor is pressed AND the positive touch sensor is NOT pressed...
        //  ...OR the positive touch sensor AND the negative touch sensor is pressed...
        if ((this.limitTouch.isPressed() && !this.posTouch.isPressed()) || (this.posTouch.isPressed() && this.negTouch.isPressed())) {
            this.axisMovement.setPower(0); // Set the power to 0%

            if (this.limitTouch.isPressed()) {
                this.timeFromHome = 0.0;
            } else if (wasMoving) {
                this.timeFromHome += this.wasMovingPositive ? this.timer.time() : -1 * this.timer.time();
            }

        } else if (this.posTouch.isPressed()) {
            // ...else IF the positive touch sensor is pressed...

            this.axisMovement.setPower(1 * this.axisSpeedMult); // Set the power to 100%

            if (!this.wasMoving) {
                this.timer.reset();
            } else {
                this.wasMovingPositive = true;
            }
        } else if (this.negTouch.isPressed()) {
            // ...else IF the negative touch sensor is pressed...

            this.axisMovement.setPower(-1 * this.axisSpeedMult); // Set the power to -100%

            if (!this.wasMoving) {
                this.timer.reset();
            } else {
                this.wasMovingPositive = false;
            }
        } else {
            // ...else no buttons are being pressed.

            this.axisMovement.setPower(0); // Set the power to 0%

            if (this.wasMoving) {
                this.timeFromHome += this.wasMovingPositive ? this.timer.time() : -1 * this.timer.time();
            }
        }

        // Updates if servo was moving last iteration
        // False IF AND ONLY IF the servo is NOT moving
        this.wasMoving = this.axisMovement.getPower() != 0.0;
    }

    /** <b>Outputs relevant information about this axis to the driver screen.</b> */
    public void telemetryOutput() {

        this.telemetry.addData(this.displayName, "\n" +
                "Servo Speed: %.2f\n" +
                "Move Forwards: %b\n" +
                "Move Backwards: %b\n" +
                "Limit Sensor Touched: %b\n" +
                "Time From Home: %.2f\n" +
                "Timer: %.2f\n" +
                "Was Moving: %b\n" +
                "Was Moving Positive: %b",
                this.axisMovement.getPower(), this.posTouch.isPressed(), this.negTouch.isPressed(), this.limitTouch.isPressed(), this.timeFromHome, this.timer.time(), this.wasMoving, this.wasMovingPositive);
    }
}
