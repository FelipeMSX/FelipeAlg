package util;

public class ObjectTest {
	private int iD;
	private String name;
	
	public ObjectTest(){
		this.iD = 0;
		this.name = "";
	}

	public ObjectTest(int id, String name){
		this.iD = id;
		this.name = name;
	}



	public int compareTo(ObjectTest arg0) {
		if(this.iD > arg0.getiD())
			return 1;
		else 
		if(this.iD < arg0.getiD())
			return -1;
		else
			return 0;
	}

	@Override
	public String toString(){
		for(int i = this.name.length(); i < 3; i++)
			this.name +=" ";
		return "NOME: " + this.name+"----"+ "ID: "+ this.iD;
		
	}
	
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
