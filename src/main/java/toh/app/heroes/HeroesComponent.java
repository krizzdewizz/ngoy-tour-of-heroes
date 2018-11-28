package toh.app.heroes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnInit;
import toh.app.Hero;
import toh.app.services.HeroService;
import toh.app.services.MessageService;

@Component(selector = "app-heroes", templateUrl = "heroes.component.html", styleUrls = { "heroes.component.css" })
@Controller
public class HeroesComponent implements OnInit {
	public List<Hero> heroes;

	public Hero selectedHero;

	@Inject
	public HeroService heroService;

	@Inject
	public MessageService messageService;

	// original toh stage 1: select hero when clicked
	@PostMapping("/selecthero")
	public String selectHero(@RequestParam("heroIndex") int heroIndex) {
		selectedHero = heroes.get(heroIndex);
		return "redirect:.";
	}

	@Override
	public void ngOnInit() {
		heroes = heroService.getHeroes();
		if (selectedHero == null) {
			selectedHero = heroes.get(0);
		}
	}
}
