package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//import frc.robot.subsystems.Sensors;
import edu.wpi.first.wpilibj.Joystick;

public class GlassNetworkTables extends SubsystemBase {
  NetworkTableEntry gyroAngle;
  NetworkTableEntry xAccel;
  NetworkTableEntry yAccel;
  NetworkTableEntry zAccel;
  Joystick _joystick = new Joystick(0);
  
  AHRS gyro = Sensors.getGyro();
  public GlassNetworkTables() {
    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable table = instance.getTable("glassSensors");
    gyroAngle = table.getEntry("GyroAngle");
    xAccel = table.getEntry("XAccel");
    yAccel = table.getEntry("yAccel");
    zAccel = table.getEntry("zAccel");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gyroAngle.setNumber(gyro.getAngle());
    System.out.println(gyro.getAngle());
    xAccel.setNumber(gyro.getRawAccelX());
    System.out.println(gyro.getRawAccelX());
    yAccel.setNumber(gyro.getRawAccelY());
    System.out.println(gyro.getRawAccelY());
    zAccel.setNumber(gyro.getRawAccelZ());
    System.out.println(gyro.getRawAccelZ());
    System.out.println(_joystick.getRawAxis(0)/2.0);
    System.out.println("----------");

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}