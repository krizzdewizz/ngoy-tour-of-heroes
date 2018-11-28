package toh.app.dashboard;

import java.util.List;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnInit;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-dashboard", templateUrl = "dashboard.component.html", styleUrls = { "dashboard.component.css" })
public class DashboardComponent implements OnInit {

	public List<Hero> heroes;

	@Inject
	public HeroService heroService;

	@Override
	public void ngOnInit() {
		heroes = heroService.getHeroes()
				.subList(1, 5);
	}

}
