package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;

public class TankDrive extends SubsystemBase {
    VictorSPX victorRight = new VictorSPX(3);
    VictorSPX victorLeft = new VictorSPX(1);
    Joystick _joystick = new Joystick(0);
    public TankDrive(){
        
    }

    @Override
    public void periodic() {
     // This method will be called once per scheduler run
        double leftY = _joystick.getRawAxis(1);// left y?
        double rightY = _joystick.getRawAxis(3);// left x? 3 right x+y

        victorRight.set(ControlMode.PercentOutput, rightY/1.5);//offset should reduce speed
        victorLeft.set(ControlMode.PercentOutput, leftY/-1.5);
    }

    @Override
    public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
    }
    
}
