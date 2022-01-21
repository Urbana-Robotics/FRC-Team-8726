package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

// WPILib Gyro Documentation: https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/gyros-software.html?highlight=Gyro#the-gyro-interface
// Basic NavX Gyro Concepts: https://pdocs.kauailabs.com/navx-mxp/installation/orientation-2/
// NavX Docs: https://www.kauailabs.com/public_files/navx-mxp/apidocs/java/com/kauailabs/navx/frc/package-summary.html
public class Sensors {
    public Sensors(){}
    private AHRS gyro = new AHRS(SPI.Port.kMXP);

    public AHRS getGyro(){
        return gyro;
    }
    
}



