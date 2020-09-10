import java.util.Scanner;

public class QueueList {
	class Node {		//����ü
		private int data;
		public Node next;
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
		
		public int getData() { return this.data; }
	}
	
	private Node front = null;
	private Node rear = null;
	private int queueSize, currentSize = 0;
	
	//init
	public QueueList(int size) {
		front = null;
		rear = null;
		this.queueSize = size;
	}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = 0;
		while(size <= 0) {			//queue ������
			System.out.print("Input queue size: ");
			size = sc.nextInt();
			
			if(size <= 0)
				System.out.println("Size should be greater than 0.");
		}
		QueueList queue = new QueueList(size);		//init
		
		boolean flag = true;
		int choice = -1;
		
		while(flag) {
			System.out.println("\n====================================================================");
			System.out.println("1. push   2. pop   3. size   4. empty   5. front   6. rear   7. quit");
			System.out.println("====================================================================");
			System.out.print("Choose the action : ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.print("Data to push : ");
				queue.push(sc.nextInt());
				break;
			case 2:
				queue.pop();
				break;
			case 3:
				System.out.println("There are " + queue.currentSize + " elements in Queue.");
				break;
			case 4:
				if(queue.isEmpty())
					System.out.println("There is no element in Queue.");
				else
					System.out.println("There are elements in Queue.");
				break;
			case 5:
				queue.front();
				break;
			case 6:
				queue.rear();
				break;
			case 7:
				flag = false;
				break;
			default:
				System.out.println("   Wrong choice. Choose from 1~7.");	
			}
		}
		
		System.out.println("\n===QUIT===");
		sc.close();
	}

	//������ ����
	public void push(int data) {
		Node newNode = new Node(data);
		
		if(isFull()) {
			System.out.println("The Queue is full.");
			return;
		} else if(isEmpty()) {		//queue�� ���������
			front = newNode;		//front�� rear �� �� newNode ����Ų��
			rear = newNode;
		} else {
			rear.next = newNode;	//���� rear�� next�� newNode�� ����Ű��
			rear = newNode;			//rear�� newNode�� ����Ų��
		}
		
		System.out.println("push " + data);
		currentSize += 1;
	}
	
	//ù��° ������ ���� (����)
	public int pop() {
		if(isEmpty()) {				//������� ���
			System.out.println("No element to pop.");
			return -1;
		}
		
		//�� ����ִٸ�
		Node delNode = this.front;	//ù ��尡 �����ǰ�
		this.front = this.front.next;	//�� ������尡 front�� �ȴ�
		
		currentSize -= 1;
		
		System.out.println("pop " + delNode.getData());
		
		return delNode.getData();
	}
	
	//queue ����ִ��� Ȯ��
	public boolean isEmpty() {
		return (front == null);
	}
	
	//queue á���� Ȯ��
	public boolean isFull() {
		if(isEmpty())				//���������
			return false;
		else						//�� ������� ��
			return(currentSize == queueSize);
	}
	
	//ù��° ������ Ȯ�� (����X)
	public int front() {
		if(isEmpty()) {				//������� ���
			System.out.println("No element in queue.");
			return -1;
		}
		
		//�� ������� ���
		System.out.println("The first element is " + front.getData());
		
		return front.getData();
	}
	
	//������ ������ Ȯ�� (����X)
	public int rear() {
		if(isEmpty()) {				//������� ���
			System.out.println("No element in queue.");
			return -1;
		}
		
		//�� ������� ���
		System.out.println("The last element is " + rear.getData());
		
		return rear.getData();
	}
}