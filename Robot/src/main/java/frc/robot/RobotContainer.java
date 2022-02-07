// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.commands.ADCommand;
import frc.robot.commands.DPadGyroCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.GlassNetworkTables;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.TankDriveCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import frc.robot.subsystems.BasicVision;
import edu.wpi.first.wpilibj.AnalogGyro;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final DriveTrain m_driveTrain = new DriveTrain();

  private final Joystick m_joystick = new Joystick(0);
  
  //private final AnalogGyro gyro = new AnalogGyro(1);
  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final GlassNetworkTables m_networkTables = new GlassNetworkTables(gyro);
  //private final BasicVision m_basicVision = new BasicVision();
  //private final SimpleAuto m_simpleAutoCommand = new SimpleAuto(m_driveTrain, gyro);
  private final ADCommand DiffDriveArcadeDrive = new ADCommand(m_driveTrain, m_joystick);
  private final DPadGyroCommand m_dpadGyroCommand = new DPadGyroCommand(m_driveTrain, m_joystick, gyro);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CommandScheduler.getInstance().setDefaultCommand(m_driveTrain, DiffDriveArcadeDrive);
    // Configure the button bindings
    configureButtonBindings();
    gyro.calibrate();
    gyro.reset();
    //gyro.setSensitivity(0.0128); // Volts per degree per second
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton button_A = new JoystickButton(m_joystick, 1);
    button_A.whileHeld(m_dpadGyroCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_simpleAutoCommand;
    return m_dpadGyroCommand;
  }
  
}
