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
    //PNEUMATIC CONTROL MODULE
    public static final int LEFT_SWING_SOLENOID = 0;
    public static final int RIGHT_SWING_SOLENOID = 1;
    public static final int LEFT_INTAKE_SOLENOID = 2;
    public static final int RIGHT_INTAKE_SOLENOID = 3;

    //CAN ID
    public static final int LEFT_LIFT_MOTOR = 5;
    public static final int RIGHT_LIFT_MOTOR = 6;
    public static final int LEFT_INTAKE_MOTOR = 12;
    public static final int RIGHT_MOTOR_FRONT = 14;
    public static final int RIGHT_MOTOR_BACK = 16;
    public static final int LEFT_MOTOR_FRONT = 11;
    public static final int LEFT_MOTOR_BACK = 13;

    //MOTOR SPEEDS
    public static final double LIFT_ARM_SPEED = 0;
    public static final double INTAKE_SPEED = 0;
    public static final double DRIVE_MAX_SPEED = 0;

    //DRIVER CONTROLS
    public static final int DRIVER_ONE = 0;
    public static final int Y_AXIS = 1;
    public static final int X_AXIS = 0;

    //ARM CONSTANTS
    public static final double MAX_ARM_ERROR = 40000;
    public static final double MAX_ARM_SPEED = 3;
    public static final double ARM_PROPORTIONAL_GAIN = 0.00001;
    public static final double FIRST_HOOK_POSITION = 150000;
    public static final double MIN_ARM_POSITION = 0;
    public static final double ALMOST_MIN_POSITION = 50000;
    public static final double HALF_ARM_POSITION = 100000;
    public static final double MAX_ARM_POSITION = 200000;
    public static final double ARM_INTEGRAL_GAIN = 0.00000003;
    public static final double ACCEPTABLE_ERROR = 8000;
}