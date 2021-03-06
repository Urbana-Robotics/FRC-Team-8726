// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;


public class ArcadeDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ArcadeDrive() {}

  VictorSPX victorLeft = new VictorSPX(1);
  VictorSPX victorRight = new VictorSPX(3);
  Joystick _joystick = new Joystick(0);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //getting trigger and joystick values
    double stick1 = _joystick.getRawAxis(0)/4.0;

    boolean leftTrigger = _joystick.getRawButton(7);
    boolean rightTrigger = _joystick.getRawButton(8);
    boolean aButton = _joystick.getRawButton(1);

    //setting left and right motor powers based on which trigger was pushed
    double leftPower = (rightTrigger ? 0.25 : leftTrigger ? -0.25 : 0.0);
    double rightPower = leftPower;

    if (aButton){
      victorLeft.set(ControlMode.PercentOutput, 0.25);
      victorRight.set(ControlMode.PercentOutput, 0.25);

      // victorLeft.set(ControlMode.PercentOutput,(stick1 > 0 ? -stick1 : stick1 < 0 ? stick1 :0.0));
      // victorRight.set(ControlMode.PercentOutput,(stick1 > 0 ? stick1 : stick1 < 0 ? -stick1 :0.0));
      // if (stick1 > 0){
      //   victorLeft.set(ControlMode.PercentOutput,-0.25);
      //   victorRight.set(ControlMode.PercentOutput,0.25);
      // } else if (stick1 < 0){
      //   victorLeft.set(ControlMode.PercentOutput,0.25);
      //   victorRight.set(ControlMode.PercentOutput,-0.25);
      // }
    } else if(leftTrigger) {
      /*
      either increaseing or decreasing left and right motor powers based on 
      how much joystick was pushed to the left or right. 2 seperate if statments
      since steering was inverted while driving backwards. 
      Differential Driving
      */
      if (stick1 > 0) {
        leftPower -= stick1/2.0;
        rightPower += stick1/2.0;
      } else if (stick1 < 0) {
        leftPower -= stick1/2.0;
        rightPower += stick1/2.0;
      }  
    } else {
      if (stick1 > 0) {
        leftPower += stick1/2.0;
        rightPower -= stick1/2.0;
      } else if (stick1 < 0) {
        leftPower += stick1/2.0;
        rightPower -= stick1/2.0;
      } 
    }
    
    //setting motor powers
    victorLeft.set(ControlMode.PercentOutput, leftPower);
    victorRight.set(ControlMode.PercentOutput, -rightPower);
 
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
