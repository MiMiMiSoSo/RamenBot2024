// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.util.CsvWriter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * Robot is timed, meaning that it will run the periodic methods at a fixed of 20ms.
 */
public class Robot extends TimedRobot {
    private RobotContainer m_robotContainer = new RobotContainer();
    // private CsvWriterCommand m_Writer = new CsvWriter();

    @Override
    public void robotInit() {

        try {
            // m_Writer.Datawrite();
        }
        catch (Exception e) {

        }

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        m_robotContainer.bindCommands();
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }
}
