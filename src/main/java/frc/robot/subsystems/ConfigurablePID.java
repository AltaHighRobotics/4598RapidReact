package frc.robot.subsystems;
public class ConfigurablePID {
    private double PROPORTIONAL_GAIN = 0;
    private double INTEGRAL_GAIN = 0;
    private double DERIVITIVE_GAIN = 0;

    private double MAX_PROPORTIONAL = 0;
    private double MAX_INTEGRAL = 0;
    private double MAX_DERIVITIVE = 0;

    private double proportional = 0;
    private double integral = 0;
    private double derivitive = 0;

    private double difference = 0;
    private double newDifference = 0;
    private double differenceDelta = 0;

    public ConfigurablePID(double p, double i, double d, double maxP, double maxI, double maxD) {
        PROPORTIONAL_GAIN = p;
        INTEGRAL_GAIN = i;
        DERIVITIVE_GAIN = d;

        MAX_PROPORTIONAL = maxP;
        MAX_INTEGRAL = maxI;
        MAX_DERIVITIVE = maxD;
    }

    public double runPID(double target, double current) {
        this.newDifference = target - current;
        this.differenceDelta = this.newDifference - this.difference;
        this.difference = this.newDifference;

        this.proportional = clamp(difference * this.PROPORTIONAL_GAIN, -this.MAX_PROPORTIONAL, this.MAX_PROPORTIONAL);
        this.integral = clamp(this.integral + (this.difference * this.INTEGRAL_GAIN), -this.MAX_INTEGRAL, this.MAX_INTEGRAL);
        this.derivitive = clamp(this.differenceDelta * this.DERIVITIVE_GAIN, -this.MAX_DERIVITIVE, this.MAX_DERIVITIVE);

        return(this.proportional + this.integral + this.derivitive);
    }

    private double clamp(double value, double min, double max) {
        return(Math.min(Math.max(value, min), max));
    }
}