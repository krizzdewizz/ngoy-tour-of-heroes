package toh.app.hero_detail;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnInit;
import ngoy.router.RouteParams;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-hero-detail", templateUrl = "hero-detail.component.html", styleUrls = { "hero-detail.component.css" })
public class HeroDetailComponent implements OnInit {
	public Hero hero;

	@Inject
	public RouteParams routeParams;

	@Inject
	public HeroService heroService;

	@Override
	public void ngOnInit() {
		hero = heroService.findById(Long.parseLong(routeParams.get("id")))
				.get();
	}

}
