package toh.app.services;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ngoy.core.Injectable;

@Injectable
public class MessageService {
	public List<String> messages = new ArrayList<>();

	public void add(String message) {
		messages.add(format("%s: %s", LocalDateTime.now(), message));
	}

	public void clear() {
		messages.clear();
	}
}
