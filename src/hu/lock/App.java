package hu.lock;

import hu.lock.controller.LockFacade;
import hu.lock.model.service.Console;
import hu.lock.model.service.DataApi;
import hu.lock.model.service.ResultWriter;

public class App {

	private final LockFacade lock;
	private final Console console;
	private final ResultWriter resultWriter;
	
	public App() {
		console = new Console();
		DataApi data = new DataApi();
		lock = new LockFacade(data.getKeys("door.txt"));
		resultWriter = new ResultWriter("success.txt");
	}

	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		String masterKey = console.read("Exercise 2: Give the code that opens the lock: ");
		System.out.println("Exercise 3: The opening code lines are: " + lock.getEqualKeyIds(masterKey));
		System.out.println("Exercise 4: The number of the first attempt containing a repeating digit is: " + lock.getFirstRepeatingLockId());
		System.out.println("Exercise 5: " + lock.getRandomKey(masterKey));
		resultWriter.printAll(lock.getOpenResults(masterKey));
	}

}
