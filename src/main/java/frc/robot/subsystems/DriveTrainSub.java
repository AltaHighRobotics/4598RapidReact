// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSub extends SubsystemBase 
{
  public TalonFX rightMotorFront;
  public TalonFX rightMotorBack;
  public TalonFX leftMotorFront;
  public TalonFX leftMotorBack;
  public DifferentialDrive diffDrive;
  
  /** Creates a new DriveTrainSub. */
  public DriveTrainSub() 
  {
    rightMotorFront = new TalonFX(Constants.RIGHT_DRIVE_MOTOR_FRONT);
    rightMotorBack = new TalonFX(Constants.RIGHT_DRIVE_MOTOR_BACK);
    leftMotorFront = new TalonFX(Constants.LEFT_DRIVE_MOTOR_FRONT);
    leftMotorBack = new TalonFX(Constants.LEFT_DRIVE_MOTOR_BACK);
    rightMotorBack.follow(rightMotorFront);
    leftMotorBack.follow(leftMotorFront);
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }

  public void setArcadeDrive(final double joyForward, final double joyTurn)
  {
    rightMotorFront.set(ControlMode.PercentOutput, joyForward * Constants.DRIVE_MAX_SPEED, DemandType.ArbitraryFeedForward, joyTurn*(Constants.DRIVE_MAX_SPEED/2));
    leftMotorFront.set(ControlMode.PercentOutput, -joyForward * Constants.DRIVE_MAX_SPEED, DemandType.ArbitraryFeedForward, joyTurn*(Constants.DRIVE_MAX_SPEED/2)); 
  }
}