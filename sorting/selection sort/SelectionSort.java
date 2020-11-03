package sorting;

public class SelectionSort {
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5};
		
		sorting(arr);	//tjsxor ���� ��ȯ
		
		for(int i=0; i<arr.length; i++)	//��� ���
			System.out.print(arr[i] + " ");
	}

	private static void sorting(int[] arr) {
		int minIdx;
		
		for(int i=0; i<arr.length-1; i++) {	//������ ��Ҵ� �ڿ������� ���ĵ� ����
			minIdx = i;
			
			for(int j=i+1; j<arr.length; j++) {	//�ּڰ� ã��
				if(arr[j] < arr[minIdx])
					minIdx = j;
			}
			
			int temp = arr[i];		//�ּڰ� ���� �������� �̵�
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}
	}
}
