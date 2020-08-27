package examples;

//����Ʈ�� ����

public class BinarySearchTree {
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
	public BinarySearchTree() {	//init, root �ʱ�ȭ
		this.root = null;
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		
		bst.insert(5);
		bst.insert(8);
		bst.insert(1);
		bst.insert(6);
		bst.insert(4);
		bst.insert(9);
		bst.insert(3);
		bst.insert(2);
		bst.insert(7);
		
		System.out.print("���� ��� : ");
		bst.inorderTraverse(bst.root);
		System.out.println();
		
		bst.delete(3);
		System.out.print("3���� ��� : ");
		bst.inorderTraverse(bst.root);
		System.out.println();
		
		bst.delete(8);
		System.out.print("8���� ��� : " );
		bst.inorderTraverse(bst.root);
		System.out.println();
		
		bst.delete(1);
		System.out.print("1���� ��� : ");
		bst.inorderTraverse(bst.root);
		System.out.println();
		
		System.out.println("6Ž�� ��� : " + bst.search(6));
		
		bst.delete(6);
		System.out.print("6���� ��� : ");
		bst.inorderTraverse(bst.root);
		System.out.println();
		
		System.out.println("6Ž�� ��� : " + bst.search(6));
	}
	
	//����
	public void insert(int data) {
		Node newNode = new Node(data);	//insert�ϰ��� �ϴ� node
		
		if(root == null) {			//ù ����� ��Ʈ�� ����
			root = newNode;
			return;
		}
		
		Node parent = null;
		Node current = root;		//root���� ��ġ Ž���ϱ� ������
		
		//insert�� ��ġ Ž��
		while(true) {
			if(data == current.getData())		//�ߺ� ������� �ʴ´�
				return;
			
			parent = current;		//���� ���� �̵�
			
			if(data < current.getData()) {		//current���� ���� ���̸�
				current = current.getLeft();	//���ʼ���Ʈ���� �̵�
				
				if(current == null) {			//���ʼ���Ʈ���� null�̸� ����
					parent.setLeft(newNode);
					return;
				}
			} else if(data > current.getData()) {	//current���� ū ���̸�
				current = current.getRight();		//�����ʼ���Ʈ���� �̵�
				
				if(current == null) {				//�����ʼ���Ʈ���� null�̸� ����
					parent.setRight(newNode);
					return;
				}
			}
		}
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
		
		// �Ǵٸ� ������� case3����
//		else if(current.getLeft() != null && current.getRight() != null){
//		//step1 : ���� ����� ��ü�� ��� ã��
//		Node replacement = getRightSubMinNode(delete);
//		Node repParent = delete;
//		
//		//step2 : ��ü�� ��忡 ����� ���� ������ ��忡 ����
//		//��带 �������� �ʰ� ���� �����ϴ� ������ �ܸ���尡 �ƴ� �������ڽĳ�尡 ���� ��츦 ���
//		int delData = delete.getData();		//������� ������ ���
//		delete.setData(replacement.getData());	//������� ��忡 ��ü��� �� ����
//		
//		//step3 : ��ü�� ����� �θ�� �ڽ��� ����
//		if(repParent.getLeft() == replacement)	//��ü��尡 �θ��� ���ʼ���Ʈ���� ��
//			repParent.setLeft(replacement.getRight());
//		else			//��ü��尡 �θ��� �����ʼ���Ʈ���� ��
//			repParent.setRight(replacement.getRight());
//		}
		
		return true;
	}
	
	public Node getRightSubMinNode(Node deleteNode) {
		Node replacement = null;
		Node repParent = null;
		Node current = deleteNode.getRight();
		
		while(current != null) {		//���� ����Ʈ���� ������ ��尡 ���� ���� ��
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
	
	public void inorderTraverse(Node root) {		//������������ ��� ����
		if(root == null)
			return;
		
		inorderTraverse(root.getLeft());
		System.out.print(root.getData() + " ");
		inorderTraverse(root.getRight());
	}
	
	public void preorderTraverse(Node root) {
		if(root == null)
			return;
		
		System.out.print(root.getData() + " ");
		inorderTraverse(root.getLeft());
		inorderTraverse(root.getRight());
	}
	
	public void postorderTraverse(Node root) {
		if(root == null)
			return;
		
		inorderTraverse(root.getLeft());
		inorderTraverse(root.getRight());
		System.out.print(root.getData() + " ");
	}
}
