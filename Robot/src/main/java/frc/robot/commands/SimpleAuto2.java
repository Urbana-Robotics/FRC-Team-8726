// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Timer;


public class SimpleAuto2 extends CommandBase {
  /** Creates a new SimpleAuto. */
  //Variable Declarations
  double speedRight = 0.35;
  double speedLeft = 0.35;
  double rotateSpeed = 0.35;
  double rotateSpeedSlow = 0.25;

  //input
  AnalogGyro gyro = new AnalogGyro(1);//if not 1 its 0
 
  public SimpleAuto2() {
    
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gyro.reset();
    gyro.calibrate();
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  //shows current angle
    System.out.println(Math.round(gyro.getAngle()));
  }

  public void driveStraight(){

    speedRight = 0.4;
    speedLeft = 0.4;

  //Kick the robot code
  if (Math.abs(gyro.getAngle()) <= 3) {
    leftPower.set(speedRight - (gyro.getAngle()) / 15);
    VictorSPX.set(speedRight - (gyro.getAngle()) / 15);
  }
  else if (Math.abs(gyro.getAngle()) < 10) {
   if (gyro.getAngle() > 0) {
    victorLeft.set(speedLeft);
    victorRight.set(1.1 * speedRight);
   } else if (gyro.getAngle() < 0) {
    victorLeft.set(1.1 * speedLeft);
    victorRight.set(speedRight);
   }
  } else
   if (gyro.getAngle() > 0) {
    while (gyro.getAngle() > 10 && m_autonomousCommand()) {
     victorLeft.set(-rotateSpeed);
     victorRight.set(-rotateSpeed);
    }
   while (gyro.getAngle() > 0 && m_autonomousCommand()) {
    victorLeft.set(-rotateSpeedSlow);
    victorRight.set(-rotateSpeedSlow);
   }
   while (gyro.getAngle() < 0 && m_autonomousCommand()) {
    victorLeft.set(rotateSpeedSlow);
    victorRight.set(rotateSpeedSlow);
   }
  } else {
   while (gyro.getAngle() < -10 && m_autonomousCommand()) {
    victorLeft.set(rotateSpeed);
    victorRight.set(rotateSpeed);
   }
   while (gyro.getAngle() < 0 && m_autonomousCommand()) {
    victorLeft.set(rotateSpeedSlow);
    victorRight.set(rotateSpeedSlow);
   }
   while (gyro.getAngle() > 0 && m_autonomousCommand()) {
    victorLeft.set(-rotateSpeedSlow);
    victorRight.set(-rotateSpeedSlow);
   }
    }
  }
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  }
  

    // currentAngle = gyro.getAngle()%360;

    //currentAngle = Sensors.gyroAngle();

   // System.out.println(Math.round(currentAngle));
    //correctStraight(currentAngle);

    // victorRight.set(ControlMode.PercentOutput, -rightPower);
    // victorLeft.set(ControlMode.PercentOutput, leftPower);



    // if (currentAngle > 5) {
    //   rightPower += 0.1;
    //   leftPower -= 0.1;
    // } else if (curentAngle < -5) {
    //   rightPower -= 0.1;
    //   leftPower += 0.1;
    // } else {
    //   rightPower = 0.25;
    //   leftPower = 0.25;
    // }
    // // System.out.println(currentAngle);

    // victorRight.set(ControlMode.PercentOutput, rightPower);
    // victorLeft.set(ControlMode.PercentOutput, leftPower);
    
  

  // Called once the command ends or is interrupted.

