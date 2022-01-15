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

  VictorSPX _victor1 = new VictorSPX(1);
  VictorSPX _victor3 = new VictorSPX(3);
  Joystick _joystick = new Joystick(0);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double stick1 = _joystick.getRawAxis(0)/4.0;

    boolean leftTrigger = _joystick.getRawButton(7);
    boolean rightTrigger = _joystick.getRawButton(8);
    boolean aButton = _joystick.getRawButton(2);
    if (aButton){
      _victor1.set(ControlMode.PercentOutput,(stick1 > 0 ? -stick1 : stick1 < 0 ? stick1 :0.0));
      _victor3.set(ControlMode.PercentOutput,(stick1 > 0 ? stick1 : stick1 < 0 ? -stick1 :0.0));

    } else {
    
    double leftPower = (rightTrigger ? 0.25 : leftTrigger ? -0.25 : 0.0);
    double rightPower = leftPower;
    
    if(leftTrigger) {
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
  
    _victor1.set(ControlMode.PercentOutput, leftPower);
    _victor3.set(ControlMode.PercentOutput, -rightPower);
  } 
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
