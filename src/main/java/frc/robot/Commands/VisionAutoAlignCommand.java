package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CommandsConstants;
import frc.robot.Constants.CommandsConstants.VisionAutoAlignConstants;
import frc.robot.subsystems.SwerveDriveSystem;
import frc.robot.subsystems.VisionSystem;

public class VisionAutoAlignCommand extends CommandBase {
    private SwerveDriveSystem m_swerveDrive;
    private Timer m_timer;
    private PIDController m_translationXpid = new PIDController(CommandsConstants.translationPID_P,
            CommandsConstants.translationPid_I, CommandsConstants.translationPID_D);
    private PIDController m_translationYpid = new PIDController(CommandsConstants.translationPID_P,
    CommandsConstants.translationPid_I, CommandsConstants.translationPID_D);
    private PIDController m_rotationPid = new PIDController(CommandsConstants.rotationPID_P,
    CommandsConstants.rotationPID_I, CommandsConstants.rotationPID_D);

    private VisionSystem m_visionSystem;

    private double m_distanceMeters = VisionAutoAlignConstants.distanceMeters;

    public VisionAutoAlignCommand(SwerveDriveSystem swerveDrive, VisionSystem visionSystem) {
        m_swerveDrive = swerveDrive;
        m_visionSystem = visionSystem;
        m_timer = new Timer();
        addRequirements(m_swerveDrive, m_visionSystem);
    }

    @Override
    public void initialize() {
        m_timer.start();
    }

    @Override
    public void execute() {
        double xspeed = m_translationXpid.calculate(m_visionSystem.getDistanceMetersX(),
                0);
        double yspeed = m_translationYpid.calculate(m_visionSystem.getDistanceMetersX(),
                m_distanceMeters);
        double rotSpeed = m_rotationPid.calculate(m_visionSystem.getX(),
                0);

        xspeed = MathUtil
                .clamp(xspeed, -VisionAutoAlignConstants.percentPower, VisionAutoAlignConstants.percentPower);
        yspeed = MathUtil
                .clamp(yspeed, -VisionAutoAlignConstants.percentPower, VisionAutoAlignConstants.percentPower);
        rotSpeed = MathUtil
                .clamp(rotSpeed, -VisionAutoAlignConstants.percentPower, VisionAutoAlignConstants.percentPower);

        m_swerveDrive.drive(xspeed, yspeed, rotSpeed, true);
    }

    @Override
    public boolean isFinished() {
        if (m_timer.get() >= VisionAutoAlignConstants.timeLimit) {
            return true;
        }
        if (MathUtil.applyDeadband(m_visionSystem.getX(),
        VisionAutoAlignConstants.errorMarginRot) == 0 && 
        MathUtil.applyDeadband(m_visionSystem.getDistanceMetersY() - m_distanceMeters,
        VisionAutoAlignConstants.errorMarginDistance) == 0 && 
        MathUtil.applyDeadband(m_visionSystem.getDistanceMetersX(),
        VisionAutoAlignConstants.errorMarginDistance) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_swerveDrive.stopSystem();
    }
}
