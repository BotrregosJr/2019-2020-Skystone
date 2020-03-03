package org.firstinspires.ftc.teamcode.Albuquerque;

import com.deltarobotics9351.deltadrive.extendable.linearopmodes.mecanum.IMUPIDEncoderMecanumLinearOpMode;
import com.deltarobotics9351.deltadrive.motors.andymark.NeveRest_Classic_40;
import com.deltarobotics9351.deltamath.geometry.Rot2d;
import com.deltarobotics9351.pid.PIDCoefficients;

import org.firstinspires.ftc.teamcode.HardwareMecanum;
import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineAzul;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class Plantilla extends IMUPIDEncoderMecanumLinearOpMode {


    HardwareMecanum hws       = new HardwareMecanum(); // use the class created to define a Pushbot's hardware

    private OpenCvCamera phoneCam;
    private SkystonePatternPipelineAzul patternPipeline;

    int pattern;

    @Override
    public void _runOpMode(){
        setPID(new PIDCoefficients(0.0152, 0.0000152, 0));
        imuParameters.DEAD_ZONE = 0.2;

        encoderParameters.TICKS_PER_REV = NeveRest_Classic_40.TICKS_PER_REVOLUTION;
        encoderParameters.DRIVE_GEAR_REDUCTION = 2;
        encoderParameters.WHEEL_DIAMETER_INCHES = 4;

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


        waitForStart();

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

        rotate(Rot2d.fromDegrees(90),0.7,5);

        if(pattern ==  1){

            rotate(Rot2d.fromDegrees(90), 0.7, 5); //negative degrees for right
            forward(1,0.7,5);
            backwards(1,0.7,5);
            strafeLeft(1,0.7,5);
            strafeRight(1,0.7,5);


        } else  if(pattern ==  2){



        } else if(pattern == 3){



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
