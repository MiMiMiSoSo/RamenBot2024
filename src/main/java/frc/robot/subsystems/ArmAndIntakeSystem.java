// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;

/**
 * SwerveDriveSystem.
 */
public class ArmAndIntakeSystem extends SubsystemBase {

    public final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.intakeMotorID,
            MotorType.kBrushless);
    public final CANSparkMax m_armMotor = new CANSparkMax(ArmConstants.armMotorID,
            MotorType.kBrushless);
    public final DutyCycleEncoder m_ArmEncoder = new DutyCycleEncoder(
            ArmConstants.armEncoderChannel);
    public DigitalInput refelectometer = new DigitalInput(IntakeConstants.reflectChannel);
    public double Speed;

    public ArmAndIntakeSystem() {
        initShuffleBoard();

    }

    public void setIntakeSpeed(double SPEED) {
        Speed = SPEED;
        m_intakeMotor.set(Speed);
    }

    public double getIntakeSpeed() {
        return m_intakeMotor.get();

    }

    public double getArmAngle() {
        return m_ArmEncoder.getAbsolutePosition();

    }

    public boolean getReflectometer() {
        return refelectometer.get();
    }

    public void setArmSpeed(double speed) {
        m_armMotor.set(speed);

    }

    @Override
    public void periodic() {
        // Shuffleboard.getTab("Sensor").addBoolean(getName(), null);
        // Shuffleboard.getTab("Swerve").add("X Pose Meters", m_odometry.getPoseMeters().getX())

    }

    public void initShuffleBoard() {

        Shuffleboard.getTab("Arm and Intake").add("Arm Angle: ",
                m_ArmEncoder.getAbsolutePosition());
        Shuffleboard.getTab("Arm and Intake").add("Intake Speed: ", getIntakeSpeed());

    }

    /**
     * Stop the swerve drive system.
     */
    public void stopSystem() {
        m_intakeMotor.stopMotor();
        m_armMotor.stopMotor();
    }
}
