
public class Calculations {
	Runway runway;//Runway object containing details of the runway
	int reLda;//Recalculated landing distance available
	int obsLoc;//Distance of obstacle from runway
	int obsHeight;//get height of obstacle
	int reASDA;//Recalculated Accelerate- Stop Distance	Available (ASDA)
	int reTODA;//Recalculated Take-Off Distance Available	
	int reTORA;//Recalculated Take-Off Run Available
	int ALS;//Approach Landing Surface (height *50)	 
	int RESA;//Runway End Safety Area (RESA)
	int engineBlastAllowance;//blast allowance depends on the aircraft
	
	
	
	public Calculations(Runway runway, int obsheight,int loc, int blastAllowence){
		this.runway = runway;
		this.obsHeight=obsheight;
		obsLoc=loc;
		ALS =0;
		RESA=240;
	}

	
	//Call the appropriate method according to condition
	public void calculateLda(String direction){
		if (direction=="towards")
			calculateLdaTowards();
		else if(direction=="over")
			calculateLdaOver();
	}

	
	//Calculate the landing distance over the obstacle
	public void calculateLdaOver(){
		reLda=runway.getTORA()-obsLoc-getALS()-60;
	}
	
	
	//Calculate the landing distance towards obstacle
	public void calculateLdaTowards(){
		reLda= obsLoc-RESA-60;//Deduction from usable runway
	}
	
	
	//Call the appropriate method according to condition
	public void calculateTORA(String direction){
		if (direction=="towards")
			calculateTORATowards();
		else if(direction=="after")
			calculateTORAAfter();
	}
	
	
	//Calculate taking off distance towards obstacle
	public void calculateTORATowards(){
		reTORA=obsLoc+runway.getThreasholdDisplacement()-getALS()-60;
		reTODA=reTORA;
		reASDA=reTORA;
	}
	
	//Calculate taking off distance after the obstacle(Displaced distance)
	public void calculateTORAAfter(){
		reTORA = runway.getTORA()-obsLoc-engineBlastAllowance;
		reTODA = runway.getTODA()-obsLoc-engineBlastAllowance;
		reASDA = runway.getASDA()-obsLoc-engineBlastAllowance;
	}

	
//==========getter methods below============
	
	
	public int getReLda() {
		return reLda;
	}
	
	public int getALS(){
		ALS= obsHeight*50;
		return ALS;
	}
	public int getReASDA() {
		return reASDA;
	}
	
	public int getRESA() {
		return RESA;
	}
	
	
	public int getReTODA() {
		return reTODA;
	}
	public int getReTORA() {
		return reTORA;
	}

}
