package classTest;

import java.util.Stack;

public class StackClass {
	public static void main(String args[]) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=1; i<=5; i++)
			stack.push(i);			//1~5 ���ʴ�� push
		
		System.out.println("stack: " + stack);
		
		System.out.println("peek: " + stack.peek());	//���� ���� �ִ� ������ ��ȸ(����X)
		System.out.println("stack: " + stack);			//5�� �״�� �����ִ�.
		
		System.out.println("pop: " + stack.pop());	//���� ���� �ִ� ������ ����
		System.out.println("stack: " + stack);			//5�� �����Ǿ���
		
		System.out.println("search 5: " + stack.search(5));	//������ 5
		System.out.println("search 2: " + stack.search(2));
		
		System.out.println("empty: " + stack.empty());	//����� boolean���� ��ȯ
		
		stack.clear();		//���� ����
		System.out.println("empty: " + stack.empty());	//����� boolean���� ��ȯ
		
		System.out.println("stack: " + stack);
	}
}
