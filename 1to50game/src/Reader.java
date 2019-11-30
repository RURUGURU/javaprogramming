import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {
	//파일 읽어들이는 클래스 생성
	public void print(){
   try {
        BufferedReader br = new BufferedReader(new FileReader("score.txt"));
        //버퍼를 이용하여 score안에 있는 값을 순서대로 읽어온다.
        System.out.println("--------------------SCORE----------------------------");
        
        while(true) {
            String line = br.readLine();
            if (line==null) break;
           //라인 하나씩 읽어오고 값이 없을때 종류하고 프린트를 한다. 
            System.out.println(line);
        
        }
        
        System.out.println("-----------------------------------------------------");
        
        br.close();
        //닫는다.
    }
    catch(Exception e){
    	
    }
    
}
}

