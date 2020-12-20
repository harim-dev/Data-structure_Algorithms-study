package sorting;

public class MergeSort {
	public static final int ArrNum = 6;
	
	public static void main(String argc[]) {
		int arr[] = {8, 2, 6, 9, 5, 11};
		
		sorting(arr, 0, arr.length-1);	//���� ���� ��ȯ
		
		for(int i=0; i<arr.length; i++)	//��� ���
			System.out.print(arr[i] + " ");
	}
	
	//����
	public static void sorting(int arr[], int left, int right) {
		int mid = -1;		//�߰� ����
		
		if(left < right) {
			mid = (left + right) / 2;	//�߰� ���� �谣
			
			sorting(arr, left, mid);	//�� ������ ���� ����
			sorting(arr, mid+1, right);	//�� ������ ���� ����
			
			merge(arr, left, mid, right);	//���İ� ����
		}
		
		//else : data�� �ϳ��� ���  
	}
	
	//���� �� ���� 
	public static void merge(int arr[], int left, int mid, int right) {
		int fIdx = left;	//�� ������ ���� ��ġ
		int bIdx = mid + 1;	//�� ������ ���� ��ġ
		int curIdx = left;
		
		int sorted[] = new int[ArrNum];		//�ӽù迭
		
		//1������
		while(fIdx <= mid && bIdx <= right) {
			if(arr[fIdx] <= arr[bIdx])
				sorted[curIdx] = arr[fIdx++];
			else
				sorted[curIdx] = arr[bIdx++];
			
			curIdx++;
		}
		
		//2������ : ���� ������ ����
		if(fIdx > mid) {	//�� ������ ������ ��
			for(int i=bIdx; i<=right; i++, curIdx++)
				sorted[curIdx] = arr[i];
		} else {			//�� ������ ������ ��
			for(int i=fIdx; i<=mid; i++, curIdx++)
				sorted[curIdx] = arr[i];
		}
		
		//���� �迭�� ����
		for(int i=left; i<=right; i++)
			arr[i] = sorted[i];
	}
}
