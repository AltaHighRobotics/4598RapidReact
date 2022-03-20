// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */

  private TalonFX leftShooterMotor;
  private TalonFX rightShooterMotor;
  private ConfigurablePID leftShooterPID;
  private ConfigurablePID rightShooterPID;

  public ShooterSub() {
    leftShooterPID = new ConfigurablePID(
      Constants.SHOOTER_PORPORTIONAL_GAIN,
      Constants.SHOOTER_INTERGRAL_GAIN,
      Constants.SHOOTER_DERIVITIVE_GAIN,
      Constants.SHOOTER_MAX_PROPORTIONAL,
      Constants.SHOOTER_MAX_INTEGRAL,
      Constants.SHOOTER_MAX_DERIVITIVE
    );

    rightShooterPID = new ConfigurablePID(
      Constants.SHOOTER_PORPORTIONAL_GAIN,
      Constants.SHOOTER_INTERGRAL_GAIN,
      Constants.SHOOTER_DERIVITIVE_GAIN,
      Constants.SHOOTER_MAX_PROPORTIONAL,
      Constants.SHOOTER_MAX_INTEGRAL,
      Constants.SHOOTER_MAX_DERIVITIVE
    );

    leftShooterMotor = new TalonFX(Constants.LEFT_SHOOTER_MOTOR);
    rightShooterMotor = new TalonFX(Constants.RIGHT_SHOOTER_MOTOR);

    leftShooterMotor.configFactoryDefault();
    rightShooterMotor.configFactoryDefault();

    leftShooterMotor.setNeutralMode(NeutralMode.Coast);
    rightShooterMotor.setNeutralMode(NeutralMode.Coast);

    leftShooterMotor.setSensorPhase(false);
    rightShooterMotor.setSensorPhase(false);

    leftShooterMotor.setInverted(TalonFXInvertType.CounterClockwise);
    rightShooterMotor.setInverted(TalonFXInvertType.Clockwise);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /** Proportional Integral Controller used to set both of the shooter motors speed
   *  Adjusts both motors power to match a target velocity
   *  @param targetShooterVelocity A double representing the target velocity that the shooter motors should attempt to reach
   */
  public void setShooterMotorsVelocity(double targetShooterVelocity){

    // Gets the velocity of each of the two shooter motors
    double shooterLeftVelocity = leftShooterMotor.getSelectedSensorVelocity();
    double shooterRightVelocity = rightShooterMotor.getSelectedSensorVelocity();

    // Runs the controllers
    double shooterLeftPower = leftShooterPID.runPID(targetShooterVelocity, shooterLeftVelocity);
    double shooterRightPower = rightShooterPID.runPID(targetShooterVelocity, shooterRightVelocity);

    // Clamps the power output above the power offset value, which ensures the motors don't apply brakes, as that would cause instability and vibrations
    shooterLeftPower = Math.max(shooterLeftPower, Constants.SHOOTER_POWER_OFFSET);
    shooterRightPower = Math.max(shooterRightPower, Constants.SHOOTER_POWER_OFFSET);

    // Sets the motors to the computed power levels
    leftShooterMotor.set(ControlMode.PercentOutput, shooterLeftPower);
    rightShooterMotor.set(ControlMode.PercentOutput, shooterRightPower);

    // Displays useful values in Smart Dashboard
    SmartDashboard.putNumber("Shooter Left Power", shooterLeftPower);
    SmartDashboard.putNumber("Shooter Right Power", shooterRightPower);
  }

  public void setShooterMotorsPower(double Speed){
    leftShooterMotor.set(ControlMode.PercentOutput, Speed);
    rightShooterMotor.set(ControlMode.PercentOutput, Speed);
  }

}
