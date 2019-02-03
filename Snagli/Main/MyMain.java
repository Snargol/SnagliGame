

import model.genercis.SnagliModel;
import view.SnagliView;

public class MyMain {

	public static void main(String[] args) {
		String string = "                                                                                            ";
		System.out.println(string.length());
		SnagliModel model = new SnagliModel();
		SnagliView view = new SnagliView(model, model);
		SnagliController controller = new SnagliController(view, model);

		controller.load();

	}
}

