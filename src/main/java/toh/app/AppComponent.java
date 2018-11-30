package toh.app;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.NgModule;
import ngoy.core.OnDestroy;
import ngoy.core.OnInit;
import ngoy.forms.FormsModule;
import ngoy.router.Location;
import ngoy.router.Route;
import ngoy.router.Router;
import toh.app.services.HeroService;
import toh.app.services.MessageService;

@Component(selector = "", templateUrl = "app.component.html", styleUrls = { "app.component.css" })
@NgModule(imports = { FormsModule.class })
@Controller
public class AppComponent implements OnInit, OnDestroy {

	public final String title = "Tour of Heroes";
	public List<Route> mainRoutes;
	public boolean saved;
	public String error;

	private String redirectUrl;

	@Inject
	public Router router;

	@Inject
	public Location location;

	@Inject
	public AppState appState;

	@Inject
	public HeroService heroService;

	@Inject
	public MessageService messageService;

	@Override
	public void ngOnInit() {
		redirectUrl = format("redirect:%s", location.getPath());
		mainRoutes = router.getRoutes()
				.subList(0, 2);
	}

	@Override
	public void ngOnDestroy() {
		saved = false;
		error = null;
	}

	@PostMapping("/savehero")
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
		return redirectUrl;
	}

	// original toh stage 1: select hero when clicked
	@PostMapping("/selecthero")
	public String selectHero(@RequestParam("heroId") long heroId) {
		appState.selectedHeroId = heroId;
		return redirectUrl;
	}

	@PostMapping("/clearmessages")
	public String clear() {
		messageService.clear();
		return redirectUrl;
	}
}
