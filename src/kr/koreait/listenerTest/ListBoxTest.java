package kr.koreait.listenerTest;

import java.awt.Choice;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ListBoxTest extends JFrame implements ActionListener {

	JLabel topLabel; // 원도우 상단의 제목 레이블 !!!!

	JPanel listPanel; // 리스트 박스가 올라갈 패널
	List singleList; // 단일 선택 리스트 박스
	List multiList; // 다중 선택 리스트 박스

	JPanel buttonPanel; // 보기 버튼 삭제 버튼이 올라갈 패널
	JButton showButton, deleteButton; // 보기 버튼, 삭제 버튼

	JLabel bottomLabel; // 윈도우 하단에 작업 메세지를 표시할 레이블

	public ListBoxTest() {
		setTitle("ListBox");
		setBounds(1200, 100, 400, 400);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		원도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("콤보 상자 테스트");
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setFont(new Font("D2Coding", Font.BOLD, 35));
		add(topLabel);

//		원도우 중단의 리스트 박스 세팅
//		단일 선택 리스트 박스
//		new List() : 선택할 수 있는 아이템 개수가 기본값(4개)으로 보여지는 리스트 박스를 만든다.
//		new List(아이템 개수) : 생성자의 인수로 넘겨준 아이템의 개수 만큼 목록이 보여지는 리스트 박스를 만든다.

		singleList = new List(); // 단일 선택 리스트 박스를 만든다.
		singleList.add("고구마"); // 리스트 박에 아이템을 추가한다
		singleList.add("감자");
		singleList.add("옥수수");
		singleList.add("오이");
		singleList.add("호박");
//		다중 선택 리스트 박스
//		new List(아이템 개수,목록 선택 방법) : 생성자의 인수로 넘겨준 아이템의 개수 만큼 목록이 보여지는 리스트 박스를 만들고 목록 선택방법을 
//		생략하면 false가 기본값으고 단일 목록 선택 리스트 박스가 된다 => 목록 선택 방법에 true를 쓰면 다중 목록 선택리스트 박스가 된다.
		multiList = new List(4, true); // 다중 선택 리스트 박스를 만든다.
		multiList.add("괌");
		multiList.add("코타키나발루");
		multiList.add("다낭");
		multiList.add("나트랑");
		multiList.add("푸꾸옥");
		multiList.add("호치민");
		multiList.add("달랏");
		listPanel = new JPanel();
		listPanel.add(singleList);
		listPanel.add(multiList);

		add(listPanel);
//		원도우 중단의 보기, 삭제 버튼 세팅
		showButton = new JButton("보기");
		deleteButton = new JButton("삭제");
		buttonPanel = new JPanel();
		buttonPanel.add(showButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel);

//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다. ");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);

//		보기, 삭제 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		deleteButton.addActionListener(this);

		setVisible(true);

	}

	public static void main(String[] args) {

		ListBoxTest window = new ListBoxTest();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String item = "";
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		switch (e.getActionCommand()) {
		case "보기":
//			bottomLabel.setText("왼쪽 목록 : " + singleList.getSelectedItem());
			String str = "왼쪽 목록 : ";
			if (singleList.getSelectedItem() == null) {
				str += "없음";
			} else {
				str += singleList.getSelectedItem();
			}
			str += ", 오른쪽 목록 : ";
//			다중 선택 리스트 박스는 2개 이상을 선택할 수 있으므로 선택된 항목의 정보를 배열로 받아와야 한다.
//			getSelectedIndexes()    	: 다중 선택 리스트 박스에서 선택된 아이템들의 위치를 배열로 받아온다			
//			multiList.getSelectedItem() : 다중 선택 리스트 박스에서 선택된 항목들의 위치를 배열로 받아온다.
			String[] items = multiList.getSelectedItems();
			if (items.length == 0) {
				str += "없음";
			} else {
				for (int i = 0; i < items.length; i++) {
					if (i > 0) {
						str += ", ";
					}
					str += items[i];
				}
			}
			bottomLabel.setText(str);
			break;
		case "삭제":
//			getSelectedIndexes() 메소드는 리스트 박스에서 아무것도 선택하지 않으면 -1을 리턴한다.
			if(singleList.getSelectedIndex() >= 0) {
				JOptionPane.showMessageDialog(singleList, singleList.getSelectedItem()+ "삭제 완료");
				singleList.remove(singleList.getSelectedItem());
			}else {
				JOptionPane.showMessageDialog(singleList, "왼쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
			}
//			다중 선택 리스트 박스에서 삭제할 아이템 목록의 위치를 얻어와서 배열에 넣어준다.
			int[] deleteList = multiList.getSelectedIndexes();
			String [] deleteItems = multiList.getSelectedItems();
			if(deleteList.length > 0) {
				String str1 = "";
				for (int i = 0; i < deleteList.length; i++) {
					if(i >0) {
						str1 += ", ";
					}
					str1 += deleteItems[i];
				}
				JOptionPane.showMessageDialog(multiList, str1+  "삭제 완료");
//				리스트 박스 앞쪽 부터 삭제
				int delIndex = 0;
				/*for(int i= 0; i <deleteList.length; i++ ) {
					multiList.remove(deleteList[i] - delIndex++);
				}
				for(int position : deleteList) {
					multiList.remove(position - delIndex++);
				}*/
//				리스트 박스 뒤쪽 부터 삭제
				for (int i = deleteList.length - 1; i >=0; i--) {
					multiList.remove(deleteList[i]);
				}
				
			}
			break;
		}

	}

}
