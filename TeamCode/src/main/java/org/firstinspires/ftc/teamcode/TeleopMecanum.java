/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareMecanum;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Teleop", group="Pushbot")
//@Disabled
public class TeleopMecanum extends OpMode{

    /* Declare OpMode members. */
    HardwareMecanum hws       = new HardwareMecanum(); // use the class created to define a Pushbot's hardware


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        hws.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //

        hws.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hws.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hws.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hws.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double turbo;





        if (gamepad1.y) {
            hws.turbo = 0.2;
            turbo = 0.2;
        } else {
            hws.turbo = 1;
            turbo = 1;
        }

/**
        if (gamepad1.dpad_down){
            hws.skystoneleft.setPosition(0);
            hws.skystoneRight.setPosition(1);
        } else if (gamepad1.dpad_up){
            hws.skystoneleft.setPosition(1);
            hws.skystoneRight.setPosition(0.5);
        }
*/
            telemetry.addData("Turboooooooo prro", turbo);
            // Sets the joystick values to variables for better math understanding
            // The Y axis goes
            hws.y = gamepad1.left_stick_y;
            hws.x = -gamepad1.left_stick_x;
            hws.rot = -gamepad1.right_stick_x;
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rot = gamepad1.right_stick_x;

            // sets the math necessary to control the motors to variables
            // The left stick controls the axial movement
            // The right sick controls the rotation

            hws.frontRightPower = -hws.y + hws.rot + hws.x;
            hws.backRightPower = -hws.y + hws.rot - hws.x;
            hws.frontLeftPower = -hws.y - hws.rot - hws.x;
            hws.backLeftPower = -hws.y - hws.rot + hws.x;
            double frontRightPower  = y + rot + x;
            double backRightPower   = y + rot - x;
            double frontLeftPower   = y - rot - x;
            double backLeftPower    = y - rot + x;

            // Normalize the values so neither exceed +/- 1.0
            hws.max = Math.max(Math.abs(hws.frontRightPower), Math.max(Math.abs(hws.backRightPower),
                    Math.max(Math.abs(hws.frontLeftPower), Math.abs(hws.backLeftPower))));
            double max = Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backRightPower),
                    Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower))));
            if (hws.max > 1.0)
            {
                hws.frontRightPower /= hws.max;
                hws.backRightPower /= hws.max;
                hws.frontLeftPower /= hws.max;
                hws.backLeftPower /= hws.max;
            }
            if (max > 1.0)
            {
                frontRightPower /= max;
                backRightPower  /= max;
                frontLeftPower  /= max;
                backLeftPower   /= max;
            }

            // sets the speed for the motros with the turbo multiplier
//
            hws.frontRightPower *= hws.turbo;
            hws.backRightPower *= hws.turbo;
            hws.frontLeftPower *= hws.turbo;
            hws.backLeftPower *= hws.turbo;
            frontRightPower *= turbo;
            backRightPower  *= turbo;
            frontLeftPower  *= turbo;
            backLeftPower   *= turbo;

            hws.frontRight.setPower(hws.frontRightPower);
            hws.backRight.setPower(hws.backRightPower);
            hws.frontLeft.setPower(hws.frontLeftPower);
            hws.backLeft.setPower(hws.backLeftPower);
            telemetry.addData("front right:", frontRightPower);
            telemetry.addData("back right:", backRightPower);
            telemetry.addData("front left:", frontLeftPower);
            telemetry.addData("back left:", backLeftPower);

            telemetry.addData("skystone:", hws.skystoneleft.getPosition());
            telemetry.addData("foundation:", hws.foundation.getPosition());

            // Send telemetry message to signify robot running;
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // run until the end of the match (driver presses STOP)

        // Run wheels in POV mode (note: The joystick goes negative when pushed forwards, so negate it)
        // In this mode the Left stick moves the robot fwd and back, the Right stick turns left and right.
        // This way it's also easy to just drive straight, or just turn.

        //StartA

        if (gamepad1.right_bumper){                  //set power elevador
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
        } else if (gamepad1.left_bumper){
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
        } else {
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
        }
/*
            if(gamepad1.dpad_down){
                hws.skystoneleft.setPosition(.3);
            } else if (gamepad1.dpad_up){
                hws.skystoneleft.setPosition(0);
            }
*/
        if(gamepad1.dpad_right){
            hws.foundation.setPosition(.4);
        } else if (gamepad1.dpad_left){
            hws.foundation.setPosition(0);
        }


        //startB

        if (gamepad2.x){
            hws.transicion.setPower(1);
        } else if(gamepad2.a){
            hws. transicion. setPower(-1);
        } else if(gamepad2.b){
            hws. transicion. setPower(.5);
        }else{
            hws.transicion.setPower(0);
        }

        if (gamepad2.dpad_up){
            hws.slider.setPower(1);
        } else if (gamepad2.dpad_down){
            hws.slider.setPower(-1);
        } else if (gamepad2.right_bumper){
            hws.slider.setPower(.4);
        } else{
            hws.slider.setPower(0);
        }


        if (gamepad2.left_bumper){
            hws.outtake.setPower(1);

        }else{
            hws.outtake.setPower(0);
        }

        if (gamepad2.y){
            hws.capstone.setPosition(0);
        } else if (gamepad2.dpad_right){
            hws.capstone.setPosition(0.8);
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
