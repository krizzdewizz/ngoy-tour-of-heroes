package toh.app.heroes;

import java.util.List;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnInit;
import toh.app.AppState;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-heroes", templateUrl = "heroes.component.html", styleUrls = { "heroes.component.css" })
public class HeroesComponent implements OnInit {
	public List<Hero> heroes;

	@Inject
	public HeroService heroService;

	public Hero selectedHero;

	@Inject
	public AppState appState;

	@Override
	public void onInit() {
		heroes = heroService.getHeroes();
		selectedHero = appState.selectedHeroId != null ? heroService.findById(appState.selectedHeroId)
				.orElse(null) : null;
	}
}
