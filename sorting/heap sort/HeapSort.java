package sorting;

import java.util.Scanner;

public class HeapSort {
	private static int numData;
	
	static int[] heapArr;		//index 0�� ��� �� ��
	
	public static boolean priority(int fisrt, int second) {
		//Max Heap, ��������
		if(fisrt > second)
			return true;
		else
			return false;
		
		//Min Heap, ��������
//		if(fisrt < second)
//			return true;
//		else
//			return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input heap size: ");
		int HEAP_LEN = sc.nextInt();
		heapArr = new int[HEAP_LEN+1];
		
		init();
		
		System.out.println("insert n / delete / size / quit\t");
		
		boolean flag = true;
		
		while(flag) {
			switch(sc.next()) {
			case "insert":
				insert(sc.nextInt(), HEAP_LEN);
				break;
			case "delete":
				delete();
				break;
			case "size":
				if(isEmpty())
					System.out.println("   There is no element in Heap.");
				else
					System.out.println("   There are " + numData + "elements in Heap.");
				break;
			case "quit":
				flag = false;
				break;
			default:
				System.out.println("   Wrong command");
			}
			
			if(!isEmpty()) {
				System.out.print("   RESULT : ");
				print(heapArr, HEAP_LEN);
			}
			else
				System.out.println("   No data to print out.");
		}
		
		System.out.println("***QUIT");
		sc.close();
	}

	public static void insert(int newData, int HEAP_LEN) {
		if(numData == HEAP_LEN) {
			System.out.println("   Heap is already full.");
			return;
		}
		
		int idx = numData + 1;			//������ ������ ����
		heapArr[idx] = newData;			//������ ��忡 ������ �ӽ� ����
		
		//�ӽ÷� �������� �߰��� ����� �´� ��ġ Ž��
		while(idx != 1) {				//��Ʈ���� �ö󰡸� ��ġ Ž��
			//��������(��������)
			if(priority(heapArr[idx], heapArr[GetParentIdx(idx)])) {	//�θ𺸴� ���� ũ��(������) �θ�� �ٲ�
				int temp = heapArr[idx];
				heapArr[idx] = heapArr[GetParentIdx(idx)];
				heapArr[GetParentIdx(idx)] = temp;
				
				idx = GetParentIdx(idx);
			}
			else			//�θ𺸴� ������(ũ��) �´� ��ġ
				break;
		}
		
		numData += 1;
	}
	
	public static int delete() {
		if(isEmpty()) {
			System.out.println("No data in the Heap.");
			return -1;
		}
		
		int parentIdx = 1;
		int childIdx = GetHigherIdx(1);
		
		int delData = heapArr[1];			//��Ʈ �� �ӽ�����
		heapArr[1] = heapArr[numData];	//��Ʈ ���� == ��Ʈ�� ������ ��� ����
		heapArr[numData] = 0;			//������ ��� �ʱ�ȭ
		
		//�ӽ÷� ��Ʈ�� ��� �� ������ ����� �´� ��ġ Ž��
		while(heapArr[GetHigherIdx(parentIdx)] != 0) {		//������ ������ �������� ��ġ Ž��
			childIdx = GetHigherIdx(parentIdx);
			
			//��������(��������)
			if(!priority(heapArr[parentIdx], heapArr[childIdx])) {			//�ڽĺ��� ���� ������(ũ��) �ڽİ� �ٲ�
				int temp = heapArr[parentIdx];
				heapArr[parentIdx] = heapArr[childIdx];
				heapArr[childIdx] = temp;

				parentIdx = childIdx;
			}
			else			//�ڽĺ��� ���� ũ��(������) �´� ��ġ
				break;
		}
		
		numData -= 1;
		
		return delData;			//������ ��Ʈ�� ��ȯ
	}

	
	public static void init() {		//�� �ʱ�ȭ
		numData = 0;
	}
	
	public static boolean isEmpty() {		//����ִ��� Ȯ��
		if(numData == 0)
			return true;
		else
			return false;
	}
	
	public static int GetParentIdx(int idx) {		//�θ���
		return idx/2;
	}
	
	public static int GetLeftIdx(int idx) {			//����
		return idx*2;
	}
	
	public static int GetRightIdx(int idx) {		//������
		return idx*2+1;
	}
	
	public static int GetHigherIdx(int idx) {	//���� �켱������ �ڽ� index ��ȯ
		if(GetLeftIdx(idx) > numData)			//���� �ڽ��� ���� == �ڽ� ��尡 ���� ��
			return 0;
		else if(GetLeftIdx(idx) == numData)		//���� �ڽ��� ������ ���� == ������ ����� ��
			return GetLeftIdx(idx);
		else {									//�ڽ� ��尡 �ΰ� �� ���� ��, �� �� �켱���� ���� index ��ȯ
			if(priority(heapArr[GetRightIdx(idx)], heapArr[GetLeftIdx(idx)]))	
				return GetRightIdx(idx);
			else
				return GetLeftIdx(idx);
		}
	}
	
	public static void print(int[] heapArr, int HEAP_LEN) {
		int data = -1;
		
		for(int i=1; i<HEAP_LEN+1; i++) {
			data = heapArr[i];
			if(data != 0)
				System.out.print(heapArr[i] + " ");
		}
		
		System.out.println();
	}
}
