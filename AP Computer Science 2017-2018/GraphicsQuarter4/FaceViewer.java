import javax.swing.JFrame;

public class FaceViewer
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.setTitle("A Face");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FaceComponent component = new FaceComponent();
		frame.add(component);
		frame.setVisible(true);
	}
}