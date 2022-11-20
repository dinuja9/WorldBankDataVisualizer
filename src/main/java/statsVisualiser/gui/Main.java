package statsVisualiser.gui;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetData data = new GetData("AG.LND.FRST.ZS", (1950)-1, 2018, "can");
		data.fetchData();
		for(int i = 0 ; i<data.valueOfYear.size(); i++) {
			System.out.println("Years: " + data.year.get(i) + " Value: " + data.valueOfYear.get(i));
			}
		}
}