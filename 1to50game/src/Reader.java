import java.io.BufferedReader;
import java.io.FileReader;

public class Reader {
	//���� �о���̴� Ŭ���� ����
	public void print(){
   try {
        BufferedReader br = new BufferedReader(new FileReader("score.txt"));
        //���۸� �̿��Ͽ� score�ȿ� �ִ� ���� ������� �о�´�.
        System.out.println("--------------------SCORE----------------------------");
        
        while(true) {
            String line = br.readLine();
            if (line==null) break;
           //���� �ϳ��� �о���� ���� ������ �����ϰ� ����Ʈ�� �Ѵ�. 
            System.out.println(line);
        
        }
        
        System.out.println("-----------------------------------------------------");
        
        br.close();
        //�ݴ´�.
    }
    catch(Exception e){
    	
    }
    
}
}

