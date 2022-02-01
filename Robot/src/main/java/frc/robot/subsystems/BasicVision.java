package frc.robot.subsystems;

import edu.wpi.first.cscore.AxisCamera;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Scalar;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;

import edu.wpi.first.cameraserver.CameraServer;
public class BasicVision extends SubsystemBase {
    AxisCamera cam1;
    CvSink sink1;
    CvSource maskedBlue;
    Mat blueMask,redMask,annotatedFrame,currentFrameRaw,currentFrameProc,hierarchy;
    Scalar blueUp,blueLow,redUp,redLow,color,yellow,green;
    Size blurSize;
    List<MatOfPoint> contours;
    double sigmaX = 0.0;

    public BasicVision(){
        
        cam1 = CameraServer.addAxisCamera("cam1","10.87.26.173");
        sink1 = CameraServer.getVideo();
        maskedBlue = CameraServer.putVideo("MaskedBlue", 320, 240);
        currentFrameRaw = new Mat();
        currentFrameProc =new Mat();
        blueMask = new Mat();
        redMask = new Mat();
        blurSize = new Size(11,11);
        blueUp = new Scalar(94,107,43);
        blueLow = new Scalar(119,203,178);
        green = new Scalar(0,255,0);

    }
    @Override
    public void periodic() {
        sink1.grabFrame(currentFrameRaw);
        Imgproc.cvtColor(currentFrameProc, currentFrameProc, Imgproc.COLOR_BGR2HSV);
        Core.inRange(currentFrameProc, blueLow, blueUp, blueMask);//TODO: tune color ranges and add red
        Imgproc.GaussianBlur(blueMask, blueMask,blurSize, sigmaX);//Consider changing to blur
        Imgproc.erode(blueMask,blueMask,new Mat(),new Point(-1,-1),2);
        Imgproc.dilate(blueMask,blueMask,new Mat(),new Point(-1,-1),2);
        Imgproc.findContours(blueMask, contours, hierarchy,Imgproc.RETR_EXTERNAL , Imgproc.CHAIN_APPROX_SIMPLE);
        annotatedFrame = currentFrameProc.clone();
        for (int i = 0;i<contours.size();i++){
            MatOfPoint2f contour = new MatOfPoint2f(contours.get(i).toArray());
            RotatedRect ellipse = Imgproc.fitEllipse(contour);
            double eccentricity = Math.sqrt(1-(Math.pow((ellipse.size.width/2),2) / Math.pow((ellipse.size.height/2),2)));
            if (eccentricity < 0.65){ // should be a ball
                float[] radius=new float[1];
                Point circleCenter=new Point();//might use moments for more accurate center
                Imgproc.minEnclosingCircle(contour, circleCenter,radius); 
                if (Imgproc.contourArea(contour) >= 0.7*3.141*(Math.pow(radius[0],2))){
                    color = green;
                } else {
                    color = yellow;
                }
                Imgproc.circle(annotatedFrame, circleCenter, (int) radius[0],color);
                maskedBlue.putFrame(annotatedFrame);
                

                
            }
        }
        
    }

}
