package toh.app.messages;

import ngoy.core.Component;
import ngoy.core.Inject;
import toh.app.services.MessageService;

@Component(selector = "app-messages", templateUrl = "messages.component.html", styleUrls = { "messages.component.css" })
public class MessagesComponent {

	@Inject
	public MessageService messageService;

	public boolean isMessages() {
		return !messageService.messages.isEmpty();
	}
}
