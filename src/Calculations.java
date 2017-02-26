
public class Calculations {
	Runway runway;
	int reThreshold;//Displaced threshold
	int reLda;//Recalculated landing distance available
	int obsLoc;//Distance from runway
	int obsHeight;
	
	public Calculations(Runway runway, int obsheight,int loc){
		this.runway = runway;
		reThreshold=runway.getThreasholdDisplacement();//initially set to default
		reLda= runway.getLDA();//initially set to default
		this.obsHeight=obsheight;
		obsLoc=loc;
	}
	
	public void calculateLda(){
		reLda=runway.getTORA()-obsLoc-(obsHeight*50)-60;
		reThreshold = runway.getTODA()-reLda;
	}
	
	
	public int getdThreshold() {
		reThreshold= runway.getTODA()-reLda;
		return reThreshold;
	}
	
	
	public int getReLda() {
		return reLda;
	}
	

}
