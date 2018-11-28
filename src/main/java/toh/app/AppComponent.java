package toh.app;

import java.util.List;

import ngoy.core.Component;
import ngoy.core.Inject;
import ngoy.core.NgModule;
import ngoy.core.OnInit;
import ngoy.forms.FormsModule;
import ngoy.router.Route;
import ngoy.router.Router;

@Component(selector = "", templateUrl = "app.component.html", styleUrls = { "app.component.css" })
@NgModule(imports = { FormsModule.class })
public class AppComponent implements OnInit {
	public final String title = "Tour of Heroes";

	@Inject
	public Router router;

	public List<Route> mainRoutes;

	@Override
	public void ngOnInit() {
		mainRoutes = router.getRoutes()
				.subList(0, 2);
	}
}
