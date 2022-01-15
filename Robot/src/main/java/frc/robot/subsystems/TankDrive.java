package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;

public class TankDrive extends SubsystemBase {
    VictorSPX _victor1 = new VictorSPX(1);
    VictorSPX _victor3 = new VictorSPX(3);
    Joystick _joystick = new Joystick(0);
    public TankDrive(){
        
    }

    @Override
    public void periodic() {
     // This method will be called once per scheduler run
        double stick1 = _joystick.getRawAxis(1);// left y?
        double stick0 = _joystick.getRawAxis(2);// left x? 3 right x+y

        _victor1.set(ControlMode.PercentOutput, stick0/-1.5);//offset should reduce speed
        _victor3.set(ControlMode.PercentOutput, stick1/1.5);
    }

    @Override
    public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
    }
    
}
