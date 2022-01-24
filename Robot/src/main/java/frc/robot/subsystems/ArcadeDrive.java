// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class ArcadeDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  public ArcadeDrive() {
    // 
  }
  
  VictorSPX victorLeft = new VictorSPX(Constants.LEFT_MOTOR);
  VictorSPX victorRight = new VictorSPX(Constants.LEFT_MOTOR);
  Joystick joystick = new Joystick(Constants.LEFT_X);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //getting trigger and joystick values

    //setting left and right motor powers based on which trigger was pushed

    boolean leftTrigger = joystick.getRawButton(Constants.LEFT_TRIGGER);
    boolean rightTrigger = joystick.getRawButton(Constants.RIGHT_TRIGGER);

    double leftX = joystick.getRawAxis(Constants.LEFT_X);

    double temp = (leftTrigger ? -0.5 : 0.0);
    double leftPower = (rightTrigger ? 0.5 : temp);
    double rightPower = leftPower;


    /*
      either increaseing or decreasing left and right motor powers based on 
      how much joystick was pushed to the left or right. 2 seperate if statments
      since steering was inverted while driving backwards. 
      Differential Driving
    */
    if(leftTrigger) {
      if (leftX > 0) {
        leftPower -= leftX/4.0;
        rightPower += leftX/4.0;
      }  
    } else {
      if (leftX > 0) {
        leftPower += leftX/4.0;
        rightPower -= leftX/4.0;
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
