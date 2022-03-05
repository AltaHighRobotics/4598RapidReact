/** Subsystem for the climbing mechanism for the 2022 FRC competition Rapid React
 *  @author Cracker
 *  @author Icarus Innovated
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.*;

import java.lang.Math;

public class ClimbingSub extends SubsystemBase {
  private Solenoid leftSwingSolenoid;
  // private Solenoid rightSwingSolenoid;
  private TalonFX leftLiftMotor;
  private TalonFX rightLiftMotor;
  private double rightLiftMotorVelocity;
  private double leftLiftMotorVelocity;
  private double rightLiftMotorPosition;
  private double leftLiftMotorPosition;
  private double rightLiftMotorIntegral;
  private double leftLiftMotorIntegral;
  private double currentArmTarget;
  private int currentStage;
  private boolean hasRun;

  public ClimbingSub() {
      leftSwingSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.LEFT_SWING_SOLENOID);
      // rightSwingSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.RIGHT_SWING_SOLENOID);
      leftLiftMotor = new TalonFX(Constants.LEFT_LIFT_MOTOR);
      rightLiftMotor = new TalonFX(Constants.RIGHT_LIFT_MOTOR);
      leftLiftMotor.setNeutralMode(NeutralMode.Brake);
      rightLiftMotor.setNeutralMode(NeutralMode.Brake);
      leftLiftMotor.configFactoryDefault();
      rightLiftMotor.configFactoryDefault();
      leftLiftMotor.setInverted(false);
      rightLiftMotor.setInverted(false);
      leftLiftMotor.setSensorPhase(false);
      rightLiftMotor.setSensorPhase(false);
      currentArmTarget = Constants.MAX_ARM_POSITION;
      currentStage = 0;
      hasRun = false;
    }

  /** Extends the climbing arms
   *  WARNING! This command has no automatic stop programmed into it and will break the robot if not used correctly
   *  @deprecated
   */
  public void ExtendArms(){
    leftLiftMotor.set(ControlMode.PercentOutput, -Constants.LIFT_ARM_SPEED);
    rightLiftMotor.set(ControlMode.PercentOutput, Constants.LIFT_ARM_SPEED);
  }

  /** Retracts the climbing arms
   *  WARNING! This command has no automatic stop programmed into it and will break the robot if not used correctly
   *  @deprecated
   */
  public void RetractArms(){
    leftLiftMotor.set(ControlMode.PercentOutput, Constants.LIFT_ARM_SPEED);
    rightLiftMotor.set(ControlMode.PercentOutput, -Constants.LIFT_ARM_SPEED);
  }

  /** Sets the arms to not move
   * 
   */
  public void ArmsStationary(){
    leftLiftMotor.set(ControlMode.PercentOutput, 0);
    rightLiftMotor.set(ControlMode.PercentOutput, 0);
  }
  
  /** Toggles the solonoids on the arms to on
   * 
   */
  public void SwingArms(){
    // rightSwingSolenoid.set(true);
    leftSwingSolenoid.set(true);
  }

  /** Toggles the solonoids on the arms to off
   * 
   */
  public void ReturnArms(){
    // rightSwingSolenoid.set(false);
    leftSwingSolenoid.set(false);
  }

  /** Proportional Integral Controller used for the arms
   *  Scales the motors power to reach the target position as fast as possible
   *  @param targetPosition A double representing the target position tracked by the encoder that the controller will attempt to reach
   */
  public void SetArmsWithClamp(double targetPosition)
  {
    rightLiftMotorPosition = rightLiftMotor.getSelectedSensorPosition();
    leftLiftMotorPosition = leftLiftMotor.getSelectedSensorPosition();
    rightLiftMotorVelocity = rightLiftMotor.getSelectedSensorVelocity();
    leftLiftMotorVelocity = leftLiftMotor.getSelectedSensorVelocity();
    
    double averageArmPosition = (-leftLiftMotorPosition + rightLiftMotorPosition)/2;
    double armMin = Math.min(targetPosition, averageArmPosition + Constants.MAX_ARM_ERROR);
    double actualTarget = Math.max(armMin, averageArmPosition - Constants.MAX_ARM_ERROR);

    double leftArmError = (-actualTarget - leftLiftMotorPosition) * Constants.MAX_ARM_SPEED;
    double rightArmError = (actualTarget - rightLiftMotorPosition) * Constants.MAX_ARM_SPEED;

    double leftArmVelocityError = leftArmError - leftLiftMotorVelocity;
    double rightArmVelocityError = rightArmError - rightLiftMotorVelocity;

    leftLiftMotorIntegral = Math.min(Math.max(leftLiftMotorIntegral + leftArmVelocityError, -1), 1);
    rightLiftMotorIntegral = Math.min(Math.max(rightLiftMotorIntegral + rightArmVelocityError, -1), 1);

    double leftArmPower = leftArmVelocityError * Constants.ARM_PROPORTIONAL_GAIN + leftLiftMotorIntegral * Constants.ARM_INTEGRAL_GAIN;
    double rightArmPower = rightArmVelocityError * Constants.ARM_PROPORTIONAL_GAIN + rightLiftMotorIntegral * Constants.ARM_INTEGRAL_GAIN;

    leftLiftMotor.set(ControlMode.PercentOutput, leftArmPower);
    rightLiftMotor.set(ControlMode.PercentOutput, rightArmPower);
  }

  /** Gets the current target for use in SetArmsWithClamp() later in the code
   *  @return A double representing the location of the current target in terms of a motor encoder
   */
  public double getCurrentTarget()
  {
    return currentArmTarget;
  }

  /** Sets the current target for use in SetArmsWithClamp() later in the code
   *  @param target A double representing the location of the target in terms of a motor encoder
   */
  public void setCurrentTarget(double target)
  {
    currentArmTarget = target;
  }

  /** Gets the current stage the robot is on when climbing
   * @return An int representing the current stage
   */
  public int getCurrentStage()
  {
    return currentStage;
  }

  /** Sets the current stage the robot is on when climbing
   * @param stage An int representing what stage the robot currently is on
   */
  public void setCurrentStage(int stage)
  {
    currentStage = stage;
  }

  /** Sets whether or not the switch case has run in ClimbingCommand.java
   * @param hasSwitchRun A boolean representing whether or not the switch case has run
   */
  public void setHasRun(boolean hasSwitchRun)
  {
    hasRun = hasSwitchRun;
  }

  /** Gets whether or not the switch case has run in ClimbingCommand.java
   *  @return A boolean representing wheter or not the switch case has run
   */
  public boolean getHasRun()
  {
    return hasRun;
  }

  /** Gets the encoder position for the left motor 
   *  @return A double representing the left motor encoder position
   */
  public double getLeftCoderPos()
  {
    return leftLiftMotor.getSelectedSensorPosition();
  }

  /** Gets the encoder position for the right motor
   *  @return A double representing the right motor encoder position
   */
  public double getRightCoderPos()
  {
    return rightLiftMotor.getSelectedSensorPosition();
  }

  /** Checks if the arms are at the target position
   * @param target A double representing the location of the target position
   * @return A boolean representing whether or not that target position has been reached
   */
  public boolean hasReachedPosition(double target)
  {
    double pos = rightLiftMotor.getSelectedSensorPosition();
    if(target > pos - Constants.ACCEPTABLE_ERROR &&
       target < pos + Constants.ACCEPTABLE_ERROR)
    {
      System.out.println("Target Reached");
      return true;
    }
    return false;
  }

  @Override
  public void periodic() {
    
  } 
}