public class LinkedListQueue {

	private Node first = null

	private class Node
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public LinkedListQueue(int capacity)
	{
		s = new String[capacity]
	}

	public void enqueue(String item)
	{
		Node oldlast = last;

		last = new Node();
		last.item = item;
		last.next = null;

		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = newlast;
		}
	}

	public void dequeue()
	{
		String item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		return item;
	}
}
