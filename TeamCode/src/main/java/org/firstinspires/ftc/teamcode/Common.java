package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static android.os.SystemClock.sleep;

@Autonomous(name = "Common", group = "Exercises")
//@Disabled
public class Common {

    public static final int RED = 0;
    public static final int BLUE = 1;

    void DetectRedOrBlue() {};

    static public int redOrBlue(HardwareMap hardwareMap, Telemetry telemetry)
    {
        // Sense the color
        // get a reference to the color sensor.
        ColorSensor mySensorColor = hardwareMap.get(ColorSensor.class, "color_sensor");

        // get a reference to the distance sensor that shares the same name.
        DistanceSensor mySensorDistance = hardwareMap.get(DistanceSensor.class, "color_sensor");

        int redCount =0;
        int blueCount =0;
        for(int i = 0; i<5; i = i+1)
        {
            // Determine if red or blue based on what the color sensor detects
            if(mySensorColor.red() > 57) {
                telemetry.addData("Its Red = ", mySensorColor.red());
                redCount = redCount + 1;
            }
            else if (mySensorColor.blue() > 52) {
                telemetry.addData("Its Blue = ", mySensorColor.blue());
                blueCount = blueCount + 1;
            }
            else telemetry.addData("Its None" , mySensorColor.blue());

            // Increment the color counter according to what color is sensed
        }

        //determine wether there are more red results of blue results out of 5
        if (redCount > blueCount)
            return RED;
        else
            return BLUE;
    }
    public static void knockOffJewel(HardwareMap hardwareMap,Telemetry telemetry){

        DcMotor leftMotor;
        DcMotor rightMotor;
        Servo sideArm;

        sideArm = hardwareMap.servo.get("sensor_hand");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        //find out what color the ball is
        if (Common.redOrBlue(hardwareMap, telemetry) == Common.RED)
        {
            telemetry.addData("It's", "Red");
            leftMotor.setPower(0.6);
            rightMotor.setPower(0);
            sleep(250);
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            sleep(400);

            //lift up the arm
            sideArm.setPosition(-0.4);

            leftMotor.setPower(-0.6);
            rightMotor.setPower(0);
            sleep(400);

        }
        else
        {
            telemetry.addData("It's", "Blue");
            leftMotor.setPower(0.4);
            rightMotor.setPower(0.4);
            sleep(200);
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            sleep(400);
            //lift up the arm
            sideArm.setPosition(-0.4);

            sleep(500);

            leftMotor.setPower(-0.4);
            rightMotor.setPower(-0.4);
            sleep(150);
            leftMotor.setPower(0);
            rightMotor.setPower(0);

            sleep(1000);
        }

        telemetry.update();

    }
}
