package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineAzul;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Autonomo Skystone Azul", group="Final")
public class AutonomoSkystoneAzul extends LinearOpMode {

    private OpenCvCamera phoneCam;
    private SkystonePatternPipelineAzul patternPipeline;

    int pattern = 0;

    HardwareMecanum robot   = new HardwareMecanum();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        //falta hacer t.odo lo del hardware, no se como lo tengas hecho asi que lo dejo asi.

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

        telemetry.addData("[/!\\]", "Recuerden posicionar correctamente el robot, con los dos rectangulos que se ven en la camara apuntando justo hacia las dos ultimas stones de la quarry (las mas cercanas a el skybridge)\n\nGOOO DELTA ROBOTICS!!!");
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

        if(pattern == 1){

            //PATTERN A (1)

        }else if(pattern == 2){

            //PATTERN B (2)

        }else if(pattern == 3){

            //PATTERN C (3)

        }else{
            //en teoria este codigo nunca se deberia de ejecutar, pero por si las dudas...
            telemetry.addData("[ERROR]", "No se que ha pasado ni como has llegado hasta aqui. Lo siento =(");
            telemetry.update();
            while(opModeIsActive());
        }
    }


}