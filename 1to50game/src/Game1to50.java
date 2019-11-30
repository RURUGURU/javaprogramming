import java.io.IOException;

import javax.swing.JOptionPane;
public class Game1to50 {
	
static String Name;
//이름을 받는 변수(정적변수)

public static void main(String[] ar) throws IOException {
//파일 입력을 위해 예외처리를 해줌
	
	Name = JOptionPane.showInputDialog("이름을 입력해 주세요");
	JOptionPane.showMessageDialog(null, Name+"님 환영합니다.", "Game 1to 50", JOptionPane.PLAIN_MESSAGE);
	//처음 시작화면에  이름을 받아들이고 메세지 표시
	
	MainFrame mf = new MainFrame();
	//메인프레임을 실행(게임실행)
	
	
	}
}



