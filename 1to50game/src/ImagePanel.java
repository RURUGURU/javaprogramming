import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ImagePanel extends JPanel implements MouseListener {
	//실제게임화면을표시할패널
	
	int count = 3;//카운트다운표시용
	int x, y; //좌표값
	int check; //숫자체크용
	
	//------------------------------------
	boolean time_ck=true;
	//무한쓰레드를 탈출하기 위하여 변수를 하나주고 조건을 만족시킬때 변수 해제.(탈출용)
	//------------------------------------
	
	static String time;//클리어시간값표시용
	boolean game_start = false;
	
	Random ran_num = new Random();
	//랜덤함수
	Vector rect_select = new Vector();
	//1-50 숫자보관용벡터
	SelectRect sr;
	//숫자보관용 클래스 접근키

	ImagePanel() {
		this.addMouseListener(this);
	}
	//마우스 움직임
	
	public void countDown(int count) {
		//게임시간값을 받아와 카운트다운표시 후 0이되면 게임시작
		switch (count) {
			case 0:
				this.count = 3;
				break;
			case 1:
				this.count = 2;
				break;
			case 2:
				this.count = 1;
				break;
			case 3:
				this.count = 0;
				game_start = false;
				break;
		}
	}
	
	public void gameStart(boolean game_start) {
		//게임기본세팅
		//25개의사각박스와해당박스안에
		//랜덤으로난수를발생시켜나온숫자를받아입력한다.
		this.game_start = game_start;
		if ( this.game_start ){
			check = 1;
			
			//------------------------------------
			time_ck=true;//탈출용	변수				
			//------------------------------------
			
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 5; ++j) {
					int num = 0;
					int xx = 5 + i * 80;
					int yy = 5 + j * 80;
					//25개사각형좌표값들
					num = ran_num.nextInt(25) + 1;
					for (int k = 0; k < rect_select.size(); ++k) {
						sr = (SelectRect) rect_select.get(k);
						if ( sr.number == num ) {
							num = ran_num.nextInt(25) + 1;
							k = -1;
						}
					}
					//중복없는난수발생
					sr = new SelectRect(xx, yy, num);
					rect_select.add(sr);
					//받은좌표 및 난수값을 객체화 하여 좌표로 저장
				}
			}
		}
	}
	
	public void paint(Graphics g) {
		//화면그리기
		g.drawRect(0, 0, 400, 400);
		//게임화면사각테두리
		if (game_start) {
			//카운트다운 텍스트 그리기
			g.setFont(new Font("Default", Font.BOLD, 50));
			g.drawString("CountDown", 70, 150);

			g.setFont(new Font("Default", Font.BOLD, 100));
			g.drawString("" + count, 170, 250);
		}
		else if ( !game_start && count == 0 ){
			for (int i = 0; i < rect_select.size(); ++i) {
				sr = (SelectRect) rect_select.get(i);

				g.drawRect(sr.x, sr.y, 70, 70);
				g.setFont(new Font("Default", Font.BOLD, 30));
				g.drawString("" + sr.number, sr.x + 22, sr.y + 45);
				//저장된 각각의 숫자값을 받아 사각형과 숫자그리기
			}
			g.setColor(Color.blue);
			g.drawRect(x * 80 + 5, y * 80 + 5, 70, 70);
			//마우스로 선택된 사각박스를 푸르게표시
		}
		if ( check > 50 ){
			g.setColor(Color.blue);
			g.setFont(new Font("Default", Font.BOLD, 50));
			g.drawString("-Game Clear-", 40, 150);
			//게임 클리어 표시
			
			g.setColor(Color.red);
			g.setFont(new Font("Default", Font.PLAIN, 40));
			g.drawString("" + MainFrame.show_time, 90, 250);
			//게임 시간 표시
			
			g.setColor(Color.orange);
			g.setFont(new Font("Default", Font.BOLD, 40));
			g.drawString("name: "+Game1to50.Name, 90, 350);
			//이름과 결과 표시
		}
	}
	
	public void ClearTime(String time){
		this.time = time;
	}
	//시간 생성자 만들기
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		//마우스를 클릭할때 이벤트
		x = e.getX() / 80;
		y = e.getY() / 80;
		//클릭하는 구역 나누기
		
		if ( count == 0 ){
			//게임 시작시
			for (int i = 0; i < rect_select.size(); ++i) {
				sr = (SelectRect) rect_select.get(i);
				if (x == sr.x / 80 && y == sr.y / 80) {
					int xx = sr.x;
					int yy = sr.y;

					if ( sr.number - check == 0 ) {
						++check ;
						rect_select.remove(i);
						//1-50 숫자순서대로클릭되면해당하는숫자제거
						if ( check < 27 ){
							int num = 0;
							num = ran_num.nextInt(25) + 26;
							for (int k = 0; k < rect_select.size(); ++k) {
								sr = (SelectRect) rect_select.get(k);
								if ( sr.number == num ) {
									num = ran_num.nextInt(25) + 26;
									k = -1;
								}
							}
							//25미만일때, 새로운 숫자가 인가되고, 26부턴 인가하지 않음
							
							sr = new SelectRect(xx, yy, num);
							rect_select.add(sr);
							//제거된숫자가있으면그자리에
							//다시난수를발생시켜숫자를추가
						}
					}
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
}
