package toh.app.hero_detail;

import static java.lang.String.format;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.OnDestroy;
import ngoy.core.OnInit;
import ngoy.router.Location;
import ngoy.router.RouteParams;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-hero-detail", templateUrl = "hero-detail.component.html", styleUrls = { "hero-detail.component.css" })
@Controller
public class HeroDetailComponent implements OnInit, OnDestroy {
	public Hero hero;

	@Inject
	public RouteParams routeParams;

	@Inject
	public HeroService heroService;

	@Inject
	public Location location;

	public boolean saved;
	public String error;

	private String redirectUrl;

	@Override
	public void ngOnInit() {
		hero = heroService.findById(Long.parseLong(routeParams.get("id")))
				.orElse(null);
		redirectUrl = location.getPath();
	}

	@Override
	public void ngOnDestroy() {
		saved = false;
		error = null;
	}

	@PostMapping("/detail/savehero")
	public String saveHero(Hero heroRequest) {
		String name = heroRequest.name;
		if (name == null || name.isEmpty()) {
			error = "Name must not be empty.";
		} else {
			Optional<Hero> hero = heroService.findById(heroRequest.id);
			if (hero.isPresent()) {
				hero.get().name = name;
				saved = true;
			} else {
				error = format("hero with id %s could not be found.", heroRequest.id);
			}
		}
		return format("redirect:%s", redirectUrl);
	}
}
