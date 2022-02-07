package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;



public final class GlassNetworkTables extends SubsystemBase {
  private static NetworkTableEntry gyroAngle,xGyro,yGyro,zGyro,gyroRate;
  private static NetworkTableEntry blueHUp,blueSUp,blueVUp,blueHLow,blueSLow,blueVLow;
  private static NetworkTableEntry [] blueValues;
 
  private ADXRS450_Gyro gyro;
  public GlassNetworkTables(ADXRS450_Gyro gyro) {
    this.gyro = gyro;
    NetworkTableInstance instance = NetworkTableInstance.getDefault();
    NetworkTable table = instance.getTable("glassSensors");
    gyroAngle = table.getEntry("GyroAngle");
    gyroRate = table.getEntry("Gyro Rate");
    xGyro = table.getEntry("XGyro");
    yGyro = table.getEntry("yGyro");
    zGyro = table.getEntry("zGyro");
    /*
    blueHUp = table.getEntry("blue H Upper");
    blueHUp.setNumber(94);
    blueSUp = table.getEntry("blue S Upper");
    blueSUp.setNumber(107);
    blueVUp = table.getEntry("blue V Upper");
    blueVUp.setNumber(43);
    blueHLow = table.getEntry("blue H Lower");
    blueHLow.setNumber(119);
    blueSLow = table.getEntry("blue S Lower");
    blueSLow.setNumber(203);
    blueVLow = table.getEntry("blue V Lower");
    blueVLow.setNumber(178);
    
    blueValues = new NetworkTableEntry[] {blueHUp,blueSUp,blueVUp,blueHLow,blueSLow,blueVLow};
    */
    //NetworkTableEntry yaw = table.getEntry("yawAxis");
    //System.out.println(gyro.getBoardYawAxis());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    gyroRate.setNumber(gyro.getRate());
    gyroAngle.setNumber(gyro.getAngle());
    
  }
  /*

  public static double[][] getBlueRanges(){
    double[][] vals = new double[2][3];
    for (int i = 0;i<6;i++){
      if (i>3){
        vals[0][i] = blueValues[i].getDouble(0);
      } else {
        vals[1][i-3] = blueValues[i].getDouble(0);
      }
      
    }
    return vals;
  }
  */
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}