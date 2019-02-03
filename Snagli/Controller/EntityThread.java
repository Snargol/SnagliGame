import java.nio.channels.AsynchronousByteChannel;

import model.entities.TemporalEntity;
import model.genercis.SnagliModel;

public class EntityThread extends Thread{
	SnagliModel model;

		public EntityThread(SnagliModel model) {
			
		}
	
		@Override
		public void run() {
			super.run();
			while (true) {
				TemporalEntityTurn();
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void TemporalEntityTurn() {
			for (TemporalEntity entity : getModel().getTemporalEntities()) {
				entity.isAlive(getModel().getTimer().getTimeMilliSeconds());
			}
		}

		public SnagliModel getModel() {
			return model;
		}
		
		
}
