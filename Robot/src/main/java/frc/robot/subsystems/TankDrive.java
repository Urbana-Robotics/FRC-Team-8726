package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
    public TankDrive(){
        //
    }

    @Override
    public void periodic() {
     // This method will be called once per scheduler run

        Constants.victorRight.set(ControlMode.PercentOutput, Constants.rightY/1.5);//offset should reduce speed
        Constants.victorLeft.set(ControlMode.PercentOutput, Constants.leftY/-1.5);
    }

    @Override
    public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
    }
    
}
