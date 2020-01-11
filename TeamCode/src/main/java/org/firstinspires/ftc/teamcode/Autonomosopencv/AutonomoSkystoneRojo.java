package org.firstinspires.ftc.teamcode.Autonomosopencv;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareMecanum;
import org.firstinspires.ftc.teamcode.pipeline.SkystonePatternPipelineRojo;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
@Disabled
@Autonomous(name="Autonomo Skystone Rojo", group="Final")
public class AutonomoSkystoneRojo extends LinearOpMode {

    private OpenCvCamera phoneCam;
    private SkystonePatternPipelineRojo patternPipeline;

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
        patternPipeline = new SkystonePatternPipelineRojo();

        //definimos la pipeline para la camara
        phoneCam.setPipeline(patternPipeline);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        telemetry.addData("[/!\\]", "Francesco virgolini\n\nLa maquina mas veloz de la tote italia!!!");
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
            turnLeft(400,1);
            atras(0.2,0.2,0.2,0.2,1500);
            sleep(250);
            atras(0.7,0.7,0.7,0.7,550);
            izquierda(0.7,0.7,0.7,0.7,200);
            enfrente(0.5,0.5,0.5,0.5,600);
            robot.skystoneleft.setPosition(0);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,700);
            turnRight(600,0.6);
            enfrente(0.7,0.7,0.7,0.7,700);
            robot.skystoneleft.setPosition(1);
            sleep(500);
            turnLeft(550,0.6);
            izquierda(1,1,1,1,2500);
            atras(0.7,0.7,0.7,0.7,550);
            derecha(.7,.7,.7,.7,300);
            enfrente(0.5,0.5,0.5,0.5,700);
            robot.skystoneleft.setPosition(0);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,700);
            turnRight(500,0.8);
            enfrente(0.7,0.7,0.7,0.7,1000);
            robot.skystoneleft.setPosition(1);
            sleep(500);
            atras(0.5,.5,.5,.5,300);




            //PATTERN A (1)

        }else if(pattern == 2){
            turnLeft(400,1);
            atras(0.2,0.2,0.2,0.2,1500);
            sleep(250);
            atras(0.7,0.7,0.7,0.7,550);
            derecha(0.7,0.7,0.7,0.7,350);
            enfrente(0.5,0.5,0.5,0.5,600);
            robot.skystoneleft.setPosition(0);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,400);
            turnRight(600,0.6);
            enfrente(0.7,0.7,0.7,0.7,500);
            robot.skystoneleft.setPosition(1);
            sleep(500);
            turnLeft(650,0.6);
            izquierda(1,1,1,1,2800);
            atras(0.7,0.7,0.7,0.7,550);
            derecha(.7,.7,.7,.7,700);
            enfrente(0.5,0.5,0.5,0.5,700);
            robot.skystoneleft.setPosition(0);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,700);
            turnRight(500,0.8);
            enfrente(0.7,0.7,0.7,0.7,1000);
            robot.skystoneleft.setPosition(0.5);
            sleep(500);
            atras(0.5,.5,.5,.5,400);

            //PATTERN B (2)

        }else if(pattern == 3){
            turnLeft(400,1);
            atras(0.2,0.2,0.2,0.2,1500);
            sleep(250);
            atras(0.7,0.7,0.7,0.7,550);
            izquierda(0.7,0.7,0.7,0.7,200);
            enfrente(0.5,0.5,0.5,0.5,600);
            robot.skystoneRight.setPosition(1);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,700);
            turnRight(600,0.6);
            enfrente(0.7,0.7,0.7,0.7,700);
            robot.skystoneRight.setPosition(0);
            sleep(500);
            turnLeft(550,0.6);
            izquierda(1,1,1,1,2500);
            atras(0.7,0.7,0.7,0.7,550);
            derecha(.7,.7,.7,.7,200);
            enfrente(0.5,0.5,0.5,0.5,700);
            robot.skystoneRight.setPosition(1);
            sleep(1000);
            atras(0.5,0.5,0.5,0.5,700);
            turnRight(500,0.8);
            enfrente(0.7,0.7,0.7,0.7,1000);
            robot.skystoneRight.setPosition(0.5);
            sleep(500);
            atras(0.5,.5,.5,.5,300);

            //PATTERN C (3)

        }else{
            //en teoria este codigo nunca se deberia de ejecutar, pero por si las dudas...
            telemetry.addData("[ERROR]", "No se que ha pasado ni como has llegado hasta aqui. Lo siento =(");
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


}