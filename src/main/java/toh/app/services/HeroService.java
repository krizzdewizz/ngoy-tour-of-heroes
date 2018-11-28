package toh.app.services;

import static toh.app.MockHeroes.HEROES;

import java.util.List;
import java.util.Optional;

import ngoy.core.Inject;
import ngoy.core.Injectable;
import toh.app.Hero;

@Injectable
public class HeroService {

	@Inject
	public MessageService messageService;

	public List<Hero> getHeroes() {
		messageService.add("HeroService: fetched heroes");
		return HEROES;
	}

	public Optional<Hero> findById(long id) {
		return getHeroes().stream()
				.filter(hero -> hero.id == id)
				.findFirst();
	}
}
