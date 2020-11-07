/*
dP                                         dP   
88                                         88   
88 88d8b.d8b. 88d888b. .d8888b. 88d888b. d8888P 
88 88'`88'`88 88'  `88 88'  `88 88'  `88   88   
88 88  88  88 88.  .88 88.  .88 88         88   
dP dP  dP  dP 88Y888P' `88888P' dP         dP   
              88                                
              dP                                
888888ba                                            dP                            oo                   
88    `8b                                           88                                                 
88     88 .d8888b. 88d888b. .d8888b. 88d888b. .d888b88 .d8888b. 88d888b. .d8888b. dP .d8888b. .d8888b. 
88     88 88ooood8 88'  `88 88ooood8 88'  `88 88'  `88 88ooood8 88'  `88 88'  `"" 88 88ooood8 Y8ooooo. 
88    .8P 88.  ... 88.  .88 88.  ... 88    88 88.  .88 88.  ... 88    88 88.  ... 88 88.  ...       88 
8888888P  `88888P' 88Y888P' `88888P' dP    dP `88888P8 `88888P' dP    dP `88888P' dP `88888P' `88888P' 
                   88                                                                                  
                   dP    
*/
package Bot;
import java.awt. * ;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import robocode. * ;
import java.awt. * ;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class fnlbot extends AdvancedRobot {
  public int num = 1;
  public int rotation = 90;
  public void run() {

    setAdjustGunForRobotTurn(true);
    
    turnLeft(getHeading());
    TacticalAvoidance();
    while (num == 1) {
      setAdjustRadarForRobotTurn(true);
      turnRadarRight(360);
    }
  }

  public void TacticalAvoidance() {
    ahead(150);
  }

  public void onHitWall(HitWallEvent event) {
    turnLeft(rotation);
  }

  public void BulletHitEvent() {
    TacticalAvoidance();
  }

  public void onHitRobot(HitRobotEvent event) {
    turnRight(180);
    rotation = -1*90;
    TacticalAvoidance();
  }
/*
d888888P                                       dP   oo                   
   88                                          88                        
   88    .d8888b. 88d888b. .d8888b. .d8888b. d8888P dP 88d888b. .d8888b. 
   88    88'  `88 88'  `88 88'  `88 88ooood8   88   88 88'  `88 88'  `88 
   88    88.  .88 88       88.  .88 88.  ...   88   88 88    88 88.  .88 
   dP    `88888P8 dP       `8888P88 `88888P'   dP   dP dP    dP `8888P88 
                                .88                                  .88 
                            d8888P                               d8888P  
 .d888888                 dP 
d8'    88                 88 
88aaaaa88a 88d888b. .d888b88 
88     88  88'  `88 88'  `88 
88     88  88    88 88.  .88 
88     88  dP    dP `88888P8 
                             
                             
.d88888b  dP                           dP   oo                   
88.    "' 88                           88                        
`Y88888b. 88d888b. .d8888b. .d8888b. d8888P dP 88d888b. .d8888b. 
      `8b 88'  `88 88'  `88 88'  `88   88   88 88'  `88 88'  `88 
d8'   .8P 88    88 88.  .88 88.  .88   88   88 88    88 88.  .88 
 Y88888P  dP    dP `88888P' `88888P'   dP   dP dP    dP `8888P88 
                                                             .88 
                                                         d8888P  
 */
  public void onScannedRobot(ScannedRobotEvent e) {
    double absoluteBearing = getHeading() + e.getBearing();
    double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
    turnGunRight(bearingFromGun);
    if (getGunHeat() == 0 && Math.abs(getGunTurnRemaining()) < 10) {
    	  double distance = e.getDistance();
    	  if(distance >= 400){
          fire(1);
        }
        else if(distance < 400 && distance > 200){
          fire(2);
        }
        else{
          fire(3);
        }
    }
    TacticalAvoidance();
  }

}