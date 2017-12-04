package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.
//Simple autonomous program that drives robot forward 5 seconds and then ends
@Autonomous(name = "RedParkSafeZone", group = "Exercises")
//@Disabled
public class RedParkSafeZone extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    // called when init button is  pressed.


    @Override


    public void runOpMode() throws InterruptedException


    {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

// wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.

        leftMotor.setPower(0.50);
        rightMotor.setPower(0.50);

        sleep(2000); // wait for 4 seconds.

        // set motor power to 0.10.

        leftMotor.setPower(0.70);
        rightMotor.setPower(0.0);

        sleep(2000);

        leftMotor.setPower(0.50);
        rightMotor.setPower(0.50);

        sleep(500);
        
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);




    }
}
