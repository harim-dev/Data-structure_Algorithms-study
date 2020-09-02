package examples;

//�տ� �����ϴ� LinkedList

public class SingleLinkedList {
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
		
		public int getData() {
			return this.data;
		}
		
		public void setNext(Node node) {
			this.next = node;
		}
		
		public Node getNext() {
			return this.next;
		}
	}
	
	private Node head;			//list�� head(ù ���)
	private Node tail;			//list�� tail(������ ���)
	private int nodeNum;		//list�� �ִ� node ��
	
	public SingleLinkedList() {		//init
		head = null;
		tail = null;
		nodeNum = 0;
	}
	
	public static void main(String[] argc) {
		SingleLinkedList list = new SingleLinkedList();
		
		System.out.print("[PRINT] ");
		list.print();
		
		System.out.print("[INSERT FRONT 2] ");
		list.insertFront(2);
		list.print();
		
		System.out.print("[INSERT FRONT 1] ");
		list.insertFront(1);
		list.print();
		
		System.out.print("[INSERT LAST 4] ");
		list.insertLast(4);
		list.print();
		
		System.out.print("[SEARCH 3] ");
		list.search(3);
		
		System.out.print("[INSERT MID 3] ");
		list.insertMid(3, 3);
		list.print();
		
		System.out.print("[SEARCH 3] ");
		list.search(3);
		
		System.out.print("[DELETE MID 4] ");
		list.deleteMid(4);
		
		System.out.print("[DELETE FRONT] ");
		list.deleteFront();
		list.print();
		
		System.out.print("[DELETE MID 5] ");
		list.deleteMid(5);
	}
	
	//����
	public void insertFront(int data) {		//list�� �տ� ����
		Node newNode = new Node(data);		//���ο� ��� ����
		
		newNode.setNext(head);	//newNode.next�� ���� head�� ����Ű��
		head = newNode;			//newNode�� head�� �ȴ�
		
		nodeNum++;		//node �� ����
		
		if(head.getNext() == null)	//node�� �ϳ� ���� ��, 
			tail = head;			//head�� tail�� ���� ��带 ����Ų��
	}
	
	public void insertLast(int data) {		//lise�� �ڿ� ����
		Node newNode = new Node(data);		//���ο� ��� ����
		
		if(nodeNum == 0)			//list�� ���������
			insertFront(data);		//�տ� �߰��ϴ� �Ͱ� ����
		else {
			tail.setNext(newNode);	//���� tail.next�� newNode�� �ǰ�
			tail = newNode;			//newNode�� tail�� �ȴ�
			
			nodeNum++;		//node �� ����
		}
	}
	
	public void insertMid(int index, int data) {	//index: ������ ��ġ
		if(index == 1)		//index�� 1�̶�� list�� �տ� ����
			insertFront(data);
		else {
			if(index > nodeNum+1) {		//list�� ��� ������ ũ��
				System.out.println("Now we have " + nodeNum + " nodes.");
				return;
			}
			
			Node preNode = findPlace(index-1);	//������ ��ġ�� ���� ���
			Node newNode = new Node(data);
			
			newNode.setNext(preNode.getNext());	//newNode.next = preNode.next
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
			nodeNum--;
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
			
			nodeNum--;
			
			if(delNode == tail)
				tail = preNode;
			
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