package cdi.example.datastore;

public interface DataStore {
	
	public Boolean getValue(String word);
	
	public void putValue(String word, boolean palindrome);
}
