package sophia.hibernate.model;

public class Book {

	private String bookNm;
	private String bookPrice;

	public String getBookNm() {
		return bookNm;
	}

	public void setBookNm(String bookNm) {
		this.bookNm = bookNm;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "Book [bookNm=" + bookNm + ", bookPrice=" + bookPrice + "]";
	}

}
