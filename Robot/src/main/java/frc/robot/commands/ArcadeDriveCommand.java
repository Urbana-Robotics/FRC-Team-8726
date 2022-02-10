// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import frc.robot.Constants;
public class ArcadeDriveCommand extends CommandBase {
  /** Creates a new SimpleAuto. */
  private DriveTrain drivesystem;
  private Joystick joystick;
  private double leftSpeed, rightSpeed, leftX, leftX2, errorRate;
  private double leftTrigger, rightTrigger, leftTrigger2, rightTrigger2;
  private double forwardSensitivity, turningSensitivity, inplaceSensitivity;

  ADXRS450_Gyro gyro;


  public ArcadeDriveCommand(DriveTrain subsystem, Joystick joystick1, ADXRS450_Gyro gyro) {
    drivesystem = subsystem;
    joystick = joystick1;
    addRequirements(subsystem);

    this.gyro = gyro;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    leftTrigger = joystick.getRawAxis(Constants.LEFT_TRIGGER_AXIS);
    rightTrigger = joystick.getRawAxis(Constants.RIGHT_TRIGGER_AXIS);

    leftX = joystick.getRawAxis(Constants.LEFT_X);

    // squaring to decrease sensitivity
    leftX2 = Math.pow(leftX, 2.0);
    leftTrigger2 = Math.pow(leftTrigger, 2.0);
    rightTrigger2 = Math.pow(rightTrigger, 2.0);
    
    errorRate = gyro.getRate();

    forwardSensitivity = 1.5;
    turningSensitivity = 5.0;
    inplaceSensitivity = 1.5;

    System.out.println("Error Rate:" + errorRate);
    System.out.println("Errer Angle:" + gyro.getAngle());
    System.out.println("Right Speed:" + rightSpeed);
    System.out.println("Left Speed:" + leftSpeed);

    if(leftX != 0.0) { // if your trying to turn
      if (rightTrigger == 0.0 && leftTrigger == 0.0) { // turning inplace
        if (leftX > 0.0) {
          leftSpeed = leftX2 / inplaceSensitivity;
          rightSpeed = -leftX2 / inplaceSensitivity;
        } else if (leftX < 0.0) {
          leftSpeed = -leftX2 / inplaceSensitivity;
          rightSpeed = leftX2 / inplaceSensitivity;
        } 
      } else if (rightTrigger > 0.0 && leftTrigger == 0.0) { // driving in an arc forward
        if (leftX < 0.0) {
          leftSpeed = rightTrigger2 / forwardSensitivity - leftX2 / turningSensitivity;
          rightSpeed = rightTrigger2 / forwardSensitivity + leftX2 / turningSensitivity;
        } else if (leftX > 0.0) {
          leftSpeed = rightTrigger2 / forwardSensitivity + leftX2 / turningSensitivity;
          rightSpeed = rightTrigger2 / forwardSensitivity - leftX2 / turningSensitivity;
        }
      } else if (leftTrigger > 0.0 && rightTrigger == 0.0) { // driving in an arc backward
        if (leftX < 0.0) {
          leftSpeed = -leftTrigger2 / forwardSensitivity + leftX2 / turningSensitivity;
          rightSpeed = -leftTrigger2 / forwardSensitivity - leftX2 / turningSensitivity;
        } else if (leftX > 0.0) {
          leftSpeed = -leftTrigger2 / forwardSensitivity - leftX2 / turningSensitivity;
          rightSpeed = -leftTrigger2 / forwardSensitivity + leftX2 / turningSensitivity;
        }
      }
    } else { // gyro corrects robot as it is drifting live
      if (rightTrigger > 0.0 && leftTrigger == 0.0) {
        leftSpeed = rightTrigger2 / forwardSensitivity;
        rightSpeed = rightTrigger2 / forwardSensitivity;
      } else if (leftTrigger > 0.0 && rightTrigger == 0.0) {
        leftSpeed = -leftTrigger2 / forwardSensitivity;
        rightSpeed = -leftTrigger2 / forwardSensitivity;
      }
    } 

    
    // // Simulates Acceleration Hopefully
    // // leftSpeedFinal and rightSpeedFinal are initialized to 0.0
    // //acceleration will be in constants

    // if(leftSpeedFinal > leftSpeed) {
    //   leftSpeedFinal -= acceleration;
    // } else if (leftSpeedFinal < leftSpeed) {
    //   leftSpeedFinal += acceleration
    // } 

    // if(rightSpeedFinal > rightSpeed) {
    //   rightSpeedFinal -= acceleration;
    // } else if (rightSpeedFinal < rightSpeed) {
    //   rightSpeedFinal += acceleration
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
