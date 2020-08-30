package examples;

//Linked List

public class HashChaining {
	public class Person {
		private int ssn;		//�������, key
		private String name;	//�̸�
		private String addr;	//�ּ�
		
		public Person(int ssn, String name, String addr) {
			this.ssn = ssn;
			this.name = name;
			this.addr = addr;
		}
		
		public void setSsn(int ssn) { this.ssn = ssn; }
		public int getSsn() { return this.ssn; }
		public void setName(String name) { this.name = name; }
		public String getName() { return this.name; }
		public void setAddr(String addr) { this.addr = addr; }
		public String getAddr() { return this.addr; }
	}
	
	public class Node {
		private int key;
		private Person person;
		private Node next;
		
		public Node() {				//init
			this.key = -1;
			this.person = null;
			this.next = null;
		}
		
		public Node(Person person) {	//new node			
			this.key = person.ssn;
			this.person = person;
			this.next = null;
		}
		
		public void setPerson(Person person) { this.person = person; }
		public Person getPerson() { return this.person; }
		public void setNext(Node node) { this.next = node; }
		public Node getNext() { return this.next; }
		
		//�ٷ� access �����ϵ���
		public int getKey() { return this.key; }
		public int getSSn() { return this.person.getSsn(); }
		public String getName() { return this.person.getName(); }
		public String getAddr() { return this.person.getAddr(); }
	}
	
	private final static int MAX_TBL = 32;	//�ؽ� ���̺� ũ��
	private static Node[] MyTbl;			//�ؽ� ���̺�
	
	public HashChaining() {				//init
		MyTbl = new Node[MAX_TBL];
		
		for(int i=0; i<MAX_TBL; i++)
			MyTbl[i] = new Node();
	}
	
	public static void main(String[] args) {
		HashChaining hc = new HashChaining();
		
		System.out.println("\tinsert Hong, Kim, Lee");
		hc.insert(200216, "Hong", "Korea");
		hc.insert(940202, "Kim", "China");
		hc.insert(951216, "Lee", "Korea");
		
		hc.print();
		
		System.out.println("\n\tsearch 200216");
		hc.search(200216);
		
		System.out.println("\n\tdelete 951216");
		hc.delete(951216);
		hc.print();
		
		System.out.println("\n\tsearch 951216");
		hc.delete(951216);
	}
	
	//�ؽ� �Լ�
	public int hashing(int key) {			//key������ hash(index)���� ���
		return key%100;						//���� ��¥
	}
	
	//����
	public void insert(int ssn, String name, String addr) {
		Person person = new Person(ssn, name, addr);	//Person �����
		int key = person.getSsn();					//ssn == key
		int hash = hashing(person.getSsn());		//hash ��� (���̺��� index ���� �ȴ�)
		
		if(check(key) != null) {			//key�� �ߺ��ȴٸ�
			System.out.println("Already exist.");
			return;
		}
		
		Node newNode = new Node(person);		//��� ����
		Node current = MyTbl[hash];				//hash�� �ش��ϴ� ���̺� ù ���
		
		if(current.getNext() == null)			//���̺��� ����ִ� == �� hash���� node�� ����
			MyTbl[hash].setNext(newNode);		//���̺��� ù node
		else {									//list�� ���� �տ� �߰�
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}	
	}
	
	//����
	public boolean delete(int key) {
		Node current = MyTbl[hashing(key)];		//MyTbl[hash] ù��° Node���� ����
		
		if(current.getNext() != null ) {		//�ش� hash�� ��尡 ���� ��
			while(current != null) {				//null�� �ƴ� ������ == list�� ������
				if(current.getNext() == null)		//���� ��尡 null == ������ ���, �� ã�� �ʿ� ����
					break;
				
				if(current.getNext().getKey() == key) {	//current.next == key (���� node�� ������� �ϴ� ����϶�)
					Node delNode = current.getNext();	//������� �ϴ� ���
					current.setNext(delNode.getNext());	//current.next = delNode.next (delNode�� ����� ���� ���� ���� ��带 ����)
					
					System.out.println("key: " + key + " deleted.");
					return true;
				}
				
				current = current.getNext();		//���� ����
			}
		}
			
		System.out.println("No such data.");
		return false;
	}
	
	//Ž��
	public void search(int key) {
		Node result = check(key);		//���� ���� Ȯ��
		
		if(result != null) {			//null�� �ƴϸ� ���� ���
			System.out.print("[" + key + "'s info] ");
			System.out.print("Name: " + result.getName());
			System.out.println(", Address: " + result.getAddr());
		} else
			System.out.println("No key(" + key + ")");
	}
	
	public Node check(int key) {		//���� ���� Ȯ�� ��, �ش� ��� ��ȯ
		int hash = hashing(key);
		
		Node current = MyTbl[hash].getNext();	//�ش� hash ���̺� 
		
		while(current != null ) {
			if(current.getKey() == key)
				return current;
			
			current = current.getNext();
		}
		
		return null;
	}
	
	//���
	public void print() {
		for(int i=0; i<MAX_TBL; i++) {
			if(MyTbl[i].getNext() != null) {
				System.out.print(i + "\t");
				
				Node current = MyTbl[i].getNext();
				
				while(current != null) {
					System.out.print(current.getName() + ", ");
					current = current.getNext();
				}
				System.out.println();
			}
		}
	}
}
