package toh.app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import ngoy.core.Component;
import ngoy.core.Inject;
import toh.app.services.MessageService;

@Component(selector = "app-messages", templateUrl = "messages.component.html", styleUrls = { "messages.component.css" })
@Controller
public class MessagesComponent {

	@Inject
	public MessageService messageService;

	@PostMapping("/clearmessages")
	public String clear() {
		messageService.clear();
		return "redirect:.";
	}
}
