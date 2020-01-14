package org.firstinspires.ftc.teamcode.AutoNacional;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareMecanum;
import org.firstinspires.ftc.teamcode.ImuTurn;
import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineAzul;
import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineRojo;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Autonomo Skystone Rojo imu", group="Final")
public class autonomoSkystoneRojoIMUEncoder extends LinearOpMode {

    private OpenCvCamera phoneCam;
    private SkystonePatternPipelineRojo patternPipeline;

    int pattern = 0;

    HardwareMecanum robot   = new HardwareMecanum();   // Use a Pushbot's hardware
    ImuTurn imu;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        imu = new ImuTurn(robot, telemetry, this, hardwareMap);

        imu.initIMU();
        while(!imu.isIMUCalibrated()){

        }
        //falta hacer t.odo lo del hardware, no se como lo tengas hecho asi que lo dejo asi.




        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset.
        telemetry.addData("Path0",  "Starting at %7d :%7d : %7d :%7d",
                robot.frontLeft.getCurrentPosition(),
                robot.frontRight.getCurrentPosition(),
                robot.backRight.getCurrentPosition(),
                robot.backLeft.getCurrentPosition());
        telemetry.update();



        //obtenemos la id del monitor de la camara (la vista de la camara que se vera desde el robot controller)
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        //creamos la camara de OpenCV
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.FRONT, cameraMonitorViewId);

        //la inicializamos
        phoneCam.openCameraDevice();

        //creamos la pipeline
        patternPipeline = new SkystonePatternPipelineRojo();

        //definimos la pipeline para la camara
        phoneCam.setPipeline(patternPipeline);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        telemetry.addData("[/!\\]", "Francesco virgoliniiii  \n\nla maquina mas velos de la tote italia");
        telemetry.update();

        //esperamos que el usuario presione <play> en la driver station
        waitForStart();

        //si el pattern es 0 significa que no se ha posicionado bien el robot, ya que no se logro detectar un pattern.
        if(patternPipeline.pattern == 0){
            telemetry.addData("[ERROR]", "Se ha posicionado de forma erronea el robot...");
            telemetry.update();
            while(opModeIsActive());
        }

        pattern = patternPipeline.pattern;

        phoneCam.closeCameraDevice(); //apagamos la camara ya que no es necesaria a partir de este punto.

        telemetry.addData("Pattern", pattern); //mandamos mensaje telemetry para reportar que ya se detecto un patron
        telemetry.update();

        if(pattern == 1){//posicion 3





        }else if(pattern == 2){ //posicion 2





        }else if(pattern == 3){//posicion 1

            imu.rotate(80,0.3);
            sleep(500);
          //  encoderDrive(0.7,-2,-2,-2,-2,4);
            encoderDrive(0.3,5,5,5,5,4);
            robot.skystoneRight.setPosition(1);
            sleep(1000);





        }else{
            //en teoria este codigo nunca se deberia de ejecutar, pero por si las dudas...
            telemetry.addData("[ERROR]", "No se que ha pasado ni como has llegado hasta aqui. Lo siento :(");
            telemetry.update();
            while(opModeIsActive());
        }
    }

    public void enfrente (double frontRightPower, double backRightPower, double frontLeftPower, double backLeftPower, long tiempo){
        robot.frontRight.setPower(frontRightPower);
        robot.backRight.setPower(backRightPower);
        robot.frontLeft.setPower(frontLeftPower);
        robot.backLeft.setPower(backLeftPower);
        sleep(tiempo);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void atras (double frontRightPower, double backRightPower, double frontLeftPower, double backLeftPower, long tiempo){
        robot.frontRight.setPower(-frontRightPower);
        robot.backRight.setPower(-backRightPower);
        robot.frontLeft.setPower(-frontLeftPower);
        robot.backLeft.setPower(-backLeftPower);
        sleep(tiempo);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void izquierda (double frontRightPower, double backRightPower, double frontLeftPower, double backLeftPower, long tiempo){
        robot.frontRight.setPower(frontRightPower);
        robot.backRight.setPower(-backRightPower);
        robot.frontLeft.setPower(-frontLeftPower);
        robot.backLeft.setPower(backLeftPower);
        sleep(tiempo);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void derecha (double frontRightPower, double backRightPower, double frontLeftPower, double backLeftPower, long tiempo){
        robot.frontRight.setPower(-frontRightPower);
        robot.backRight.setPower(backRightPower);
        robot.frontLeft.setPower(frontLeftPower);
        robot.backLeft.setPower(-backLeftPower);
        sleep(tiempo);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void  turnRight (long time,double power){
        robot.frontRight.setPower(-power);
        robot.backRight.setPower(-power);
        robot.frontLeft.setPower(power);
        robot.backLeft.setPower(power);
        sleep(time);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void  turnLeft (long time,double power){
        robot.frontRight.setPower(power);
        robot.backRight.setPower(power);
        robot.frontLeft.setPower(-power);
        robot.backLeft.setPower(-power);
        sleep(time);
        robot.frontRight.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        sleep(500);
    }
    public void meter (){
        robot.intakeLeft.setPower(1);
        robot.intakeRight.setPower(1);

    }

    public void sacar (){
        robot.intakeLeft.setPower(-1);
        robot.intakeRight.setPower(-1);

    }
    public void nochupar (){
        robot.intakeLeft.setPower(0);
        robot.intakeRight.setPower(0);

    }

    public void encoderDrive(double speed,
                             double frontLeftInches, double frontRightInches,
                             double backLeftInches, double backRightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(frontLeftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int)(frontRightInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(backLeftInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.frontRight.getCurrentPosition() + (int)(backRightInches * COUNTS_PER_INCH);


            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);

            // Turn On RUN_TO_POSITION
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() || robot.backRight.isBusy() || robot.frontRight.isBusy() || robot.backLeft.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d :%7d :%7d", newFrontLeftTarget,  newFrontRightTarget, newBackLeftTarget, newBackRightTarget );
                telemetry.addData("Path2",  "Running at %7d :%7d : %7d :%7d",
                        robot.frontRight.getCurrentPosition(),
                        robot.frontLeft.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.backLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
            robot.frontLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(150);   // optional pause after each move

            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }


}