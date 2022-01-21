package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;


// WPILib Gyro Documentation: https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/gyros-software.html?highlight=Gyro#the-gyro-interface
// Basic NavX Gyro Concepts: https://pdocs.kauailabs.com/navx-mxp/installation/orientation-2/
public class AutoTesting extends SubsystemBase{
  Timer timer;
  public AutoTesting() {
    timer = new Timer();
     timer.start();   
    

  }
  

  VictorSPX victorRight = new VictorSPX(3);
  VictorSPX victorLeft = new VictorSPX(1);
  //AHRS gyro = new AHRS(SPI.Port.kMXP); //may be moved into sensors class
  boolean isDriving = true;
  int timeDriven = 0;

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    boolean check = timer.hasElapsed(5);
    if (check){
      isDriving = false;
      }
    else{
        isDriving = true;
      }

    if (isDriving){
    victorRight.set(ControlMode.PercentOutput, -0.45);
    victorLeft.set(ControlMode.PercentOutput, 0.45);
    }
    
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
