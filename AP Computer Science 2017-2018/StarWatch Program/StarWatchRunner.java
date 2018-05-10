import java.util.ArrayList;
import java.util.List;

public class StarWatchRunner {

	public static void main(String[] args) {
		StarWatch starWatch = new StarWatch();
		starWatch.addDataPacket(new Signals("1", 3, 5, 3, 6));
		starWatch.addDataPacket(new Signals("2", 3, 7, 3, 6));
		starWatch.addDataPacket(new Signals("7", 2, 5, 3, 7));
		starWatch.addDataPacket(new Signals("4", 3, 3, 3, 6));
		starWatch.addDataPacket(new Signals("5", 3, 8, 3, 6));
		starWatch.addDataPacket(new Signals("6", 3, 8, 3, 6));
		starWatch.addDataPacket(new Signals("7", 3, 8, 3, 8));
		starWatch.addDataPacket(new Signals("8", 3, 8, 3, 6));
		starWatch.addDataPacket(new Signals("7", 3, 8, 3, 9));
		starWatch.addDataPacket(new Signals("10", 3, 8, 3, 6));

		System.out.println(starWatch.getBestPacket(7).getSource());
		System.out.println(starWatch.filterList(3,  7));
		List<DataPacket> stuff = starWatch.alienMessage(3, 7, 4);
		for (DataPacket packet : stuff) {
			System.out.println(packet.getSource());
		}
	}

}
