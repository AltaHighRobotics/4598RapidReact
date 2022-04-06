
// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import com.fasterxml.jackson.annotation.JsonInclude.Include;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.subsystems.DrivetrainSub;
// import frc.robot.subsystems.IntakeSub;
// import frc.robot.subsystems.ShootingSub;

// public class RapidReactAutoCommand extends CommandBase {
//   private final DrivetrainSub m_drivetrain;
//   private final IntakeSub m_intakeSub;
//   private final ShootingSub m_shooterSub;
//   private boolean includeBall1;
//   private boolean includeBall2;
//   private boolean includeBall3;
//   private boolean includeBall4;
//   private boolean hasgoneshoot;
//   private boolean runningoutofnames;
//   private boolean isDriving;
//   private int stage;
//   private int c;
//   private int cButlikeAgain;
//   private Integer origPos;
//   private double [] waypoint;

//   public RapidReactAutoCommand(DrivetrainSub drivetrainSub, ShootingSub shootingSub, IntakeSub intakeSub, Integer InitialPosition, Boolean ball1, Boolean ball2, Boolean ball3, Boolean ball4) {
//     m_drivetrain = drivetrainSub;
//     m_shooterSub = shootingSub;
//     m_intakeSub = intakeSub;
//     isDriving = false;
//     hasgoneshoot = false;
//     runningoutofnames = false;
//     includeBall1 = ball1;
//     includeBall2 = ball2;
//     includeBall3 = ball3;
//     includeBall4 = ball4;
//     origPos = InitialPosition;
//     c = 0;
//     cButlikeAgain = 0;

//     waypoint = new double[2];
//     stage = 0;
    
//     addRequirements(shootingSub, intakeSub, drivetrainSub);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() 
//   {
//     waypoint[0] = 1;
//     waypoint[1] = 1;
//     switch (origPos)
//     {
//       case 1:
//         m_drivetrain.setPos(0, 100);
//         break;

//       case 2:
//         m_drivetrain.setPos(-57, 40);
      
//       case 3:
//         m_drivetrain.setPos(85.5, 67);
//     }
//     m_drivetrain.setPos(0, 0);
//     m_drivetrain.resetYaw();
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     m_intakeSub.IntakeOn();
//     m_intakeSub.IntakeExtend(); 
//     m_drivetrain.drivetrainPositionIntegration();
//     //System.out.println(!includeBall1);

//     if (isDriving)
//     {
//       m_drivetrain.setDriveToWaypoint(waypoint[0], waypoint[1], true);
//     }

//     switch (stage)
//     {
//       case 0:
//         cButlikeAgain++;
//         stage = 1;

//           // if(m_shooterSub.autoShoot())
//           // {
//           //   // if (m_drivetrain.pointAtWaypoint(0, 33.7))
//           //   // {
//           //   //   stage = 1;
//           //   // }
//           // }
  
//         break;

//         case 1:
//         if (!includeBall1)
//         {
//           System.out.println("doesnt work");
//           stage = 2;
//         }
//         else
//         {
//           SmartDashboard.putString("Auto Process", "HEADING WAYPOINT 1");
//           waypoint = Constants.WAYPOINT_BALL_1;
//           isDriving = true;
//           if (m_drivetrain.hasReachedWaypoint())
//           {
//             if(m_shooterSub.autoShoot())
//             {
//               hasgoneshoot = true;
//             }
//           }
//           else
//           {
//             inCommandgoShoot();
//           }
          
//         }
//         break;

//         case 2:
//         if (!includeBall2)
//         {
//           System.out.println("doesnt work");
//           stage = 3;
//         }
//         else
//         {
//           SmartDashboard.putString("Auto Process", "HEADING WAYPOINT 2");
//           waypoint = Constants.WAYPOINT_BALL_2;
//           if (m_drivetrain.hasReachedWaypoint())
//           {
//             if(m_shooterSub.autoShoot())
//             {
//               hasgoneshoot = true;
//             }
//           }
//           else
//           {
//             inCommandgoShoot();
//           }
          
//         }
//         break;

//         case 3:
//         if (!includeBall3)
//         {
//           System.out.println("doesnt work");
//           stage = 4;
//         }
//         else
//         {
//           SmartDashboard.putString("Auto Process", "HEADING WAYPOINT 3");
//           waypoint = Constants.WAYPOINT_BALL_3;
//           if (m_drivetrain.hasReachedWaypoint())
//           {
//             if(m_shooterSub.autoShoot())
//             {
//               hasgoneshoot = true;
//             }
//           }
//           else
//           {
//             inCommandgoShoot();
//           }
          
//         }
//         break;

//         case 4:
//         if (!includeBall1)
//         {
//           System.out.println("doesnt work");
//           stage = 5;
//         }
//         else
//         {
//           SmartDashboard.putString("Auto Process", "HEADING WAYPOINT 4");
//           waypoint = Constants.WAYPOINT_BALL_4;
//           if (m_drivetrain.hasReachedWaypoint())
//           {
//             if(m_shooterSub.autoShoot())
//             {
//               hasgoneshoot = true;
//             }
//           }
//           else
//           {
//             inCommandgoShoot();
//           }
          
//         }
//         break;

//       case 5:
//         SmartDashboard.putString("Auto Process", "IDLE");
//         break;
      
//       default:
//         SmartDashboard.putString("Auto Process", "ERROR BAD LOGIC");
//         break;
//     }
//     //System.out.println(waypoint[0]+ " " + waypoint[1]);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     m_drivetrain.stopMotors();
//     m_intakeSub.IntakeRetract();
//     m_intakeSub.IntakeOff();
//     m_shooterSub.stopAimingMotors();
//     m_shooterSub.stopShooterMotors();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
//   public boolean inCommandgoShoot()
//   {
//     c++;
//     waypoint = Constants.SHOOT_DISTANCE_WAYPOINT;
//     if (c > 50)
//     {
//       if(m_drivetrain.setDriveToWaypoint(waypoint[0], waypoint[1], true))
//       {
//         runningoutofnames = true;
//       }
//       if(runningoutofnames)
//       {
//         if(m_drivetrain.pointAtWaypoint(0,0))
//         {
//           if(m_shooterSub.autoShoot())
//           {
//             hasgoneshoot = false;
//             return true;
//           }
//         }
//       }
//     }
//     return false;
//   }

// }
