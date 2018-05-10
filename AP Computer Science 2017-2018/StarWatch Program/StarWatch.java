import java.util.ArrayList;
import java.util.List;

public class StarWatch {
private List<DataPacket> dataPackets;
public StarWatch() { 
dataPackets = new ArrayList<DataPacket>(); 
}

// returns the data packet from the dataPackets list with 
// the highest intelligence factor that is greater than
// or equal to minIntelFactor. If the list is empty or no
// such packets are found, returns null; if the list contains 
// several packets with an intelligence factor equal to or 
// above minIntelFactor and equal to the highest value,
// returns the first such packet in the list
public DataPacket getBestPacket(double minIntelFactor) {
DataPacket bestPacket = null;
for (DataPacket dataPacket : dataPackets) {
if (dataPacket.getIntelFactor()>=minIntelFactor) {
if (bestPacket!=null) {
if (dataPacket.getIntelFactor()>bestPacket.getIntelFactor()) {
bestPacket = dataPacket;
}
} else {
bestPacket = dataPacket;
}
}
}
return bestPacket;
}

public int filterList(int minStrength, int minDuration) {
int numRemoved = 0;
for (int i = 0; i < dataPackets.size(); i++) {
if (dataPackets.get(i).getStrength()<minStrength || dataPackets.get(i).getDuration()<minDuration) {
dataPackets.remove(i);
i--;
numRemoved++;
}
}
return numRemoved;
}

public List alienMessage(int minStrength, int minDuration, double minIntelFactor) {
filterList(minStrength, minDuration);
DataPacket bestPacket = getBestPacket(minIntelFactor);
if (bestPacket != null) {
ArrayList<DataPacket> matchedPackets = new ArrayList<DataPacket>();
for (DataPacket dataPacket : dataPackets) {
if (dataPacket.getSource().equalsIgnoreCase(bestPacket.getSource()) && dataPacket.getQuadrant()==bestPacket.getQuadrant()) {
matchedPackets.add(dataPacket);
}
}
return matchedPackets;
} else {
return null;
}

}

public void addDataPacket(DataPacket dataPacket) {
	dataPackets.add(dataPacket);
}

// ... other methods and instance variables not shown
}
