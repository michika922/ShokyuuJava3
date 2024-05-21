package src;

public class RectIf extends MyFrame {
	public void run() {
		int x=-10;
		int y=-10;
		int z=10;
		int i=10;
		while(x<=170) {
			System.out.println(x);
			fillRect(x,y,z,i);
			x+=z;
			y+=i;
			z+=5;
			i+=5;
		}
		
		
		
	}
}
	


