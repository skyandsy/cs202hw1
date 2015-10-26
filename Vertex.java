
public class Vertex {
	private String vertexName;
	private boolean visited;//dfs for topological sorting
	private boolean visitedPrinttopo;
	private boolean bfsVisited;
	private int dfsStartTime;
	private int dfsEndTime;
	private state stateOfv;
    public Vertex(String vertexName){
    	 this.vertexName = vertexName;
    	 this.visited = false;
    	 this.visitedPrinttopo = false;
    	 this.dfsStartTime = 0;
    	 this.dfsEndTime = 0;
    	 this.stateOfv = state.UNKNOWN;
    	 this.bfsVisited = false;
     }  
    public state getState(){
    	return this.stateOfv;
    }
    public void setTrue(){
    	this.stateOfv = state.TRUE;
    }
    public void setFalse(){
    	this.stateOfv = state.FALSE;
    }
    public void setTrueOrFalse(){
    	this.stateOfv = state.TRUEORFALSE;
    }
    public void setUnknown(){
    	this.stateOfv = state.UNKNOWN;
    }
     public void setDFSStartTime(int t){
    	 this.dfsStartTime = t;
     }
     public void setDFSEndTime(int t){
    	 this.dfsEndTime = t;
     }
     public int getDFSStartTime(){
    	 return this.dfsStartTime;
     }
     public int getDFSEndTime(){
    	 return this.dfsEndTime;
     }
	public boolean isVisited(){
		return this.visited;
	}
	public void setVisit(){
		 this.visited = true;
	}
	public boolean bfsVisited(){
		return this.bfsVisited;
	}
	public void setBfsVisit(){
		 this.bfsVisited = true;
	}
	public boolean isPrintVisited(){
		return this.visitedPrinttopo;
	}
	public void setPrintVisited(){
		 this.visitedPrinttopo = true;
	}
	public String getName(){
		return this.vertexName;
	}
	public void setName(String name){
		 this.vertexName = name;
	}
	
}
