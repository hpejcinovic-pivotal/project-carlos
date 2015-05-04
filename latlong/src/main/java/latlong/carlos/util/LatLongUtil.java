package latlong.carlos.util;


public class LatLongUtil {

	public static double LAT_500METERS = 0.004491556;
	public static double LON_500METERS = 0.005986;

	/** 41.474937 is in the middle of the Box 1.1 so we need to adjust it by 250 meters same for LON**/
	public static double START_LAT = 41.474937 + (LAT_500METERS/2) ;
	public static double START_LON = 74.913585 + (LON_500METERS/2);

	public static double NUMBER_OF_SQUARES = 500;

	public static double FINISH_LAT = START_LAT - (LAT_500METERS * NUMBER_OF_SQUARES);
	public static double FINISH_LON = START_LON - (LON_500METERS * NUMBER_OF_SQUARES);



	public static void printCellId(Cell cell) {
		System.out.println("Cell height:" + cell.getCellHeight() + " widht:"
				+ cell.getCellWidth());

	}

	public static Cell calcCellId(LatLong latlong) {

		double lat = Math.abs(latlong.getLat());
		double lon = Math.abs(latlong.getLon());

		if(!isLatLonValid(lat,lon)){
			return null;
		}
		
		int cellHeight = (int) ((START_LAT - lat) / LAT_500METERS);
		int cellWidth = (int) ((START_LON - lon) / LON_500METERS);

		return new Cell(cellHeight, cellWidth);
	}
	
	private static boolean isLatLonValid(double lat, double lon){
		boolean isValid = true;

		if ((lat > START_LAT) || (lat < FINISH_LAT)){
			isValid = false;
		}
		
		if ((lon > START_LON) || (lon < FINISH_LON)){
			isValid = false;
		}
		
		return isValid;
		
	}
}
