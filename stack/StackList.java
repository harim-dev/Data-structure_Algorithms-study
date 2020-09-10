import java.util.Scanner;

public class StackList {
	class Node {		//����ü
		private int data;
		public Node next;
		
		public Node() {
			this.data = 0;
			this.next = null;
		}
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() { return this.data; }
	}
	
	private Node head;
	private int stackSize;
	private int nodeNum;
	
	//init
	public StackList(int size) {
		head = null;
		this.stackSize = size;
		this.nodeNum = 0;
	}
		
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int size = 0;
		while(size <= 0) {			//stack ������
			System.out.print("Input stack size: ");
			size = sc.nextInt();
			
			if(size <= 0)
				System.out.println("Size should be greater than 0.");
		}
		StackList stack = new StackList(size);		//init
		
		boolean flag = true;
		int choice = -1;
		
		while(flag) {
			System.out.println("\n=========================================================");
			System.out.println("1. push   2. pop   3. size   4. empty   5. peek   6. quit");
			System.out.println("=========================================================");
			System.out.print("Choose the action : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.print("Data to push : ");
				stack.push(sc.nextInt());
				break;
			case 2:
				stack.pop();
				break;
			case 3:
				System.out.println("There are " + stack.nodeNum + " elements in Stack.");
				break;
			case 4:
				if(stack.isEmpty())
					System.out.println("There is no element in Queue.");
				else
					System.out.println("There are elements in Queue.");
				break;
			case 5:
				stack.peek();
				break;
			case 6:
				flag = false;
				break;
			default:
				System.out.println("   Wrong choice. Choose from 1~6.");	
			}
		}
		
		System.out.println("===QUIT===");
		
		sc.close();
	}
	
	//������ ����
	public void push(int data) {
		Node newNode = new Node(data);
		
		if(isFull()) {				//���� �� á�� ���
			System.out.println("The Stack is full.");
			return;
		} else if(isEmpty()) {		//������ ������� ��� == ù ���
			this.head = newNode;	//�� ��尡 head�� �ȴ�
		} else {
			newNode.next = this.head;	//�� ��尡 ���� head�� �տ� ���� 
			this.head = newNode;		//�� ��尡 head�� �ȴ�
		}
		
		nodeNum++;
		
		System.out.println("push " + data);
	}
	
	//������ ������ ���� (����)
	public int pop() {
		if(isEmpty()) {				//������� ���
			System.out.println("No element to pop.");
			return -1;
		}
		
		//�� ����ִٸ�
		Node topNode = this.head;
		this.head = this.head.next;
		
		System.out.println("pop " + topNode.getData());
		
		nodeNum--;
		
		return topNode.getData();
	}
	
	//������ ������ ��ȯ(����X)
	public int peek() {
		if(isEmpty()) {				//������� ���
			System.out.println("No element to pop.");
			return -1;
		}
		
		//�� ����ִٸ�
		System.out.println("The current top element is " + head.getData());
		
		return head.getData();
	}
	
	//���� ����ִ��� Ȯ��
	public boolean isEmpty() {
		if(head == null)
			return true;
		else
			return false;
	}
	
	//������ á���� Ȯ��
	public boolean isFull() {
		if(isEmpty())				//���������
			return false;
		else						//�� ������� ��
			return ((this.stackSize) == nodeNum);	//���� ��� �� & ���� ũ�� ��
	}
}