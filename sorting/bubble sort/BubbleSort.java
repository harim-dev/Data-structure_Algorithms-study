package sorting;

public class BubbleSort {
	public static void main(String argc[]) {
		int arr[] = {6,3,7,9,2};
		
		sorting(arr);	//���� ���� ��ȯ
		
		for(int i=0; i<arr.length; i++)	//��� ���
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[]) {
		for(int i=0; i<arr.length; i++) {		//nȸ��
			for(int j=0; j<arr.length-i-1; j++) {	//������ ��� ��
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
}