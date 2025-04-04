package org.firstinspires.ftc.teamcode.action;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/** This class controls the grabber object on the claw game.
 * <p>
 *     This class operates one {@link CRServo} and 1 {@link TouchSensor}.
 *     The button to control up/down movement is a toggle button.
 *     This means the first press is down, and the second press is up.
 * </p>
 * @see SubmersibleServo
 * @see org.firstinspires.ftc.teamcode.init.Submersible Submersible
 */
public class Grabber {

    Telemetry telemetry; // The telemetry class object
    CRServo magnet; // The servo that controls the grabber height
    TouchSensor grab; // The toggle button for up/down movement
    Boolean clawMovesUp = true; // Is the grabber moving up?
    Boolean wasPressed = false; // Was the toggle button pressed on the last loop iteration?

    /** <b>This is the constructor for {@link Grabber}.</b>
     * <p>
     *     This is the main constructor for Grabber.
     * </p>
     * @param grabber Servo that moves the grabber up and down
     * @param opMode The current OpMode (pass in "this")
     * @param grabButton The touch sensor to move the grabber up/down
     * @see Grabber
     * @see SubmersibleServo
     * @see org.firstinspires.ftc.teamcode.init.Submersible Submersible
     */
    public Grabber(CRServo grabber, @NonNull OpMode opMode, TouchSensor grabButton) {
        this.magnet = grabber;
        this.grab = grabButton;
        this.telemetry = opMode.telemetry;
    }

    /** <b>Controls the servo movement for the {@link Grabber}.</b>
     * <p>
     *     This method interacts with a touch sensor to determine how the servo should behave when the user wants to move.
     *     The touch sensor to move is used to change movement directions.
     *     Press the touch sensor to switch directions.
     *     Press and hold the touch sensor to move.
     * </p>
     */
    public void clawMovement () {

        /* Sets the power to the button state (0 or 1) multiplied by the direction and speed (down speed or up speed)
            For example, if the touch sensor is NOT pressed and the claw is moving up...
                0 * 0.3 = 0% speed
            If the touch sensor is pressed and the claw is moving up...
                1 * 0.3 = 30% speed
            If the touch sensor is pressed and the claw is moving down...
                1 * -0.3 = -30% speed
         */
        this.magnet.setPower(grab.getValue() * (clawMovesUp ? 0.30 : -0.30));

        // If the touch sensor was pressed the last loop iteration
        // AND the touch sensor was NOT pressed this loop iteration...
        if (this.wasPressed && !this.grab.isPressed()) {
            this.clawMovesUp = !this.clawMovesUp; // ...then swap directions
        }

        this.wasPressed = this.grab.isPressed(); // Update the previous loop iteration tracker
    }

    /** <b>Outputs relevant information about the {@link Grabber} to the driver screen.</b> */
    public void telemetryOutput() {

        telemetry.addData("Grabber", "\n" +
                "Claw Moves Up: %b\n" +
                "Was Pressed: %b\n" +
                "Magnet: %.2f\n" +
                "Grab: %b",
                this.clawMovesUp, this.wasPressed, this.magnet.getPower(), this.grab.isPressed());
    }
}
