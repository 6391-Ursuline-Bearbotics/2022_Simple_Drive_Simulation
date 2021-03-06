// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.UA6391.DifferentialDrive6391;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final SpeedControllerGroup m_leftMotors =
      new SpeedControllerGroup(
          new WPI_TalonFX(DriveConstants.kLeftMotor1Port),
          new WPI_TalonFX(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(
          new WPI_TalonFX(DriveConstants.kRightMotor1Port),
          new WPI_TalonFX(DriveConstants.kRightMotor2Port));

  // The robot's drive
  private final DifferentialDrive6391 m_drive = new DifferentialDrive6391(m_leftMotors, m_rightMotors);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    m_drive.setMaxOutput(DriveConstants.kMaxOutputForward, DriveConstants.kMaxOutputRotation);
    m_drive.setMinOutput(DriveConstants.kMinOutputForward, DriveConstants.kMinOutputRotation);
    m_drive.setDeadband(DriveConstants.kDeadbandForward, DriveConstants.kDeadbandRotation);
    m_drive.setRamp(DriveConstants.kRampForward, DriveConstants.kRampRotation);
    m_drive.setDriveStraight(DriveConstants.kDriveStraightLeft, DriveConstants.kDriveStraightRight);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutputForward, double maxOutputRotation) {
    m_drive.setMaxOutput(maxOutputForward, maxOutputRotation);
  }

  public void setDeadband(double deadbandForward, double deadbandRotation) {
    m_drive.setDeadband(deadbandForward, deadbandRotation);
  }
}
