package sorting;

public class QuickSort {
	public static void main(String argc[]) {
		int arr[] = {5,6,7,2,16,80,-20,15,8,6};
		
		sorting(arr, 0, arr.length-1);		//�迭, left, right
		
		for(int i=0; i<arr.length; i++)	//��� ���
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[], int left, int right) {
		int pivot = partition(arr, left, right);	//pivot�� �������� ������ 
		
		//���ο� pivot�� �������� divide
		if(left < pivot-1)
			sorting(arr, left, pivot-1);
		
		if(pivot < right)
			sorting(arr, pivot, right);
	}
	
	//pivot�� �������� �켱������ ���� ��, ���� ������ ������
	public static int partition(int arr[], int left, int right) {
		int pivot = arr[(left+right)/2];	//�迭�� �߰�
		
		while(left <= right) {
			while(arr[left] < pivot)	//pivot���� ū ���� ã��
				left++;
			
			while(arr[right] > pivot)	//pivot���� ���� ���� ã��
				right--;
			
			if(left <= right) {			//�������� �ʾҴٸ� �� ��ȯ
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				
				left++;
				right--;
			}
		}
		
		//pivot�� left�� �ٲ�
		return left;
	}
	
//	public static void swap(int arr[], int idx1, int idx2) {
//		int temp = arr[idx1];
//		arr[idx1] = arr[idx2];
//		arr[idx2] = temp;
//	}
}
