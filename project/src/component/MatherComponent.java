package component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import JDBC.QNS.GroupTable.staffQns_T;

public class MatherComponent extends Box{

        protected staffQns_T DIO_Staff = new staffQns_T();
        protected staffQns_T DIO_Student = new staffQns_T();

        public MatherComponent() {
                super(BoxLayout.Y_AXIS);
        }
        
}
