package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;



public class GlassNetworkTables extends SubsystemBase {
  NetworkTableEntry gyroAngle,xGyro,yGyro,zGyro,gyroRate;
  NetworkTableEntry blueHUp,blueSUp,blueVUp,blueHLow,blueSLow,blueVLow;
  NetworkTableEntry [] blueValues;
 
  AnalogGyro gyro;
  public GlassNetworkTables() {
    gyro = new AnalogGyro(1);
    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable table = instance.getTable("glassSensors");
    gyroAngle = table.getEntry("GyroAngle");
    gyroRate = table.getEntry("Gyro Rate");
    xGyro = table.getEntry("XGyro");
    yGyro = table.getEntry("yGyro");
    zGyro = table.getEntry("zGyro");
    blueHUp = table.getEntry("blue H Upper");
    blueSUp = table.getEntry("blue S Upper");
    blueVUp = table.getEntry("blue V Upper");
    blueHLow = table.getEntry("blue H Lower");
    blueSLow = table.getEntry("blue S Lower");
    blueVLow = table.getEntry("blue V Lower");
    blueValues = new NetworkTableEntry[] {blueHUp,blueSUp,blueVUp,blueHLow,blueSLow,blueVLow};
    //NetworkTableEntry yaw = table.getEntry("yawAxis");
    //System.out.println(gyro.getBoardYawAxis());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gyroRate.setNumber(gyro.getRate());
    gyroAngle.setNumber(gyro.getAngle());
    
  }

  public int[] getBlueRanges(){
    int[] vals = new int[6];
    for (int i = 0;i<6;i++){
      vals[i] =  blueValues[i].getNumber(0).intValue();
    }
    return vals;
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}