// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;

public class ColorSub extends SubsystemBase {
  /** Creates a new ColorSub. */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  Color detectedColor = m_colorSensor.getColor();
  double IR = m_colorSensor.getIR();
  public ColorSub() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getColorlol(){
    detectedColor = m_colorSensor.getColor();
    System.out.println("RED" + detectedColor.red);
    System.out.println("GREEN" + detectedColor.green);
    System.out.println("BLUE" + detectedColor.blue);
  }
}