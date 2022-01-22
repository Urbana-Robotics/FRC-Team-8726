/*
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Timer;


public class AutoTesting extends SubsystemBase{
  Timer timer;
  public AutoTesting() {
    
  }
  

  VictorSPX victorRight = new VictorSPX(3);
  VictorSPX victorLeft = new VictorSPX(1);


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    boolean check = timer.hasElapsed(2);
    // if (check){
    //   victorRight.set(ControlMode.PercentOutput, 0);
    //   victorLeft.set(ControlMode.PercentOutput, 0);
    //   }
    // else{
    //   victorRight.set(ControlMode.PercentOutput, -0.45);
    //   victorLeft.set(ControlMode.PercentOutput, 0.45);
    //   }

    
  }


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  
  public void resetTimer() {
    timer.stop();
    timer.reset();
    timer.start();
  }
  
}
*/