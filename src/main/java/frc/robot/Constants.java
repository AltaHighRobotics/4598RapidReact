// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //PNUMATIC CONTROL MODULE
    public static final int LEFT_SWING_SOLENOID = 0;
    public static final int RIGHT_SWING_SOLENOID = 1;
    public static final int LEFT_INTAKE_SOLENOID = 2;
    public static final int RIGHT_INTAKE_SOLENOID = 3;

    //CAN ID
    public static final int LEFT_LIFT_MOTOR = 0;
    public static final int RIGHT_LIFT_MOTOR = 1;
    public static final int LEFT_INTAKE_MOTOR = 2;
    public static final int RIGHT_MOTOR_FRONT = 3;
    public static final int RIGHT_MOTOR_BACK = 4;
    public static final int LEFT_MOTOR_FRONT = 5;
    public static final int LEFT_MOTOR_BACK = 6;

    //MOTOR SPEEDS
    public static final double LIFT_ARM_SPEED = 0;
    public static final double INTAKE_SPEED = 0;
    public static final double DRIVE_MAX_SPEED = 0;

    //DRIVER CONTROLS
    public static final int DRIVER_ONE = 0;
    public static final int Y_AXIS = 1;
    public static final int X_AXIS = 2;
}
