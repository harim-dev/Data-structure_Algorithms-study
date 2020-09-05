package examples;

import java.util.Scanner;

//�տ� �����ϴ� LinkedList

public class DoubleLinkedList {
	class Node {
		private int data;
		private Node next;
		private Node prev;
		
		public Node() {
			this.data = 0;
			this.next = null;
			this.prev = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
		public int getData() { return this.data; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
		public void setPrev(Node node) { this.prev = node; }
		public Node getPrev() { return this.prev; }
	}
	
	private Node head;			//list�� head(ù ���)
	private Node tail;
	private int nodeNum;		//list�� �ִ� node ��
	
	public DoubleLinkedList() {		//init
		head = null;
		tail = null;
		nodeNum = 0;
	}
	
	public void Menu() {
		System.out.println("\n=======================================================================");
		System.out.print("1. INSERT\t");
		System.out.print("2. DELETE\t");
		System.out.print("3. SEARCH\t");
		System.out.print("4. PRINT\t");
		System.out.println("5. QUIT");
		System.out.println("=======================================================================");
		System.out.print("Choose the action : ");
	}
	
	public static void main(String[] argc) {
		DoubleLinkedList list = new DoubleLinkedList();
		Scanner sc = new Scanner(System.in);
		
		int data = -1;
		boolean flag = true;
		int choice = 0;
		int choice2 = 0;
		int index = 0;
		
		while(flag) {
			list.Menu();
			choice = sc.nextInt();
			
			switch(choice) {
			case 1: 		//����
				System.out.println("1. FRONT\t2. MIDDLE\t3. LAST");
				System.out.print("Add data to : ");
				choice2 = sc.nextInt();
				System.out.print("Data to insert : ");
				data = sc.nextInt();
				
				if(choice2 == 1)
					list.insertFront(data);
				else if(choice2 == 2) {
					System.out.print("Index to insert : ");
					index = sc.nextInt();
					list.insertMid(index, data);
				} else if(choice2 == 3)
					list.insertLast(data);
				else
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				break;
			case 2:			//����
				System.out.println("1. FRONT\t2. MIDDLE\t3. LAST");
				System.out.print("delete data from : ");
				choice2 = sc.nextInt();
				
				if(choice2 == 1)
					list.deleteFront();
				else if(choice2 == 2) {
					System.out.print("Index to delete : ");
					index = sc.nextInt();
					list.deleteMid(index);
				} else if(choice2 == 3)
					list.deleteLast();
				else
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				break;
			case 3:			//Ž��
				System.out.print("Data to search : ");
				data = sc.nextInt();	//Ž���� ������
				
				list.search(data);	//Ž�� ���� �� ���
				break;
			case 4:			//���
				list.print();
				break;
			case 5:			//���α׷� ����
				flag = false;
				break;
			default :		//1,2,3,4,5 �̿��� ����
				System.out.println("   Wrong choice. Choose from 1~5.");
			}
		}
		
		System.out.println("\n===FINISH PROGRAM===");
	}
	
	//����
	public void insertFront(int data) {		//list�� �տ� ����
		Node newNode = new Node(data);		//���ο� ��� ����
		
		newNode.setNext(head);	//newNode.next�� ���� head�� ����Ų��
		if(head != null)		//list�� ����ִ� ���°� �ƴ϶��
			head.setPrev(newNode);	//list�� ù����� prev�� newNode�� ����
		
		newNode.setPrev(null);	//newNode�� ù���� prev�� ����
		head = newNode;			//newNode�� head�� �ȴ�
		
		nodeNum++;		//node �� ����
		
		if(head.getNext() == null)	//node�� �ϳ� ���� ��, 
			tail = head;			//head�� tail�� ���� ��带 ����Ų��
	}
	
	public void insertLast(int data) {		//list�� �ڿ� ����
		Node newNode = new Node(data);		//���ο� ��� ����
		
		if(nodeNum == 0)			//list�� ���������
			insertFront(data);		//�տ� �߰��ϴ� �Ͱ� ����
		else {
			tail.setNext(newNode);	//���� tail.next�� newNode�� �ǰ�
			newNode.setPrev(tail);	//newNode.prev�� ���� tail�� �ǰ�
			tail = newNode;			//newNode�� tail�� �ȴ�
			
			nodeNum++;		//node �� ����
		}
	}
	
	public void insertMid(int index, int data) {	//index: ������ ��ġ
		Node newNode = new Node(data);
		
		if(index == 1)		//index�� 1�̶�� list�� �տ� ����
			insertFront(data);
		else {
			if(index > nodeNum+1) {		//list�� ��� ������ ũ��
				System.out.println("Now we have " + nodeNum + " nodes.");
				return;
			}
			
			Node preNode = findPlace(index-1);	//������ ��ġ�� ���� ���
			
			newNode.setNext(preNode.getNext());	//newNode.next = preNode.next
			newNode.setPrev(preNode);			//newNode.prev = preNode
			preNode.getNext().setPrev(newNode);	//preNode.next.prev = newNode
			preNode.setNext(newNode);			//preNode.next = newNode
			
			nodeNum++;
			
			if(newNode.getNext() == null)		//newNode.next�� null�̶��,
				tail = newNode;					//newNode�� tail�� �ȴ�
		}
	}
	
	public Node findPlace(int index) {		//index�� ���� ��带 ��ȯ
		Node current = head;
		
		for(int i=1; i<index; i++)			//node�� ó������ �����鼭 Ž��
			current = current.getNext();
		
		return current;
	}
	
	//����
	public boolean deleteFront() {			//list�� ù ��� ����
		if(head == null) {	//list�� ���������
			System.out.println("No Data.");
			return false;
		} else {			//list�� �� ���������
			head = head.getNext();	//head�� ���� head.next�� ����Ų��
			head.setPrev(null);		//���ο� head�� prev�� null��
			
			nodeNum--;
			
			if(nodeNum == 0)		//���� �� ��尡 0����
				tail = null;
			
			return true;
		}
	}
	
	public boolean deleteLast() {
		if(head == null) {	//list�� ���������
			System.out.println("No Data.");
			return false;
		} else {			//list�� �� ���������
			tail = tail.getPrev();	//tail�� ���� tail.prev�� ����Ų��
			tail.setNext(null);		//���ο� tail�� next�� null��
			
			nodeNum--;
			
			if(nodeNum == 0)		//���� �� ��尡 0����
				tail = null;
			
			return true;
		}
	}
	
	public boolean deleteMid(int index) {
		if(index == 1)		//ù ��� ����
			deleteFront();
		else {
			if(index > nodeNum) {		//list�� ��� ������ ũ��
				System.out.println("we have " + nodeNum + " nodes.");
				return false;
			}
			
			Node preNode = findPlace(index-1);	//������ ��ġ�� ���� ���
			Node delNode = preNode.getNext();	//������ ���
			
			preNode.setNext(delNode.getNext());	//������ ����� �ճ���� next�� ������ ����� next�� ����
			
			if(delNode != tail)			//������ ��尡 tail�� �ƴϸ� 
				delNode.getNext().setPrev(preNode);	//������ ����� �޳���� prev�� ������ ����� �ճ��� ����
			else						//������ ��尡 tail�̸�
				tail = preNode;			//���ο� tail ����
			
			nodeNum--;
			
			delNode = null;		//������ ����
			
			return true;
		}
		
		return false;
	}
	
	//Ž��
	public boolean search(int data) {
		if(head == null) {		//list�� ������� ��
			System.out.println("No Data.");
			return false;
		}
			
		Node current = head;	//head���� ���ʴ�� Ž��
		
		while(current != null) {
			if(current.getData() == data) {		//ã�� data�� ���� ���̸�
				System.out.println(current.getData() + " in the list.");
				return true;
			} else				//ã�� data�� �ٸ� ���̸� ���� ���� �̵�
				current = current.getNext();
		}
		
		//ã�� data�� ���� ��
		System.out.println(data +" not in the list.");
		return false;
	}
	
	//���
	public void print() {
		if(head == null) {		//head == null
			System.out.println("No Data.");
			return;
		}
		
		Node current = head;
		
		while(current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println();
	}
}