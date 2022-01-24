// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class ArcadeDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ArcadeDrive() {
    // 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //getting trigger and joystick values

    //setting left and right motor powers based on which trigger was pushed
    double leftPower = (Constants.rightTrigger ? 0.5 : Constants.leftTrigger ? -0.5 : 0.0);
    double rightPower = leftPower;


    /*
      either increaseing or decreasing left and right motor powers based on 
      how much joystick was pushed to the left or right. 2 seperate if statments
      since steering was inverted while driving backwards. 
      Differential Driving
    */
    if(Constants.leftTrigger) {
      if (Constants.stick1 > 0) {
        leftPower -= Constants.stick1/4.0;
        rightPower += Constants.stick1/4.0;
      }  
    } else {
      if (Constants.stick1 > 0) {
        leftPower += Constants.stick1/4.0;
        rightPower -= Constants.stick1/4.0;
      }
    }
    
    //setting motor powers
    Constants.victorLeft.set(ControlMode.PercentOutput, leftPower);
    Constants.victorRight.set(ControlMode.PercentOutput, -rightPower);
 
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
