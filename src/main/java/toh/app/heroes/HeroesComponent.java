package toh.app.heroes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnInit;
import toh.app.AppState;
import toh.app.Hero;
import toh.app.services.HeroService;
import toh.app.services.MessageService;

@Component(selector = "app-heroes", templateUrl = "heroes.component.html", styleUrls = { "heroes.component.css" })
@Controller
public class HeroesComponent implements OnInit {
	public List<Hero> heroes;

	@Inject
	public HeroService heroService;

	@Inject
	public AppState appState;

	@Inject
	public MessageService messageService;

	public Hero selectedHero;

	// original toh stage 1: select hero when clicked
	@PostMapping("/selecthero")
	public String selectHero(@RequestParam("heroId") long heroId) {
		appState.selectedHeroId = heroId;
		return "redirect:.";
	}

	@Override
	public void ngOnInit() {
		heroes = heroService.getHeroes();
		selectedHero = appState.selectedHeroId != null ? heroService.findById(appState.selectedHeroId)
				.orElse(null) : null;
	}
}
