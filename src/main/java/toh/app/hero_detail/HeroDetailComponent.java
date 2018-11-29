package toh.app.hero_detail;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.Input;
import ngoy.core.OnInit;
import ngoy.router.RouteParams;
import toh.app.AppState;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-hero-detail", templateUrl = "hero-detail.component.html", styleUrls = { "hero-detail.component.css" })
public class HeroDetailComponent implements OnInit {
	@Input
	public Hero hero;

	@Input
	public boolean readonly;

	@Inject
	public AppState appState;

	@Inject
	public RouteParams routeParams;

	@Inject
	public HeroService heroService;

	@Override
	public void ngOnInit() {
		if (hero == null) {
			long heroId = Long.parseLong(routeParams.get("id"));
			hero = heroService.findById(heroId)
					.orElse(null);
			appState.selectedHeroId = heroId;
		}
	}
}
