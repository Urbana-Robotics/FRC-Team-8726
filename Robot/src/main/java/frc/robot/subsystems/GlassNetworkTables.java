package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//import frc.robot.subsystems.Sensors;
import edu.wpi.first.wpilibj.Joystick;

public class GlassNetworkTables extends SubsystemBase {
  NetworkTableEntry gyroAngle;
  NetworkTableEntry xGyro;
  NetworkTableEntry yGyro;
  NetworkTableEntry zGyro;
  Joystick _joystick = new Joystick(0);
  
 ADXRS450_Gyro gyro = Sensors.getGyro();
  public GlassNetworkTables() {
    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable table = instance.getTable("glassSensors");
    gyroAngle = table.getEntry("GyroAngle");
    xGyro = table.getEntry("XGyro");
    yGyro = table.getEntry("yGyro");
    zGyro = table.getEntry("zGyro");
    //NetworkTableEntry yaw = table.getEntry("yawAxis");
    //System.out.println(gyro.getBoardYawAxis());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gyroAngle.setNumber(Sensors.gyroAngle());
    //System.out.println(gyro.getAngle());
    //xGyro.setNumber(gyro.getRawGyroX());
    ///System.out.println(gyro.getRawGyroX());
    //yGyro.setNumber(gyro.getRawGyroY());
    //System.out.println(gyro.getRawGyroY());
    //zGyro.setNumber(gyro.getRawGyroZ());
    //System.out.println(gyro.getRawGyroZ());
   // System.out.println(_joystick.getRawAxis(0)/2.0);
    //System.out.println("----------");

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}