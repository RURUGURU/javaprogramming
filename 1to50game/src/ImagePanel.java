import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ImagePanel extends JPanel implements MouseListener {
	//��������ȭ����ǥ�����г�
	
	int count = 3;//ī��Ʈ�ٿ�ǥ�ÿ�
	int x, y; //��ǥ��
	int check; //����üũ��
	
	//------------------------------------
	boolean time_ck=true;
	//���Ѿ����带 Ż���ϱ� ���Ͽ� ������ �ϳ��ְ� ������ ������ų�� ���� ����.(Ż���)
	//------------------------------------
	
	static String time;//Ŭ����ð���ǥ�ÿ�
	boolean game_start = false;
	
	Random ran_num = new Random();
	//�����Լ�
	Vector rect_select = new Vector();
	//1-50 ���ں����뺤��
	SelectRect sr;
	//���ں����� Ŭ���� ����Ű

	ImagePanel() {
		this.addMouseListener(this);
	}
	//���콺 ������
	
	public void countDown(int count) {
		//���ӽð����� �޾ƿ� ī��Ʈ�ٿ�ǥ�� �� 0�̵Ǹ� ���ӽ���
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
		//���ӱ⺻����
		//25���ǻ簢�ڽ����ش�ڽ��ȿ�
		//�������γ������߻����ѳ��¼��ڸ��޾��Է��Ѵ�.
		this.game_start = game_start;
		if ( this.game_start ){
			check = 1;
			
			//------------------------------------
			time_ck=true;//Ż���	����				
			//------------------------------------
			
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 5; ++j) {
					int num = 0;
					int xx = 5 + i * 80;
					int yy = 5 + j * 80;
					//25���簢����ǥ����
					num = ran_num.nextInt(25) + 1;
					for (int k = 0; k < rect_select.size(); ++k) {
						sr = (SelectRect) rect_select.get(k);
						if ( sr.number == num ) {
							num = ran_num.nextInt(25) + 1;
							k = -1;
						}
					}
					//�ߺ����³����߻�
					sr = new SelectRect(xx, yy, num);
					rect_select.add(sr);
					//������ǥ �� �������� ��üȭ �Ͽ� ��ǥ�� ����
				}
			}
		}
	}
	
	public void paint(Graphics g) {
		//ȭ��׸���
		g.drawRect(0, 0, 400, 400);
		//����ȭ��簢�׵θ�
		if (game_start) {
			//ī��Ʈ�ٿ� �ؽ�Ʈ �׸���
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
				//����� ������ ���ڰ��� �޾� �簢���� ���ڱ׸���
			}
			g.setColor(Color.blue);
			g.drawRect(x * 80 + 5, y * 80 + 5, 70, 70);
			//���콺�� ���õ� �簢�ڽ��� Ǫ����ǥ��
		}
		if ( check > 50 ){
			g.setColor(Color.blue);
			g.setFont(new Font("Default", Font.BOLD, 50));
			g.drawString("-Game Clear-", 40, 150);
			//���� Ŭ���� ǥ��
			
			g.setColor(Color.red);
			g.setFont(new Font("Default", Font.PLAIN, 40));
			g.drawString("" + MainFrame.show_time, 90, 250);
			//���� �ð� ǥ��
			
			g.setColor(Color.orange);
			g.setFont(new Font("Default", Font.BOLD, 40));
			g.drawString("name: "+Game1to50.Name, 90, 350);
			//�̸��� ��� ǥ��
		}
	}
	
	public void ClearTime(String time){
		this.time = time;
	}
	//�ð� ������ �����
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		//���콺�� Ŭ���Ҷ� �̺�Ʈ
		x = e.getX() / 80;
		y = e.getY() / 80;
		//Ŭ���ϴ� ���� ������
		
		if ( count == 0 ){
			//���� ���۽�
			for (int i = 0; i < rect_select.size(); ++i) {
				sr = (SelectRect) rect_select.get(i);
				if (x == sr.x / 80 && y == sr.y / 80) {
					int xx = sr.x;
					int yy = sr.y;

					if ( sr.number - check == 0 ) {
						++check ;
						rect_select.remove(i);
						//1-50 ���ڼ������Ŭ���Ǹ��ش��ϴ¼�������
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
							//25�̸��϶�, ���ο� ���ڰ� �ΰ��ǰ�, 26���� �ΰ����� ����
							
							sr = new SelectRect(xx, yy, num);
							rect_select.add(sr);
							//���ŵȼ��ڰ���������ڸ���
							//�ٽó������߻����Ѽ��ڸ��߰�
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
