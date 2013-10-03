package jetbrains.sample.exception;

public class InvalideSizeTimesException extends Exception {
	public InvalideSizeTimesException(int size, long id){
		System.out.println("size time nella Run deve essere solo uno invece di "+size);
		System.out.println("id è "+id);
	}
}
