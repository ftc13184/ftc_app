package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.
//Simple autonomous program that drives robot forward 5 seconds and then ends
@Autonomous(name = "RedJewelStraight", group = "Exercises")
//@Disabled
public class RedJewelStraight extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo leftClaw;
    Servo rightClaw;
    DcMotor leftArm;
    Servo sideArm;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftClaw = hardwareMap.servo.get("left_hand");
        rightClaw = hardwareMap.servo.get("right_hand");
        leftArm = hardwareMap.dcMotor.get("left_arm");
        sideArm = hardwareMap.servo.get("sensor_hand");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        //Use break mode on the 2 drives so that they can counteract gravity when getting onto the balance plate
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //put break mode
        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // wait for start button.
        waitForStart();

        //set side arm to starting position
        sideArm.setPosition(-0.1);

        telemetry.addData("Mode", "running");
        telemetry.update();

        //make the claw close on the glyph
        leftClaw.setPosition(0.3);
        rightClaw.setPosition(-0.3);

        //lift up the glyph
        leftArm.setPower(0.25);
        sleep(2000);
        leftArm.setPower(0);

        //put the side arm down to sense the color of the jewel
        sideArm.setPosition(0.5);

        //stop
        sleep(1000);

        // knock off the jewel
        Common.knockOffJewel( hardwareMap, telemetry);

        //turn left to face cryptobox
        leftMotor.setPower(0.00);
        rightMotor.setPower(0.60);
        sleep(225);

        // set power to both motors to drive off balance board.
        leftMotor.setPower(0.40);
        rightMotor.setPower(0.40);
        // continue till we reach safe zone.
        sleep(400);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        //put the arm down
        leftArm.setPower(-0.25);
        sleep(1300);

        //continue push in the glyph
        leftMotor.setPower(0.60);
        rightMotor.setPower(0.60);
        sleep(500);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        sleep(300);

        //drop the glyph
        leftClaw.setPosition(-1);
        rightClaw.setPosition(1);

        sleep(300);

        //move back
        leftMotor.setPower(-0.60);
        rightMotor.setPower(-0.60);
        sleep(300);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        sleep(200);

        //lift arm so that it is clear of the glyph
        leftArm.setPower(0.4);
        sleep(500);

        //stop the arm
        leftArm.setPower(0);
    }
}
