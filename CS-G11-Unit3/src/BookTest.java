
public class BookTest {

	public static void main(String[] args) {
		Book book1 = new Book("Java Programming", 
				"Mr. Lin", -2000, "FAKE-ISBN-1");
		Book book2 = new Book("The Secret Life of Bees", 
				"Sun Monk", 2001, "FAKE-ISBN-2");
		
		System.out.println(book1);
		System.out.println(book2);
	}

}
