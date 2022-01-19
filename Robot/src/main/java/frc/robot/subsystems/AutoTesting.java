package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;


// WPILib Gyro Documentation: https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/gyros-software.html?highlight=Gyro#the-gyro-interface

public class AutoTesting extends SubsystemBase{
    /** Creates a new ExampleSubsystem. */
  public AutoTesting() {}
  VictorSPX victorRight = new VictorSPX(3);
  VictorSPX victorLeft = new VictorSPX(1);
  //AHRS gyro = new AHRS(SPI.Port.kMXP);
  boolean isDriving = true;
  int timeDriven = 0;
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (isDriving){
    victorRight.set(ControlMode.PercentOutput, 0.5);
    victorLeft.set(ControlMode.PercentOutput, 0.5);
    }
    timeDriven++;
    if (timeDriven > 10){ //may be too small based on scheduler frequency
      isDriving = false;
    }
   
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
