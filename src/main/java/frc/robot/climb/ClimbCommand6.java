/** Command that climbs all of the monkey bars in the 2022 FRC competion Rapid React
 * Needs a seprate command to reset this command to original values (see JackFrickedUpCommand.java)
 * @author Cracker
 * @author Icarus Innovated
 */
package frc.robot.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbingSub;

public class ClimbCommand6 extends CommandBase {
  
  private final ClimbingSub m_climbingSub;
  private double currentTarget;
    //Current target the robot is attempting to reach
  private double currentSpeed;
    //Current speed the arms should travel at
  private double currentWinchTarget;
    //Current target the arm winch is attempting to reach
  private int currentStage;
    //Current stage of climbing the robot is on

  public ClimbCommand6(ClimbingSub climbingSub) {
    m_climbingSub = climbingSub;
    addRequirements(climbingSub);
  }

  @Override
  public void initialize() {
    currentTarget = m_climbingSub.getCurrentTarget();
    currentSpeed = m_climbingSub.getCurrentSpeed();
    currentStage = m_climbingSub.getCurrentStage();
    currentWinchTarget = m_climbingSub.getCurrentWinchTarget();
  }

  @Override
  public void execute() {
    currentTarget = m_climbingSub.getCurrentTarget();
    currentSpeed = m_climbingSub.getCurrentSpeed();
    currentStage = m_climbingSub.getCurrentStage();
    currentWinchTarget = m_climbingSub.getCurrentWinchTarget();

    SmartDashboard.putNumber("Climb Stage:", currentStage);

    m_climbingSub.SetArmsWithClamp(currentTarget, currentSpeed);
      //Makes climbing arms go to the current Target
    m_climbingSub.moveArmWinchToPosition(currentWinchTarget);
      //Makes winch go to current target
      System.out.println("CASE 6");
          SmartDashboard.putString("Climb Target:", "Minimum Arm Position, Arms in");
          m_climbingSub.setCurrentTarget(Constants.MIN_ARM_POSITION);
          m_climbingSub.setCurrentSpeed(Constants.ARM_SLOW_SPEED);
          m_climbingSub.setCurrentWinchTarget(Constants.ARM_WINCH_MAX_POSITION);
          System.out.println("AAAAAAAAAAAA");
    }
  // }

  @Override
  public void end(boolean interrupted) {
    m_climbingSub.ArmsStationary();
    //Stops the arms
    m_climbingSub.setCurrentStage(2);
    //Increments the stage by one
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
