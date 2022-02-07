// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogGyro;

public class ArcadeDriveCommand extends CommandBase {
  /** Creates a new SimpleAuto. */
  private DriveTrain drivesystem;
  private Joystick joystick;
  private boolean leftTrigger, rightTrigger;
  private double leftSpeed, rightSpeed, leftX, errorRate;


  AnalogGyro gyro;


  public ArcadeDriveCommand(DriveTrain subsystem, Joystick joystick1, AnalogGyro gyro) {
    drivesystem = subsystem;
    joystick = joystick1;
    addRequirements(subsystem);

    this.gyro = gyro;
		
    leftSpeed = 0.35;
    rightSpeed = 0.35;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    leftTrigger = joystick.getRawButton(5);
    rightTrigger = joystick.getRawButton(6);

    leftX = joystick.getRawAxis(Constants.LEFT_X);

    errorRate = gyro.getRate();


    // if(rightTrigger) { // changing motor speeds to ajust for drift based in rate at which robot is drifting
    //   leftSpeed -= errorRate;
    //   rightSpeed += errorRate;
    // } else if (leftTrigger) { // same thing
    //   leftSpeed = -leftSpeed + errorRate;
    //   rightSpeed = -rightSpeed - errorRate;
    // } else {
    //   leftSpeed = 0;
    //   rightSpeed = 0;
    // }

    if(leftTrigger) {
      leftSpeed = -leftSpeed;
      rightSpeed = -rightSpeed;
    } else if (!rightTrigger) {
      leftSpeed = 0.0;
      rightSpeed = 0.0;
    }



    // if(leftX == 0) { // If the left joystick is 0 (driving in a stright line) 
    //   errorRate = gyro.getRate();
    //   if(rightTrigger) { // changing motor speeds to ajust for drift based in rate at which robot is drifting
    //     leftSpeed -= errorRate;
    //     rightSpeed += errorRate;
    //   } else if (leftTrigger) { // same thing
    //     leftSpeed = -leftSpeed + errorRate;
    //     rightSpeed = -rightSpeed - errorRate;
    //   } else {
    //     leftSpeed = 0;
    //     rightSpeed = 0;
    //   }
    // } 
    // else {
    //   if(rightTrigger) { // just basic arcade drive for if your turning
    //     leftSpeed += leftX/4.0;
    //     rightSpeed -= leftX/4.0;
    //   } else if (leftTrigger) {
    //     leftSpeed -= leftX/4.0;
    //     rightSpeed += leftX/4.0;
    //   } else {
    //     leftSpeed = -leftX/4.0;
    //     rightSpeed = leftX/4.0;
    //   }
    // }

    drivesystem.drive(leftSpeed, rightSpeed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
