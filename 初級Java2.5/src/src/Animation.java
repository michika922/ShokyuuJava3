package src;

public class Animation extends MyFrame {
	public void run() {
		int y=100;
		int x=30;
		while(true) {
		while(x<=170) {
			clear();
			setColor(0,128,0);
			fillRect(x,y,10,100);
			x+=5;
			sleep(0.1);
		}
		while(x>=30) {
			clear();
			setColor(0,128,0);
			fillRect(x,y,10,100);
			x-=5;
			sleep(0.1);
		}
	}
	}

}
