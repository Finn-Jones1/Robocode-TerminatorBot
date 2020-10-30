package Bot;
import java.awt.*;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import robocode.*;
import java.awt.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;



public class fnlbot extends Robot {
	int num = 0;
	public void run() {
		setAdjustGunForRobotTurn(true);
		turnLeft(getHeading());
//		turnRadarRight(getHeading()- 120);
		TacticalAvoidance();
		while (true) {
			turnGunRight(10);
		}
	}
	
	public void TacticalAvoidance() {
		ahead(5000);
	}
	
	public void onHitWall(HitWallEvent event) {
       	turnLeft(90);

	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		if (num == 1) {
		    double absoluteBearing = getHeading() + e.getBearing();
			double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
			turnGunRight(bearingFromGun);
			fire(3);
		}

		//turnRadarRight(2.0 * Utils.normalRelativeAngleDegrees(getHeading() + e.getBearing() - getRadarHeading()));
	    
	    
	}
	
}
