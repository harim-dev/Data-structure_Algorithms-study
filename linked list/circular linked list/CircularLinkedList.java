package examples;

import java.util.Scanner;

public class CircularLinkedList {
	class Node {
		private int data;
		private Node next;
		
		public Node() {
			this.data = 0;
			this.next = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() { return this.data; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
	}
	
	private Node tail;
	
	public CircularLinkedList() {		//init
		tail = null;
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
		CircularLinkedList list = new CircularLinkedList();
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
				System.out.println("1. FRONT\t2. LAST");
				System.out.print("Add data to : ");
				choice2 = sc.nextInt();
				System.out.print("Data to insert : ");
				data = sc.nextInt();
				
				if(choice2 == 1)
					list.insertFront(data);
				else if(choice2 == 2)
					list.insertLast(data);
				else
					System.out.println("   Wrong choice. Choose from 1~2.");
				
				break;
			case 2:			//����
				list.deleteFront();
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
		
		if(tail == null) {		//list�� ���������
			tail = newNode;
			newNode.setNext(newNode);
		} else {				//list�� ��尡 ������
			newNode.setNext(tail.getNext());	//newNode.next = tail.next(ù���)
			tail.setNext(newNode);				//tail.next�� ���ο� ��带 ����Ų��
		}
	}
	
	public void insertLast(int data) {		//list�� �ڿ� ����
		Node newNode = new Node(data);		//���ο� ��� ����
		
		if(tail == null) {		//list�� ���������
			tail = newNode;
			newNode.setNext(newNode);
		} else {				//list�� ��尡 ������
			newNode.setNext(tail.getNext());	//newNode.next = tail.next(ù���)
			tail.setNext(newNode);				//tail.next�� ���ο� ��带 ����Ų��
			tail = newNode;						//newNode�� tail�� �ȴ�
		}
	}
	
	//����
	public boolean deleteFront() {			//list�� ù ��� ����
		if(tail == null) {		//list�� ���������
			System.out.println("   No Data.");
			return false;
		} else {				//list�� �� ���������
			Node delNode = tail.getNext();		//������ ���(ù ���)
			
			if(delNode == tail)	//������ ��尡 ������ ����� �� (��尡 �ϳ��� ��)
				tail = null;
			else				//����Ʈ�� 2�� �̻� ��尡 ���� ��
				tail.setNext(tail.getNext().getNext());
			return true;
		}
	}
	
	//Ž��
	public boolean search(int data) {
		if(tail == null) {		//list�� ������� ��
			System.out.println("  No Data.");
			return false;
		}
			
		Node current = tail.getNext();	//ù ������ ���ʴ�� Ž��
		
		while(current != tail) {		//������ ��� ������ Ž��
			if(current.getData() == data) {		//ã�� data�� ���� ���̸�
				System.out.println("   " + current.getData() + " in the list.");
				return true;
			} else				//ã�� data�� �ٸ� ���̸� ���� ���� �̵�
				current = current.getNext();
		}
		
		if(current.getData() == data) {	//������ ��� Ž��
			System.out.println("   " + current.getData() + " in the list.");
			return true;
		}
		
		//ã�� data�� ���� ��
		System.out.println("   " + data +" not in the list.");
		return false;
	}
	
	//���
	public void print() {
		if(tail == null) {			//list�� ������� ��
			System.out.println("   No Data.");
			return;
		}
		
		Node current = tail.getNext();	//ù���
		
		while(current != tail) {		//������ ��� ������ ���
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		
		System.out.print(current.getData() + " ");	//������ ��� ���
		System.out.println();
	}
}