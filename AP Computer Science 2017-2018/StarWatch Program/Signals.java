
public class Signals implements DataPacket {

	String source;
	int quadrant;
	double intelFactor;
	int strength;
	int duration;
	
	public Signals(String sourceIn, int quadrantIn, double intelFactorIn, int strengthIn, int durationIn) {
		source = sourceIn;
		quadrant = quadrantIn;
		intelFactor = intelFactorIn;
		strength = strengthIn;
		duration = durationIn;
	}
	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return source;
	}

	@Override
	public int getQuadrant() {
		// TODO Auto-generated method stub
		return quadrant;
	}

	@Override
	public double getIntelFactor() {
		// TODO Auto-generated method stub
		return intelFactor;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return strength;
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return duration;
	}

}
