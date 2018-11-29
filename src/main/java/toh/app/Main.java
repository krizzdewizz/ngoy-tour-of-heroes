package toh.app;

import static ngoy.core.Provider.useValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ngoy.Ngoy;
import ngoy.core.Provider;
import ngoy.router.Location;
import ngoy.router.RouterConfig;
import ngoy.router.RouterModule;
import toh.app.dashboard.DashboardComponent;
import toh.app.hero_detail.HeroDetailComponent;
import toh.app.heroes.HeroesComponent;

@Controller
@RequestMapping("/**")
public class Main implements InitializingBean {

	@Value("${spring.profiles.active:unknown}")
	private String activeProfile;

	private Ngoy<AppComponent> ngoy;

	@Autowired
	private BeanInjector beanInjector;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void afterPropertiesSet() throws Exception {
		createApp();
	}

	private void createApp() {

		RouterConfig routerConfig = RouterConfig.baseHref("/")
				.location(useValue(Location.class, () -> request.getRequestURI()))
				.route("heroes", HeroesComponent.class)
				.route("dashboard", DashboardComponent.class)
				.route("detail/:id", HeroDetailComponent.class)
				.build();

		AppState appState = new AppState();

		ngoy = Ngoy.app(AppComponent.class)
				.injectors(beanInjector)
				.modules(RouterModule.forRoot(routerConfig))
				.modules(Main.class.getPackage())
				.providers(Provider.useValue(AppState.class, appState))
				.build();
	}

	@GetMapping()
	public void home(HttpServletResponse response) throws Exception {
		if (activeProfile.contains("dev") && "a".isEmpty()) {
			createApp();
		}
		ngoy.render(response.getOutputStream());
	}
}