package kr.koreait.listenerTest;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ListBoxTest2 extends JFrame implements ActionListener {
	
	JLabel topLabel;					// 윈도우 상단의 제목 레이블
	
	JPanel listPanel;					// 리스트 박스가 올라갈 패널
	
//	List singleList;					// 단일 선택 리스트 박스(java.awt 패키지)
	JList<String> singleList;			// 단일 선택 리스트 박스(javax.swing 패키지)
	DefaultListModel<String> single;	// singleList에 표시할 아이템 목록을 저장하는 객체
	
//	List multiList;						// 다중 선택 리스트 박스(java.awt 패키지)
	JList<String> multiList;			// 다중 선택 리스트 박스(javax.swing 패키지)
	DefaultListModel<String> multi;		// multiList에 표시할 아이템 목록을 저장하는 객체
	
	JPanel buttonPanel;					// 보기, 삭제 버튼이 올라갈 패널
	JButton showButton;					// 보기 버튼
	JButton deleteButton;				// 삭제 버튼
	
	JLabel bottomLabel;					// 윈도우 하단에 작업 메시지를 표시할 레이블
	
	public ListBoxTest2() {
		setTitle("ComboBox");
		setBounds(1200, 100, 400, 400);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		윈도우 상단의 제목 레이블 세팅
		topLabel = new JLabel("리스트 박스 테스트");
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setFont(new Font("D2Coding", Font.BOLD, 35));
		add(topLabel);
		
//		윈도우 중단의 리스트 박스 세팅
		listPanel = new JPanel();
		
//		단일 선택 리스트 박스를 만들고 아이템을 넣어준다.
		single = new DefaultListModel<String>();	// JList로 구현한 리스트 박스에 표시할 아이템을 저장할 DefaultListModel 객체를 만든다.
		single.addElement("고구마");				// JList로 구현한 리스트 박스에 표시할 아이템을 DefaultListModel 객체에 넣어준다.
		single.addElement("감자");
		single.addElement("옥수수");
		single.addElement("오이");
		single.addElement("호박");
		single.addElement("양파");
//		JList 클래스 객체를 생성할 때 생성자의 인수로 리스트 박스에 표시할 아이템을 저장한 DefaultListModel 객체를 넘겨준다.
		singleList = new JList<String>(single);
//		JList 클래스는 FlowLayout을 사용하는 경우 스크롤 바를 표시할 수 없으므로 스크롤 바를 표시하기 위해 JScrollPane 클래스의 객체를 생성할 
//		때 생성자의 인수로 JList 클래스 객체를 인수로 넘겨서 스크롤 바를 만든다.
//		=> 윈도우에는 JList 클래스 객체가 아닌 JScrollPane 클래스의 객체를 넣어준다.
		JScrollPane jsSingle = new JScrollPane(singleList);
//		FlowLayout을 사용하는 경우 반드시 JScrollPane 클래스의 객체의 크기를 setPreferredSize() 메소드로 지정해야 스크롤 바가 표시된다.
		jsSingle.setPreferredSize(new Dimension(120, 75));
		
//		setSelectionMode() 메소드로 JList 클래스로 생성한 리스트 박스의 선택 모드를 변경한다. => MULTIPLE_INTERVAL_SELECTION이 기본값
//		ListSelectionModel.MULTIPLE_INTERVAL_SELECTION => 다중 선택 모드, ctrl 키와 shift 키를 사용하는 다중 선택이 가능하다.
//		ListSelectionModel.SINGLE_INTERVAL_SELECTION => 다중 선택 모드, shift 키를 사용하는 다중 선택이 가능하다.
//		ListSelectionModel.SINGLE_SELECTION => 단일 선택 모드
		singleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listPanel.add(jsSingle);
		
//		다중 선택 리스트 박스를 만들고 아이템을 넣어준다.
		multi = new DefaultListModel<String>();
		multi.addElement("괌");
		multi.addElement("코타키나발루");
		multi.addElement("다낭");
		multi.addElement("나트랑");
		multi.addElement("푸꾸옥");
		multi.addElement("호치민");
		multiList = new JList<String>(multi);
		JScrollPane jsMulti = new JScrollPane(multiList);
		jsMulti.setPreferredSize(new Dimension(120, 75));
		multiList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listPanel.add(jsMulti);
		
		add(listPanel);
		
//		윈도우 중단의 보기, 삭제 버튼 세팅
		showButton = new JButton("보기");
		deleteButton = new JButton("삭제");
		buttonPanel = new JPanel();
		buttonPanel.add(showButton);
		buttonPanel.add(deleteButton);
		add(buttonPanel);
		
//		윈도우 하단의 레이블 세팅
		bottomLabel = new JLabel("이곳에 작업 결과가 표시됩니다.");
		bottomLabel.setHorizontalAlignment(JLabel.CENTER);
		add(bottomLabel);
		
//		보기, 삭제 버튼에 ActionListener를 걸어준다.
		showButton.addActionListener(this);
		deleteButton.addActionListener(this);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		ListBoxTest2 window = new ListBoxTest2();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		어떤 버튼에서 ActionListener가 실행되었나 판단해서 적당한 작업을 한다.
		String item = "";
		switch(e.getActionCommand()) {
			case "보기":
//				bottomLabel.setText("왼쪽 목록 : " + singleList.getSelectedItem());
				String str = "왼쪽 목록 : ";
//				getSelectedValue() : JList 클래스로 만든 단일 목록 리스트 박스에서 선택한 항목의 아이템을 얻어온다.			
				if(singleList.getSelectedValue() == null) {
					str += "없음";
				} else {
					str += singleList.getSelectedValue();
				}
				str += ", 오른쪽 목록 : ";
//				다중 선택 리스트 박스는 2개 이상을 선택할 수 있으므로 선택된 아이템의 정보를 배열로 받아와야 한다.
//				getSelectedIndexes() : 다중 선택 리스트 박스에서 선택된 아이템들의 위치를 배열로 받아온다.
//				List 인터페이스 타입으로 얻어오기 떄문에 ArrayList로 형변환 시켜서 받아준다.				
				ArrayList<String> items = (ArrayList<String>)multiList.getSelectedValuesList();
				if(items.size() == 0) {
					str += "없음";
				} else {
					for(int i = 0; i < items.size(); i++) {
						if(i > 0) {
							str += ", ";
						}
						str += items.get(i);
					}
				}
				bottomLabel.setText(str);
				break;
			case "삭제":
//				getSelectedIndex() 메소드는 리스트 박스에서 아무것도 선택하지 않으면 -1을 리턴한다.
				if(singleList.getSelectedIndex() >= 0) {
					JOptionPane.showMessageDialog(singleList, singleList.getSelectedValue() + " 삭제 완료!!!");
//				JList에 데이터를 추가할 때 DefaultListModel 클래스 객체에 addElement() 메소드를 사용해 아이템을 추가했던 것 처럼					
					single.remove(singleList.getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(singleList, "왼쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
				}
//				다중 선택 리스트 박스에서 삭제할 아이템 목록의 위치를 얻어와서 배열에 넣어준다.
//				getSelectedIndices() : JList로 구현한 다중 목록 리스트 박스에서 여러개의 아이템을 선택했을 경우 선택된 목록의 인덱스를 배열로 
//				얻어온다.
				int[] deletList = multiList.getSelectedIndices();
				ArrayList<String> deleteItems = (ArrayList<String>)multiList.getSelectedValuesList();
				if(deletList.length > 0) {
					String str1 = "";
					for(int i = 0; i < deletList.length; i++) {
						if(i > 0) {
							str1 += ", ";
						}
						str1 += deleteItems.get(i);
					}
					JOptionPane.showMessageDialog(multiList, str1 + " 삭제 완료!!!");
//					리스트 박스 앞쪽 부터 삭제
					int delIndex = 0;
					for(int i = 0; i < deletList.length; i++) {
						multi.removeElementAt(deletList[i] - delIndex++);
						}
//					for(int position : deletList) {
//						multi.removeElementAt(deletList[i] - delIndex++);
//					}
					
//					리스트 박스 뒤쪽 부터 삭제
					for(int i = deletList.length - 1; i >= 0; i--) {
						multi.removeElementAt(deletList[i]);
					}
					
				} else {
					JOptionPane.showMessageDialog(singleList, "오른쪽 리스트 박스에서 아무것도 선택하지 않았습니다.");
				}
				break;
		}
		
	}

}























