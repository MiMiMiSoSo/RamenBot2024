package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveDriveSystem;

public class DriveXCommand extends CommandBase {
    protected SwerveDriveSystem m_swerveSystem;
    protected double m_speed;
    protected double m_maxTime;
    protected Timer m_timer;

    public DriveXCommand(SwerveDriveSystem swerveSystem, double speed, double maxTime) {
        m_swerveSystem = swerveSystem;
        m_speed = speed;
        m_timer = new Timer();
        m_maxTime = maxTime;
        addRequirements(m_swerveSystem);
    }

    @Override
    public void initialize() {
        m_timer.start();
    }

    @Override
    public void execute() {
        m_swerveSystem.drive(m_speed, 0, 0, true);
    }

    @Override
    public boolean isFinished() {
        if (m_timer.get() >= m_maxTime) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_swerveSystem.stopSystem();
    }
}