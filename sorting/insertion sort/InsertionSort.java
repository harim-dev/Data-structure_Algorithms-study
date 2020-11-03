public class InsertionSort {
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5};
		
		sorting(arr);	//���� ���� ��ȯ
		
		for(int i=0; i<arr.length; i++)	//��� ���
			System.out.print(arr[i] + " ");
	}
	
	public static void sorting(int arr[]) {	//��������
		int i, j;
		
		for(i=1; i<arr.length; i++) {
			int target = arr[i];
			
			for(j=i-1; j>=0; j--) {
				if(arr[j] > target)
					arr[j+1] = arr[j];		//�� ĭ �ڷ�
				else
					break;
			}
			
			arr[j+1] = target;		//����
		}
	}
}
