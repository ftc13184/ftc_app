package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static android.R.attr.left;
import static android.R.attr.right;
import static com.sun.tools.javac.main.Option.D;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.
//Simple autonomous program that drives robot forward 5 seconds and then ends
@Autonomous(name = "BlueParkSafeZone", group = "Exercises")
//@Disabled
public class BlueParkSafeZone extends LinearOpMode {

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

        // wait for start button.
        waitForStart();

        //set side arm to starting position
        sideArm.setPosition(0.2);

        telemetry.addData("Mode", "running");
        telemetry.update();

        //make the claw close on the glyph
        leftClaw.setPosition(0.1);
        rightClaw.setPosition(-0.1);

        //pick the glyph of the ground
        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftArm.setPower(0.25);
        sleep(2500);
        leftArm.setPower(0);

        // set power to both motors to drive off balance board.
        leftMotor.setPower(0.30);
        rightMotor.setPower(0.30);

        // continue till we reach safe zone.
        sleep(1600);

        // turn toward crypto box
        leftMotor.setPower(-0.6);
        rightMotor.setPower(0.6);

        //let turn for sufficient time to face box
        sleep(1300);

        //move backward
        leftMotor.setPower(-0.6);
        rightMotor.setPower(-0.6);

        sleep(400);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        //move center arm down
        leftArm.setPower(-0.25);
        sleep(2000);

        //stop
        leftArm.setPower(0);

        //move forward
        leftMotor.setPower(0.60);
        rightMotor.setPower(0.60);
        sleep(800);

        //drop the glyph
        leftClaw.setPosition(-1);
        rightClaw.setPosition(1);

        //move back
        leftMotor.setPower(-0.25);
        rightMotor.setPower(-0.25);
        sleep(200);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }
}
