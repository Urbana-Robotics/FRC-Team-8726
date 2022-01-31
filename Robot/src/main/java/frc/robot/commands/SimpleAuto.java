// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;



import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Sensors;

public class SimpleAuto extends CommandBase {
  /** Creates a new SimpleAuto. */
  Timer timer;
  VictorSPX victorRight = new VictorSPX(3);
  
  VictorSPX victorLeft = new VictorSPX(1);
  AHRS gyro = Sensors.getGyro();
  double startAngle;
  double currentAngle;
  double rightPower = 0.25;
  double leftPower = 0.25;
  public SimpleAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    victorRight.setInverted(true);
    victorLeft.setInverted(false);
    timer.start();
    gyro.reset();
    gyro.calibrate();
    startAngle = gyro.getAngle();
    startAngle = 0.0;
    System.out.println(startAngle);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // currentAngle = gyro.getAngle()%360;

    currentAngle = Sensors.gyroZAngle();

    System.out.println(Math.round(currentAngle));
    correctStraight(currentAngle);

    // victorRight.set(ControlMode.PercentOutput, -rightPower);
    // victorLeft.set(ControlMode.PercentOutput, leftPower);



    // if (currentAngle > 5) {
    //   rightPower += 0.1;
    //   leftPower -= 0.1;
    // } else if (currentAngle < -5) {
    //   rightPower -= 0.1;
    //   leftPower += 0.1;
    // } else {
    //   rightPower = 0.25;
    //   leftPower = 0.25;
    // }
    // // System.out.println(currentAngle);

    // victorRight.set(ControlMode.PercentOutput, rightPower);
    // victorLeft.set(ControlMode.PercentOutput, leftPower);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void turnAndCorrect(){
    if (timer.get() >= 2) {
      if (Math.abs(startAngle-currentAngle)>5){
        victorRight.set(ControlMode.PercentOutput, -0.25);
        victorLeft.set(ControlMode.PercentOutput, 0.25);
        
      } else {
        victorRight.set(ControlMode.PercentOutput, 0);
        victorLeft.set(ControlMode.PercentOutput, 0);
      }
    } else {
      victorRight.set(ControlMode.PercentOutput, -0.35);
      victorLeft.set(ControlMode.PercentOutput, 0.35);
    }
  }

  
  public void correctStraight(double currAngle){
    double currPowerLeft = 0.25;
    double currPowerRight = 0.25;
    if(currAngle > 0 && currAngle < 180){
      currPowerRight += currAngle * 0.01;
    }
    else{
      currPowerLeft += currAngle * 0.01;
    }
    victorRight.set(ControlMode.PercentOutput, currPowerRight);
    victorLeft.set(ControlMode.PercentOutput, currPowerLeft);
  }
  
}
