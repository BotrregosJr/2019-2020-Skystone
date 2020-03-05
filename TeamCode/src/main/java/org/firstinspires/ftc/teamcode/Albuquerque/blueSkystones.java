package org.firstinspires.ftc.teamcode.Albuquerque;

import com.deltarobotics9351.deltadrive.extendable.linearopmodes.mecanum.IMUPIDEncoderMecanumLinearOpMode;
import com.deltarobotics9351.deltadrive.motors.andymark.NeveRest_Classic_40;
import com.deltarobotics9351.deltadrive.utils.Invert;
import com.deltarobotics9351.deltamath.geometry.Rot2d;
import com.deltarobotics9351.pid.PIDCoefficients;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.HardwareMecanum;
import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineAzul;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;


@Autonomous(name="Skystone azul", group="Final")

public class blueSkystones extends IMUPIDEncoderMecanumLinearOpMode {


    HardwareMecanum hws       = new HardwareMecanum(); // use the class created to define a Pushbot's hardware

    private OpenCvCamera phoneCam;
    private SkystonePatternPipelineAzul patternPipeline;

    int pattern = 0;

    @Override

    public void _runOpMode(){
        setWheelsInvert(Invert.LEFT_SIDE);

        setPID(new PIDCoefficients(0.005, 0.0000145, 0));
        imuParameters.DEAD_ZONE = 0.10;

        encoderParameters.TICKS_PER_REV = NeveRest_Classic_40.TICKS_PER_REVOLUTION;
        encoderParameters.DRIVE_GEAR_REDUCTION = 2;
        encoderParameters.WHEEL_DIAMETER_INCHES = 4;

        //obtenemos la id del monitor de la camara (la vista de la camara que se vera desde el robot controller)
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        //creamos la camara de OpenCV
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.FRONT, cameraMonitorViewId);

        //la inicializamos
        phoneCam.openCameraDevice();

        //creamos la pipeline
        patternPipeline = new SkystonePatternPipelineAzul();

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

        //si el pattern es 0 significa que no se ha posicionado bien el robot, ya que no se logro detectar un pattern.
        if(patternPipeline.pattern == 0){
            telemetry.addData("[ERROR]", "Se ha posicionado de forma erronea el robot...");
            telemetry.update();
            while(opModeIsActive());
        }



        if(pattern ==  1){

            forward(4,0.6,3);
            rotate(Rot2d.fromDegrees(92), 1, 2.5); //negative degrees for right
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(10,0.3,5);
            backwards(3.5,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(72),1,2.5);
            forward(14.5,0.6,5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);

            //segunda
            backwards(18,0.5,5);
            rotate(Rot2d.fromDegrees(-105),1,2.5);
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(2.5,0.3,5);
            backwards(3,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);


            rotate(Rot2d.fromDegrees(-65),1,2.5);

            backwards(17,0.5,5);

            //aventar
            rotate(Rot2d.fromDegrees(70),1,2.5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(-80),1,3);
            forward(2.5,1,2);




        } else  if(pattern ==  2){

            forward(2,0.6,2);
            rotate(Rot2d.fromDegrees(92), 1, 2.5); //negative degrees for right
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(10,0.3,5);
            backwards(3.5,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(72),1,2.5);
            forward(12,0.6,5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);

            //segunda
            backwards(17,0.5,2);
            rotate(Rot2d.fromDegrees(-110),1,2.5);
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(7,0.3,5);
            backwards(6,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);


            rotate(Rot2d.fromDegrees(-50),1,2.5);

            backwards(15,0.5,5);

            //aventar
            rotate(Rot2d.fromDegrees(70),1,2.5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(-80),1,3);
            forward(2.5,1,2);


        } else if(pattern == 3){
            rotate(Rot2d.fromDegrees(92), 1, 2.5); //negative degrees for right

            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(10,0.3,5);
            backwards(3,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(75),1,2.5);
            forward(10,0.6,5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            backwards(13,0.5,2);
            rotate(Rot2d.fromDegrees(-120),1,2.5);
            hws.intakeLeft.setPower(1);
            hws.intakeRight.setPower(1);
            forward(5,0.3,5);
            backwards(5,1,2);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(-50),1,2.5);
            backwards(17,0.5,2);
            rotate(Rot2d.fromDegrees(70),1,2.5);
            hws.intakeLeft.setPower(-1);
            hws.intakeRight.setPower(-1);
            sleep(500);
            hws.intakeLeft.setPower(0);
            hws.intakeRight.setPower(0);
            rotate(Rot2d.fromDegrees(-70),1,2.5);
            forward(3,1,2);








        } else {
            //en teoria este codigo nunca se deberia de ejecutar, pero por si las dudas...
            telemetry.addData("[ERROR]", "No se que ha pasado ni como has llegado hasta aqui. Lo siento =(");
            telemetry.update();
            while(opModeIsActive());
        }



    }

    @Override
    public void setup(){

        hws.init(hardwareMap);

        frontLeft = hws.frontLeft;
        frontRight=hws.frontRight;
        backLeft =hws.backLeft;
        backRight= hws.backRight;
    }


}
