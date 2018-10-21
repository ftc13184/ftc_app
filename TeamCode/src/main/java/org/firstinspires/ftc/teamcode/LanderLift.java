package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

public class LanderLift extends LinearOpMode {

    HardwarePushbot robot           = new HardwarePushbot();

    DcMotor coreHex;

    public void linearLift(){
        coreHex = hardwareMap.dcMotor.get("linear_latch");

    }

    public void runCoreHex(){
        coreHex.setPower(0.5);
        sleep(300);
    }

    @Override
    public void runOpMode() {

    }
}
