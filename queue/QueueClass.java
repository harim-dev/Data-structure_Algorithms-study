package classTest;

import java.util.LinkedList;
import java.util.Queue;

public class QueueClass {
	public static void main(String args[]) {
		Queue<Integer> queue = new LinkedList<Integer>();

		for(int i=1; i<=4; i++)
			queue.add(i);			//1~4 ���ʴ�� �߰�
		
		System.out.println("queue: " + queue);
		
		System.out.println("offer 5: " + queue.offer(5));
		System.out.println("queue: " + queue);
		
		System.out.println("element: " + queue.element());	//���� �տ� �ִ� ������ ��ȯ
		System.out.println("peek: " + queue.peek());	//���� �տ� �ִ� ������ ��ȯ
		System.out.println("queue: " + queue);			//1�� ���� �����ִ�
		
		System.out.println("remove: " + queue.remove());	//���� �տ� �ִ� ������ ����, 1
		System.out.println("pop: " + queue.poll());		//���� �տ� �ִ� ������ ����, 2
		System.out.println("queue: " + queue);			//1�� 2�� �����Ǿ���
	}
}
