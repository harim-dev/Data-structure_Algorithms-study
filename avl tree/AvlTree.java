package examples;

import java.util.Scanner;

public class AvlTree {
	public class Node {			//Node��� ����ü
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.setData(data);
			setLeft(null);
			setRight(null);
		}
		
		public void setData(int data) {
			this.data = data;
		}
		
		public int getData() {
			return data;
		}
		
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}
		
		public Node getRight() {
			return right;
		}
	}
	
	public Node root;			//��Ʈ node
	public AvlTree() {			//init, root �ʱ�ȭ
		root = null;
	}
	
	public void Menu() {
		System.out.println("\n=======================================================================");
		System.out.print("1. INSERT\t");
		System.out.print("2. DELETE\t");
		System.out.print("3. SEARCH\t");
		System.out.print("4. PRINT TREE\t");
		System.out.println("5. QUIT");
		System.out.println("=======================================================================");
		System.out.print("Choose the action : ");
	}
	
	public static void main(String[] args) {
		AvlTree avl = new AvlTree();
		Scanner sc = new Scanner(System.in);
		
		int data = 0;
		boolean result = false;
		boolean flag = true;
		
		while(flag) {		//�ݺ�
			avl.Menu();		//�޴� ���
			int choice = sc.nextInt();
			
			switch(choice) {	//���ÿ� ���� �Լ� ȣ��
			case 1:			//����
				System.out.println("Data that already exists will not be inserted.");
				System.out.print("Data to insert(int) : ");
				data = sc.nextInt();	//������ ������
				
				avl.root = avl.insert(avl.root, data);
				
				break;
			case 2:			//����
				System.out.print("Data to delete(int) : ");
				data = sc.nextInt();	//������ ������
				
				result = avl.delete(data);	//���� ���� �� ���
				if(result)				//result == true
					System.out.println("   " + data + " has deleted.");
				else					//result == false
					System.out.println("   No such data.");
				
				break;
			case 3:			//Ž��
				System.out.print("Data to search(int) : ");
				data = sc.nextInt();	//Ž���� ������
				
				result = avl.search(data);	//Ž�� ���� �� ���
				if(result)				//result == true
					System.out.println("   " + data + " is in the tree.");
				else					//result == false
					System.out.println("   No such data.");
				
				break;
			case 4:			//���
				System.out.println("1. inorder\t2. preorder\t3.postorder");
				System.out.print("Choose the way to print : ");
				int choice2 = sc.nextInt();	//��� ��� ����
				
				if(choice2 == 1)		//inorder
					avl.inorderTraverse(avl.root);
				else if(choice2 == 2)	//preorder
					avl.preorderTraverse(avl.root);
				else if(choice2 == 3)	//postorder
					avl.postorderTraverse(avl.root);
				else					//1,2,3 �̿��� ����
					System.out.println("   Wrong choice. Choose from 1~3.");
				
				System.out.println();
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
	public Node insert(Node node, int data) {
		if(node == null) {		//����ִٸ�(���� ���� �� ������) ���� ����
			Node newNode = new Node(data);
			node = newNode;
		} else if(data < node.getData()) {		//���� ��庸�� ���� ���̸�
			node.setLeft(insert(node.getLeft(), data));
			node = rebalance(node);
		} else if(data > node.getData()) {		//���� ��庸�� ū ���̸�
			node.setRight(insert(node.getRight(), data));
			node = rebalance(node);
		}

		//�̹� �ִ� �������� ��� �ƹ� �۾��� �Ͼ�� �ʴ´�.
		return node;
	}
	
	
	//����
	public boolean delete(int target) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = true;
		
		while(current.getData() != target) {	//������� Ž��
			parent = current;
			
			if(target < current.getData()) {
				isLeftChild = true;
				current = current.getLeft();
			} else {
				isLeftChild = false;
				current = current.getRight();
			}
			
			if(current == null)		//target�� �������� �ʴ´ٸ�
				return false;
		}
		
		Node delete = current;	//delete�� ��� node
		
		//case1 : ��������� �ڽĳ�尡 ���� ��
		if(delete.getLeft() == null && delete.getRight() == null) {
			if(delete == root)
				root = null;
			else if(isLeftChild)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		
		//case2 : ��������� �ڽĳ�尡 �ϳ��� ��
		//case2-1 : ��������� ���� �ڽĳ�常 �ִ� ���
		else if(delete.getRight() == null) {
			if(delete == root)
				root = delete.getLeft();
			else if(isLeftChild)
				parent.setLeft(delete.getLeft());
			else
				parent.setRight(delete.getLeft());
		}
		//case2-2 : ��������� ������ �ڽĳ�常 �ִ� ���
		else if(delete.getLeft() == null) {
			if(delete == root)
				root = delete.getRight();
			else if(isLeftChild)
				parent.setLeft(delete.getRight());
			else
				parent.setRight(delete.getRight());
		}
				
		//case3 : �ڽĳ�尡 �ΰ��� �� (������ ����Ʈ���� �ּҰ����� ��ü)
//		else if(current.getLeft() != null && current.getRight() != null){
//			//step1 : ���� ����� ��ü�� ��� ã��
//			Node replacement = getRightSubMinNode(delete);
//			Node repParent = delete;
//			
//			//step2 : ��ü�� ��忡 ����� ���� ������ ��忡 ����
//			//��带 �������� �ʰ� ���� �����ϴ� ������ �ܸ���尡 �ƴ� �������ڽĳ�尡 ���� ��츦 ���
//			int delData = delete.getData();		//������� ������ ���
//			delete.setData(replacement.getData());	//������� ��忡 ��ü��� �� ����
//			
//			//step3 : ��ü�� ����� �θ�� �ڽ��� ����
//			if(repParent.getLeft() == replacement)	//��ü��尡 �θ��� ���ʼ���Ʈ���� ��
//				repParent.setLeft(replacement.getRight());
//			else			//��ü��尡 �θ��� �����ʼ���Ʈ���� ��
//				repParent.setRight(replacement.getRight());
//		}
		
		else if(delete.getLeft() != null && delete.getRight() != null) {
			Node replacement = getRightSubMinNode(delete);
			
			if(delete == root) 
				root = replacement;
			else if(isLeftChild)		//��������� �θ��� �����̾����� ��ü��带 ���ʿ� ����
				parent.setLeft(replacement);
			else						//��������� �θ��� �������̾����� ��ü��带 �����ʿ� ����
				parent.setRight(replacement);
			
			replacement.setLeft(delete.getLeft());	//��������� ���ʼ���Ʈ���� ��ü��忡 ����
		}
		
		root = rebalance(root);			//���� �� ���뷱��
		
		return true;
	}
	
	public Node getRightSubMinNode(Node deleteNode) {
		Node replacement = null;
		Node repParent = null;
		Node current = deleteNode.getRight();
		
		while(current != null) {			//���� ����Ʈ���� ������ ��尡 ���� ���� ��
			repParent = replacement;
			replacement = current;
			current = current.getLeft();	//���� ���� ����Ʈ���� ��������
		}
			
		if(replacement != deleteNode.getRight()) {	//replacement�� ���ʼ���Ʈ�����ٸ�
			repParent.setLeft(replacement.getRight());		//��ü����� �����ʼ���Ʈ���� �θ� ����
			replacement.setRight(deleteNode.getRight());	//��������� �����ʼ���Ʈ���� ��ü��忡 ����
		}
		
		return replacement;
	}
	
	//Ž��
	public boolean search(int target) {
		Node current = root;
		
		while(current != null) {
			if(target == current.getData())		//ã��
				return true;
			else if(target < current.getData())	//target�� current���� ������ ����
				current = current.getLeft();
			else					//target�� current���� ũ�� ������
				current = current.getRight();
		}
		
		return false;
	}
	
	
	//���뷱��
	public Node rebalance(Node root) {
		//�����μ��� ����� ������ �� ���, ������ �������� �� ���
		int balFac = getHeightDiff(root);		//balance factor (�����μ�)
		
		if(balFac > 1) {			//�����μ��� ������ LL�̳� LR����
			//case1 : LL
			if(getHeightDiff(root.getLeft()) > 0)	//root���� node �������� �������� �� ��� LL����
				root = rotateLL(root);
			//case2 : LR
			else									//���������� �� ��� LR����
				root = rotateLR(root);
		} else if(balFac < -1) {	//�����μ��� ������� RR�̳� RL����
			//case3 : RR
			if(getHeightDiff(root.getRight()) < 0)	//root���� node �������� �������� �� ��� RR����
				root = rotateRR(root);
			//case4 : RL
			else									//�������� �� ��� RL����
				root = rotateRL(root);
		}
		
		return root;		//���ο� root ��ȯ
	}
	
	public int getHeightDiff(Node node) {	//���ʰ� ������ ����Ʈ���� �� ���ϴ� �Լ�
		if(node == null)
			return 0;
		
		int leftHeight = getHeight(node.getLeft());		//���ʼ���Ʈ�� ���� ���
		int rightHeight = getHeight(node.getRight());	//�����ʼ���Ʈ�� ���� ���
		
		return leftHeight - rightHeight;	//������ �� ������ +, �������� �� ������ -
	}
	
	public int getHeight(Node node) {		//Ʈ���� �ִ� ���� ���ϴ� �Լ�
		if(node == null)
			return 0;
		
		int leftHeight = getHeight(node.getLeft());		//���ʼ���Ʈ�� ���� ���
		int rightHeight = getHeight(node.getRight());	//�����ʼ���Ʈ�� ���� ���
		
		if(leftHeight > rightHeight)
			return (leftHeight + 1);
		else
			return (rightHeight + 1);
	}
	
	public Node rotateLL(Node node) {
		Node parent = node;
		Node child = node.getLeft();		//�ش� ����� ���ʼ���Ʈ��
		
		//LL ȸ��
		parent.setLeft(child.getRight());	//parent�� ���ʿ� child�� �������� ����
		child.setRight(parent);				//child�� �����ʿ� parent�� ����
		
		return child;		//���ο� root�� ��ȯ
	}
	
	public Node rotateRR(Node node) {
		Node parent = node;
		Node child = node.getRight();
		
		//RR ȸ��
		parent.setRight(child.getLeft());	//parent�� �����ʿ� child�� ������ ����
		child.setLeft(parent);				//child�� ���ʿ� parent�� ����
		
		return child;		//���ο� root�� ��ȯ
	}
	
	public Node rotateLR(Node node) {		//�κ��� RRȸ�� ��, LLȸ��
		Node parent = node;
		Node child = parent.getLeft();
		
		parent.setLeft(rotateRR(child));	//���� child�� RRȸ���ϰ�, parent�� ���ʿ� ����
		return rotateLL(parent);			//child�� LLȸ���ϰ�, ���ο� root�� ��ȯ
	}
	
	public Node rotateRL(Node node) {		//�κ��� LLȸ�� ��, RRȸ��
		Node parent = node;
		Node child = parent.getRight();
		
		parent.setRight(rotateLL(child));	//���� child�� LLȸ���ϰ�, parent�� �����ʿ� ����
		return rotateRR(parent);			//child�� RRȸ���ϰ�, ���ο� root�� ��ȯ
	}
	
	
	//���
	public void inorderTraverse(Node node) {	//������������ ��� ����
		if(node == null)
			return;
		
		inorderTraverse(node.getLeft());
		System.out.print(node.getData() + " ");
		inorderTraverse(node.getRight());
	}
	
	public void preorderTraverse(Node node) {
		if(node == null)
			return;
		
		System.out.print(node.getData() + " ");
		inorderTraverse(node.getLeft());
		inorderTraverse(node.getRight());
	}
	
	public void postorderTraverse(Node node) {
		if(node == null)
			return;
		
		inorderTraverse(node.getLeft());
		inorderTraverse(node.getRight());
		System.out.print(node.getData() + " ");
	}
}
