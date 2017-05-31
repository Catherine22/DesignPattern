package com.catherine.iterator;

public class Sequence implements Container {
	private String[] weekdays = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

	@Override
	public Iterator getIterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator {
		int head;

		@Override
		public boolean hasNext() {
			return head < weekdays.length;
		}

		@Override
		public Object next() {
			return hasNext() ? weekdays[head++] : null;
		}

	}
}
