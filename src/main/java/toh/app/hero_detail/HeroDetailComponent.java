package toh.app.hero_detail;

import static java.lang.String.format;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.Input;
import ngoy.core.OnDestroy;
import ngoy.core.OnInit;
import ngoy.router.Location;
import ngoy.router.RouteParams;
import toh.app.AppState;
import toh.app.Hero;
import toh.app.services.HeroService;

@Component(selector = "app-hero-detail", templateUrl = "hero-detail.component.html", styleUrls = { "hero-detail.component.css" })
@Controller
public class HeroDetailComponent implements OnInit, OnDestroy {
	@Input
	public Hero hero;

	private boolean readonly;

	public boolean isReadonly() {
		return readonly;
	}

	@Input
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	@Inject
	public AppState appState;

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
		redirectUrl = location.getPath();

		if (hero == null) {
			long heroId = Long.parseLong(routeParams.get("id"));
			hero = heroService.findById(heroId)
					.orElse(null);
			appState.selectedHeroId = heroId;
		}
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
