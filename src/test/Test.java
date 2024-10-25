package test;

import controller.Control;
import model.entity.Pacman;
import view.IoManager;

public class Test {
	IoManager io;
	Control mt;
	Pacman pc;

	public Test() {
		io = new IoManager();
		mt = new Control();
		pc = new Pacman();
	}

	public void init()  {
//		io.showMessage(""+mt.showboard());
//		io.showMessage(""+pc.mostrarTablero());
//		mt.importTablero();
//		io.showMessage(""+pc.mostrarTablero());
//		io.showMessage(""+mt.showboard());
		
	}
}