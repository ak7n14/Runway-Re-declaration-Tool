package Model;
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
	
	
	
	public Calculations(Runway runway, int obsheight,int loc){
		this.runway = runway;
		this.obsHeight=obsheight;
		obsLoc=loc;
		ALS =0;
		RESA=240;
		engineBlastAllowance=300;//For now later can be taken as input(Depends on flight)
		//Setting Default values;
		reASDA = runway.getASDA();
		reTORA = runway.getTORA();
		reTODA = runway.getTODA();
		reLda = runway.getLDA();
	}
	
	public Calculations(Runway runway, int obsheight,int loc,int RESA,int eng){
		this.runway = runway;
		this.obsHeight=obsheight;
		obsLoc=loc;
		ALS =0;
		this.RESA=RESA;
		engineBlastAllowance=eng;//For now later can be taken as input(Depends on flight)
	}
	
	//Call the appropriate method according to condition
	public void calculateLda(String direction){
		if (direction=="Towards")
			calculateLdaTowards();
		else if(direction=="Away")
			calculateLdaOver();
	}

	
	//Calculate the landing distance over the obstacle
	void calculateLdaOver(){
		reLda=runway.getLDA()-obsLoc-getALS()-60;
	}
	
	
	//Calculate the landing distance towards obstacle
	void calculateLdaTowards(){
		reLda= obsLoc-RESA-60;//Deduction from usable runway Strip End
	}
	
	
	//Call the appropriate method according to condition
	public void calculateTORA(String direction){
		if (direction=="Towards")
			calculateTORATowards();
		else if(direction=="Away")
			calculateTORAAfter();
	}
	
	public int getStartPoint(String action, String direction) throws Exception{
		if(action == "Landing"){
			if(direction == "Towards"){
				return getSPLandindTowards();
			}
			else if(direction == "Away"){
				return getSPLandindOver();
			}
		}
		else if (action == "Taking off"){
				if(direction == "Towards"){
					getSPTakingOffTowards();
				}
				else if(direction == "Away"){
					getSPTakingoffAfter();
				}
		}
		throw new Exception("Wrong input");
	}
	
	//Calculate taking off distance towards obstacle
	void calculateTORATowards(){
		reTORA=obsLoc+runway.getThreasholdDisplacement()-getALS()-60;
		reTODA=reTORA;
		reASDA=reTORA;
	}
	
	//Calculate taking off distance after the obstacle(Displaced distance)
	void calculateTORAAfter(){
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
	

	public int getStopWay(){
		return reASDA-reTORA;
	}
	
	public int getClearWay(){
		return reTODA-reTORA;
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
	
	public Runway getRunway() {
		return runway;
	}
	
	public int getObsLoc() {
		return obsLoc;
	}
	public int getEngineBlastAllowance() {
		return engineBlastAllowance;
	}
	
	public void setEngineBlastAllowance(int engineBlastAllowance) {
		this.engineBlastAllowance = engineBlastAllowance;
	}
	
	public void setRESA(int RESA) {
		this.RESA = RESA;
	}
	//Start Point taking off towards
	public int getSPTakingOffTowards(){
		return 0;
	}
	//Start point landing over
	public int getSPLandindOver(){
		return reLda-runway.getLDA();
	}
	//Start point landing towards
	public int getSPLandindTowards(){
		return 0;
	}
	//start point taking off after
	public int getSPTakingoffAfter(){
		return runway.getTORA()-reTORA;
	}

}
