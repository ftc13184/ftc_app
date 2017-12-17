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
@Autonomous(name = "BlueParkSafeZone", group = "Exercises")
//@Disabled
public class BlueParkSafeZone extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo leftClaw;
    Servo rightClaw;
    DcMotor leftArm;
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftClaw = hardwareMap.servo.get("left_hand");
        rightClaw = hardwareMap.servo.get("right_hand");
        leftArm = hardwareMap.dcMotor.get("left_arm");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //make the claw close on the glyph
        leftClaw.setPosition(0.1);
        rightClaw.setPosition(-0.1);

        //pick the glyph of the ground
        leftArm.setPower(0.15);

        // set power to both motors to drive off balance board.
        leftMotor.setPower(0.30);
        rightMotor.setPower(0.30);

        // continue till we reach safe zone.
        sleep(1300);

        // turn toward crypto box
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.25);

        //let turn for sufficient time to face box
        sleep(3500);

        //drive toward crypto box to get into safe zone
        leftMotor.setPower(0.20);
        rightMotor.setPower(0.20);

        sleep(500);

        //stop
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }
}
