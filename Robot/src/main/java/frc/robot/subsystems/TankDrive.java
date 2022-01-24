package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
    public TankDrive(){
        //
    }

    VictorSPX victorLeft = new VictorSPX(Constants.LEFT_MOTOR);
    VictorSPX victorRight = new VictorSPX(Constants.LEFT_MOTOR);
    Joystick joystick = new Joystick(Constants.LEFT_X);

    @Override
    public void periodic() {
     // This method will be called once per scheduler run
        
        double leftY = joystick.getRawAxis(Constants.LEFT_Y);
        double rightY = joystick.getRawAxis(Constants.RIGHT_Y);

        victorRight.set(ControlMode.PercentOutput, rightY/1.5);//offset should reduce speed
        victorLeft.set(ControlMode.PercentOutput, leftY/-1.5);
    }

    @Override
    public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
    }
    
}
