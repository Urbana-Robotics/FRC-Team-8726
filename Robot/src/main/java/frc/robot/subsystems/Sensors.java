package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;


// WPILib Gyro Documentation: https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/gyros-software.html?highlight=Gyro#the-gyro-interface
// Basic NavX Gyro Concepts: https://pdocs.kauailabs.com/navx-mxp/installation/orientation-2/
// NavX Docs: https://www.kauailabs.com/public_files/navx-mxp/apidocs/java/com/kauailabs/navx/frc/package-summary.html
public class Sensors extends SubsystemBase {
   Timer timer = new Timer();
  public Sensors(){
    timer.start(); 
  }
  
  private static AHRS gyro = new AHRS(SPI.Port.kMXP);
  private static double currentZAngle = 0.0;
  double prevTime = 0.0;
    
  public static AHRS getGyro(){
      return gyro;
  }
    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    currentZAngle += (timer.get()-prevTime)*gyro.getRawGyroZ();
    prevTime = timer.get();
    currentZAngle %=360;
  }

    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
    public static double gyroZAngle(){
      return currentZAngle;
    }

}
