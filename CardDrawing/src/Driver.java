import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Driver {
	public static void main(String[] args) throws RserveException, REXPMismatchException {
//		RConnection c = new RConnection("localhost", 6311);  
//	     if(c.isConnected()) {  
//	       System.out.println("Connected to RServe.");  
//	       org.rosuda.REngine.REXP x0 = c.eval("R.version.string");  
//	       System.out.println(x0.asString()); 
//	       Logger log = new Logger();
//	       CardDrawingSimulator cd = new CardDrawingSimulator(log, c);
//	       cd.start();
//	       if(c.needLogin()) {  
//	         System.out.println("Providing Login");  
//	         c.login("username", "password");  
//	       }  
//	     } else {  
//	       System.out.println("Rserve could not connect");  
//	     }  
//	     c.close();  
//	     System.out.println("Session Closed");
		
		Logger log = new Logger();
		CardDrawingSimulator cd = new CardDrawingSimulator(log);
		cd.start();
	}
}
