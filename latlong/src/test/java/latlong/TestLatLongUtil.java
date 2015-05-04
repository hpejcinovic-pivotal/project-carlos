package latlong;

import static org.junit.Assert.assertTrue;
import latlong.carlos.util.LatLong;
import latlong.carlos.util.LatLongUtil;

import org.junit.Test;

public class TestLatLongUtil {

	@Test
	public void testLongLatValidation() {

		assertTrue(null == LatLongUtil.calcCellId(new LatLong(41.477182779, -74.913585)));
		assertTrue(null == LatLongUtil.calcCellId(new LatLong(42, -74.913585)));
	}
	

	@Test
	public void testLongLatCallcuation(){
		assertTrue(0 == LatLongUtil.calcCellId(new LatLong(41.474937, -74.913585)).getCellHeight());
		assertTrue(0 == LatLongUtil.calcCellId(new LatLong(41.474937, -74.913585)).getCellWidth());

		assertTrue(161 == LatLongUtil.calcCellId(new LatLong(40.752827, -74.913585)).getCellHeight());
		assertTrue(157 == LatLongUtil.calcCellId(new LatLong(40.752827, -73.973145)).getCellWidth());
	}
	
	
	
	
	public static void main(String[] args) {
		LatLongUtil.printCellId(LatLongUtil.calcCellId(new LatLong(41.474937, -74.913585)));
		LatLongUtil.printCellId(LatLongUtil.calcCellId(new LatLong(40.752827, -73.973145)));
		LatLongUtil.printCellId(LatLongUtil.calcCellId(new LatLong(40.720947, -74.004173)));
		LatLongUtil.printCellId(LatLongUtil.calcCellId(new LatLong(40.781475, -73.981544)));
		LatLongUtil.printCellId(LatLongUtil.calcCellId(new LatLong(40.751266, -73.993973)));
	}

}
