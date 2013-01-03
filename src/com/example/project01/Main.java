package com.example.project01;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public  class Main extends Activity {
	MediaPlayer mp;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	        
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(new MySurfaceView(this));   
        
        mp = MediaPlayer.create(getApplicationContext(), R.raw.title_theme);
        mp.setLooping(true);
        mp.start();
        
    }   
}


class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,
        Runnable {	
	
    private SurfaceHolder holder; // 서피스홀더

    private Paint paint; // 페인트
    private Canvas canvas; // 캔버스

    private Thread thread; // 스레드
    private boolean isRun; // 스레드 실행 여부를 나타내는 플래그
        
    static int score = 0;
	static int stage = 0;
	static int life = 3;
	static int times = 1000;
	static int chke;
	
    MyIcon myIcon;
    MyIcon myIcon2;
    MyIcon myIcon3;
    MyIcon queen;
    MyIcon king;
    
    public MySurfaceView(Context context) {
        super(context);

        // 서피스뷰를 접근하고 제어하는 서피스홀더 객체를 얻는다.
        holder = getHolder();
        holder.addCallback(this);
        
        // 페이트 객체를 얻는다.
        paint = new Paint();
        paint.setAntiAlias(true);
        
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        isRun = false;
        score = 0;
        thread.stop();
		System.exit(0);        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	int touchX = (int) event.getX();
    	int touchY = (int) event.getY();
    	int touchX2 = (int) event.getX();
    	int touchY2 = (int) event.getY();
    	int touchX3 = (int) event.getX();
    	int touchY3 = (int) event.getY();
    	int touchX4 = (int) event.getX();
    	int touchY4 = (int) event.getY();
    	int touchX5 = (int) event.getX();
    	int touchY5 = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
			myIcon.isHit(touchX, touchY);
        }
        
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            myIcon2.isHit2(touchX2, touchY2);
        }
        
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            myIcon3.isHit3(touchX3, touchY3);
        }
        
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            queen.isHit4(touchX4, touchY4);
        }
        
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            king.isHit5(touchX5, touchY5);
        }
        
        return true;
    }

    @Override
    public void run() {
        myIcon = new MyIcon();
        myIcon2 = new MyIcon();
        myIcon3 = new MyIcon();
        queen = new MyIcon();
        king = new MyIcon();
        isRun = true;
        
        while (isRun) {
            canvas = holder.lockCanvas();
            Bitmap mImgBack = null;
    		mImgBack = BitmapFactory.decodeResource(getResources(), R.drawable.aback);
			canvas.drawBitmap(mImgBack, 0, 0, null);

            DrawGameOver(canvas);
			DrawHeart(canvas);
            DrawScore(canvas);
            DrawStage(canvas);
            
            if (life != 0){
            DrawScreen(canvas);
        	myIcon.moveAuto(canvas);        	
        	myIcon2.moveAuto2(canvas);        	
        	myIcon3.moveAuto3(canvas);   
            queen.moveAuto4(canvas);
            king.moveAuto5(canvas);
            }
            
            holder.unlockCanvasAndPost(canvas);
            
        	try {
                Thread.sleep(times);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }

    
    public void DrawScreen(Canvas canvas) {
        Random rnd = new Random();
        int rn = rnd.nextInt(3) + 1;
    	Bitmap egg1;
    	Bitmap egg2;
    	Bitmap egg3;
    	egg1 = BitmapFactory.decodeResource(getResources(), R.drawable.egg1);
    	egg2 = BitmapFactory.decodeResource(getResources(), R.drawable.egg2);
    	egg3 = BitmapFactory.decodeResource(getResources(), R.drawable.egg3);    	
    	
    	if (rn == 1){
		canvas.drawBitmap(egg1, 230, 50, paint);
		chke = 1;
    	}else if(rn == 2){
		canvas.drawBitmap(egg2, 230, 50, paint);
		chke = 2;
    	}else if(rn == 3){
		canvas.drawBitmap(egg3, 230, 50, paint);
		chke = 3;
    	}
	}
    
	public void DrawScore(Canvas canvas) {
		Paint paint1 = new Paint();
		paint1.setAntiAlias(true);
		paint1.setColor(Color.BLUE);
		paint1.setTextSize(30);
		paint1.setTypeface(Typeface.create("", Typeface.BOLD));
	    canvas.drawText("Score " + score, 10, 30, paint1);
	    
	    if(score > 0 && score < 10){
	    	times = 1000;	    	
	    }else if(score > 10 && score < 30){
	    	times = 800;	    	
	    }else if(score > 30 && score < 60){
	    	times = 600;	    	
	    }else if(score > 60 && score < 100){
	    	times = 400;	    	
	    }else if(score > 100){
	    	times = 200;	    	
	    }
	    
	}
	
	public void DrawStage(Canvas canvas) {
		if (score >= 0 && score < 10) {stage = 1;}
		if (score > 10 && score < 30) {stage = 2;}
		if (score > 30 && score < 60) {stage = 3;}
		if (score > 60 && score < 100) {stage = 4;}
		if (score > 100) {stage = 5;}
		
		Paint paint2 = new Paint();
		paint2.setAntiAlias(true);
		paint2.setTextSize(30);
		paint2.setColor(Color.MAGENTA);
		paint2.setTypeface(Typeface.create("", Typeface.BOLD));
		canvas.drawText("Stage " + stage, 370, 30, paint2);
	}
	
	public void DrawHeart(Canvas canvas){
        Bitmap heart1;
        Bitmap heart0;
    	heart1 = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);
    	heart0 = BitmapFactory.decodeResource(getResources(), R.drawable.heart0);
    	if (life == 3){
		canvas.drawBitmap(heart1, 200, 5, paint);
		canvas.drawBitmap(heart1, 240, 5, paint);
		canvas.drawBitmap(heart1, 280, 5, paint);
    	}else if (life == 2){
    		canvas.drawBitmap(heart1, 240, 5, paint);
    		canvas.drawBitmap(heart1, 280, 5, paint);
        }else if (life == 1){
    		canvas.drawBitmap(heart1, 280, 5, paint);
        }else if (life == 0){
    		canvas.drawBitmap(heart0, 280, 5, paint);
        }        	
        }
	
	public void DrawGameOver(Canvas canvas) {
		if (life == 0){
		Bitmap gameover;
		gameover = BitmapFactory.decodeResource(getResources(), R.drawable.gameover);
		canvas.drawBitmap(gameover, 0, 0, paint);
        isRun = false;
		}
	}	

	class MyIcon {
        // 방향 상수
        private static final int LEFT = 0;
        private static final int UP = 1;
        private static final int RIGHT = 2;
        private static final int DOWN = 3;

        private Bitmap bird1;
        private Bitmap bird2;
        private Bitmap bird3;
        private Bitmap bqueen;
        private Bitmap bking;
        private Bitmap birdc1;
        private Bitmap birdc2;
        private Bitmap birdc3;
        private Bitmap bqueenc1;
        private Bitmap bkingc1;
        
        private int birdWidth1; // 아이콘 너비
        private int birdHeight1; // 아이콘 높이
        
        private int birdWidth2; // 아이콘 너비
        private int birdHeight2; // 아이콘 높이
        
        private int birdWidth3; // 아이콘 너비
        private int birdHeight3; // 아이콘 높이
        
        private int birdWidth4; // 아이콘 너비
        private int birdHeight4; // 아이콘 높이
        
        private int birdWidth5; // 아이콘 너비
        private int birdHeight5; // 아이콘 높이

        int x = 10; // 아이콘의 좌측 상단의 X 좌표
        int y = 100; // 아이콘의 좌측 상단의 Y
        
        int x2 = 80; // 아이콘의 좌측 상단의 X 좌표
        int y2 = 200; // 아이콘의 좌측 상단의 Y
        
        int x3 = 150; // 아이콘의 좌측 상단의 X 좌표
        int y3 = 300; // 아이콘의 좌측 상단의 Y
        
        int x4 = 330; // 아이콘의 좌측 상단의 X 좌표
        int y4 = 550; // 아이콘의 좌측 상단의 Y
        
        int x5 = 220; // 아이콘의 좌측 상단의 X 좌표
        int y5 = 400; // 아이콘의 좌측 상단의 Y

        int maxX; // 아이콘이 이동할 수 있는 최대 X 좌표
        int maxY; // 아이콘이 이동할 수 있는 최대 Y 좌표
        
        int maxX2; // 아이콘이 이동할 수 있는 최대 X 좌표
        int maxY2; // 아이콘이 이동할 수 있는 최대 Y 좌표
        
        int maxX3; // 아이콘이 이동할 수 있는 최대 X 좌표
        int maxY3; // 아이콘이 이동할 수 있는 최대 Y 좌표
        
        int maxX4; // 아이콘이 이동할 수 있는 최대 X 좌표
        int maxY4; // 아이콘이 이동할 수 있는 최대 Y 좌표
        
        int maxX5; // 아이콘이 이동할 수 있는 최대 X 좌표
        int maxY5; // 아이콘이 이동할 수 있는 최대 Y 좌표

        int speed = 50; // 아이콘 이동 너비
        int speed2 = 50; // 아이콘 이동 너비
        int speed3 = 50; // 아이콘 이동 너비

        boolean isHit = false; // 아이콘 터치 여부
        boolean isHit2 = false; // 아이콘 터치 여부
        boolean isHit3 = false; // 아이콘 터치 여부
        boolean isHit4 = false; // 아이콘 터치 여부
        boolean isHit5 = false; // 아이콘 터치 여부

        // 두 개의 아이콘과 아이콘의 너비와 높이를 구한다. 그리고 화면 크기를 구한다.
        MyIcon() {
            // 기본 안드로이드 모양
        	
        	bird1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        	bird2 = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        	bird3 = BitmapFactory.decodeResource(getResources(), R.drawable.bird3);
        	bqueen = BitmapFactory.decodeResource(getResources(), R.drawable.bqueen);
        	bking = BitmapFactory.decodeResource(getResources(), R.drawable.bking);
            
            // 터치시 변경될 안드로이드 모양
            birdc1 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick1);
            birdc2 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick2);
            birdc3 = BitmapFactory.decodeResource(getResources(), R.drawable.error);
            bqueenc1 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick3);
            bkingc1 = BitmapFactory.decodeResource(getResources(), R.drawable.error);
            
            birdWidth1 = bird1.getWidth(); // 아이콘 너비
            birdHeight1 = bird1.getHeight(); // 아이콘 높이
            
            birdWidth2 = bird2.getWidth(); // 아이콘 너비
            birdHeight2 = bird2.getHeight(); // 아이콘 높이
            
            birdWidth3 = bird3.getWidth(); // 아이콘 너비
            birdHeight3 = bird3.getHeight(); // 아이콘 높이
            
            birdWidth4 = bqueen.getWidth(); // 아이콘 너비
            birdHeight4 = bqueen.getHeight(); // 아이콘 높이
            
            birdWidth5 = bking.getWidth(); // 아이콘 너비
            birdHeight5 = bking.getHeight(); // 아이콘 높이
            
            maxX = getWidth() - birdWidth1; // 아이콘이 이동할 수 있는 최대 X 좌표
            maxY = getHeight() - birdHeight1; // 아이콘이 이동할 수 있는 최대 Y 좌표
            
            maxX2 = getWidth() - birdWidth2; // 아이콘이 이동할 수 있는 최대 X 좌표
            maxY2 = getHeight() - birdHeight2; // 아이콘이 이동할 수 있는 최대 Y 좌표
            
            maxX3 = getWidth() - birdWidth3; // 아이콘이 이동할 수 있는 최대 X 좌표
            maxY3 = getHeight() - birdHeight3; // 아이콘이 이동할 수 있는 최대 Y 좌표
            
            maxX4 = getWidth() - birdWidth4; // 아이콘이 이동할 수 있는 최대 X 좌표
            maxY4 = getHeight() - birdHeight4; // 아이콘이 이동할 수 있는 최대 Y 좌표
            
            maxX5 = getWidth() - birdWidth5; // 아이콘이 이동할 수 있는 최대 X 좌표
            maxY5 = getHeight() - birdHeight5; // 아이콘이 이동할 수 있는 최대 Y 좌표
            
        }

        // 아이콘 이동 너비(스피드)를 설정할 수 있는 생성자
        MyIcon(int speed) {
            this();
            this.speed = speed;
        }

        // 터치된 곳이 아이콘의 범위 내라면 isHit 변수를 true로 설정한다.
        public void isHit(int touchX, int touchY) {
            if (touchX >= x && touchX <= x + birdWidth1 && touchY >= y
                    && touchY <= y + birdHeight1) {            	
                isHit = true;
                if(chke == 1){
                score = score + 1;
                }
            }            
        }
        
        public void isHit2(int touchX2, int touchY2) {
            if (touchX2 >= x2 && touchX2 <= x2 + birdWidth2 && touchY2 >= y2
                    && touchY2 <= y2 + birdHeight2) {
                isHit2 = true;
                if(chke == 2){
                score = score + 1;
                }
            }            
        }
        
        public void isHit3(int touchX3, int touchY3) {
            if (touchX3 >= x3 && touchX3 <= x3 + birdWidth3 && touchY3 >= y3
                    && touchY3 <= y3 + birdHeight3) {
                isHit3 = true;
            }            
        }
        
        public void isHit4(int touchX4, int touchY4) {
            if (touchX4 >= x4 && touchX4 <= x4 + birdWidth4 && touchY4 >= y4
                    && touchY4 <= y4 + birdHeight4) {
                isHit4 = true;
                if(chke == 3){
                score = score + 1;
                }
            }            
        }
        
        public void isHit5(int touchX5, int touchY5) {
            if (touchX5 >= x5 && touchX5 <= x5 + birdWidth5 && touchY5 >= y5
                    && touchY5 <= y5 + birdHeight5) {
                isHit5 = true;
            }            
        }

        // 아이콘을 임의의 방향으로 이동시킨다. 이때 화면을 넘어갈 때는 화면 끝으로 이동시킨다.
        public void setNextPosition() {
            int direction = (int) (Math.random() * 4);

            if (direction == LEFT) {
                x -= speed + score;
            }

            if (direction == RIGHT) {
                x += speed + score;
            }

            if (direction == UP) {
                y -= speed + score;
            }
            if (direction == DOWN) {
                y += speed + score;
            }

            // X 좌표가 왼쪽 화면을 벗어 났다면
            if (x < 0) {
                x = 0;
            }

            // X 좌표가 오른쪽 화면을 벗어 났다면
            if (x > 360) {
                x = 360;
            }

            // Y 좌표가 위쪽 화면을 벗어 났다면
            if (y < 120) {
                y = 120;
            }

            // Y 좌표가 아래쪽 화면을 벗어 났다면
            if (y > 680) {
            	y = 680;
            }
        }
        
        public void setNextPosition2() {
            int direction = (int) (Math.random() * 4);
            
            if (direction == LEFT) {
                x2 -= speed2 + score;
            }

            if (direction == RIGHT) {
                x2 += speed2 + score;
            }

            if (direction == UP) {
                y2 -= speed2 + score;
            }
            if (direction == DOWN) {
                y2 += speed2 + score;
            }

            // X 좌표가 왼쪽 화면을 벗어 났다면
            if (x2 < 0) {
            	x2 = 0;
            }

            // X 좌표가 오른쪽 화면을 벗어 났다면
            if (x2 > 360) {
            	x2 = 360;
            }

            // Y 좌표가 위쪽 화면을 벗어 났다면
            if (y2 < 120) {
            	y2 = 120;
            }

            // Y 좌표가 아래쪽 화면을 벗어 났다면
            if (y2 > 680) {
            	y2 = 680;
            }
        }
        
        public void setNextPosition3() {
            int direction = (int) (Math.random() * 4);
        	
            if (direction == LEFT) {
                x3 -= speed3 + score;
            }

            if (direction == RIGHT) {
                x3 += speed3 + score;
            }

            if (direction == UP) {
                y3 -= speed3 + score;
            }
            if (direction == DOWN) {
                y3 += speed3 + score;
            }

            // X 좌표가 왼쪽 화면을 벗어 났다면
            if (x3 < 0) {
            	x3 = 0;
            }

            // X 좌표가 오른쪽 화면을 벗어 났다면
            if (x3 > 360) {
            	x3 = 360;
            }

            // Y 좌표가 위쪽 화면을 벗어 났다면
            if (y3 < 120) {
            	y3 = 120;
            }

            // Y 좌표가 아래쪽 화면을 벗어 났다면
            if (y3 > 680) {
            	y3 = 680;
            }
        }
        
        public void setNextPosition4() {
            int direction = (int) (Math.random() * 4);

            if (direction == LEFT) {
                x4 -= speed + score;
            }

            if (direction == RIGHT) {
                x4 += speed + score;
            }

            if (direction == UP) {
                y4 -= speed + score;
            }
            if (direction == DOWN) {
                y4 += speed + score;
            }

            // X 좌표가 왼쪽 화면을 벗어 났다면
            if (x4 < 0) {
            	x4 = 0;
            }

            // X 좌표가 오른쪽 화면을 벗어 났다면
            if (x4 > 360) {
            	x4 = 360;
            }

            // Y 좌표가 위쪽 화면을 벗어 났다면
            if (y4 < 120) {
            	y4 = 120;
            }

            // Y 좌표가 아래쪽 화면을 벗어 났다면
            if (y4 > 680) {
            	y4 = 680;
            }
        }
        
        public void setNextPosition5() {
            int direction = (int) (Math.random() * 4);

            if (direction == LEFT) {
                x5 -= speed + score;
            }

            if (direction == RIGHT) {
                x5 += speed + score;
            }

            if (direction == UP) {
                y5 -= speed + score;
            }
            if (direction == DOWN) {
                y5 += speed + score;
            }

            // X 좌표가 왼쪽 화면을 벗어 났다면
            if (x5 < 0) {
            	x5 = 0;
            }

            // X 좌표가 오른쪽 화면을 벗어 났다면
            if (x5 > 360) {
            	x5 = 360;
            }

            // Y 좌표가 위쪽 화면을 벗어 났다면
            if (y5 < 120) {
            	y5 = 120;
            }

            // Y 좌표가 아래쪽 화면을 벗어 났다면
            if (y5 > 680) {
            	y5 = 680;
            }
        }
        
        // 아이콘을 자동으로 이동시킨다.
        public void moveAuto(Canvas canvas) {
            // 움직이다가 사용자에 의해 터치되었다면 icon2 이미지를 보여주고
            // 그렇지 않으면 icon 이미지를 보여준다.
            setNextPosition();
            if (isHit) {
                canvas.drawBitmap(birdc1, x, y, paint);
                isHit = false;
            } else {
            	canvas.drawBitmap(bird1, x, y, paint);
            }
        }
        
        public void moveAuto2(Canvas canvas2) {
            setNextPosition2();
            if (isHit2) {
                canvas2.drawBitmap(birdc2, x2, y2, paint);
                isHit2 = false;
            } else {
                canvas2.drawBitmap(bird2, x2, y2, paint);
            }
        }
        
        public void moveAuto3(Canvas canvas3) {
            setNextPosition3(); 
            Random rnd = new Random();
            int r = rnd.nextInt(10) + 1;
            
            if (isHit3) {
                canvas3.drawBitmap(birdc3, x3, y3, paint);
                isHit3 = false;
                life = life - 1;
            } else if (r == 1) {
                canvas3.drawBitmap(bird1, x3, y3, paint);
            }else if(r == 2){
                canvas3.drawBitmap(bird2, x3, y3, paint);
            }else if(r == 3){
                canvas3.drawBitmap(bqueen, x3, y3, paint);
            }else if(r == 4){
                canvas3.drawBitmap(bird1, x3, y3, paint);
            }else if(r == 5){
                canvas3.drawBitmap(bird2, x3, y3, paint);
            }else if(r == 6){
                canvas3.drawBitmap(bqueen, x3, y3, paint);
            }else if(r == 7){
                canvas3.drawBitmap(bird1, x3, y3, paint);
            }else if(r == 8){
                canvas3.drawBitmap(bird2, x3, y3, paint);
            }else if(r == 9){
                canvas3.drawBitmap(bqueen, x3, y3, paint);
            }else if(r == 10){
                canvas3.drawBitmap(bird3, x3, y3, paint);
            }
        }
        
        public void moveAuto4(Canvas canvas4) {
            setNextPosition4();
            if (isHit4) {
                canvas4.drawBitmap(bqueenc1, x4, y4, paint);
                isHit4 = false;
            } else {
                canvas4.drawBitmap(bqueen, x4, y4, paint);
            }
        }
        
        public void moveAuto5(Canvas canvas5) {
            setNextPosition5();
            Random rnd = new Random();
            int rr = rnd.nextInt(10) + 1;
            
            if (isHit5) {
                canvas5.drawBitmap(bkingc1, x5, y5, paint);
                isHit5 = false;
                life = life - 1;
            } else if (rr == 1) {
            	canvas5.drawBitmap(bird1, x5, y5, paint);
            }else if(rr == 2){
            	canvas5.drawBitmap(bird1, x5, y5, paint);
            }else if(rr == 3){
            	canvas5.drawBitmap(bird1, x5, y5, paint);
            }else if(rr == 4){
            	canvas5.drawBitmap(bird2, x5, y5, paint);
            }else if(rr == 5){
            	canvas5.drawBitmap(bird2, x5, y5, paint);
            }else if(rr == 6){
            	canvas5.drawBitmap(bird2, x5, y5, paint);
            }else if(rr == 7){
            	canvas5.drawBitmap(bqueen, x5, y5, paint);
            }else if(rr == 8){
            	canvas5.drawBitmap(bqueen, x5, y5, paint);
            }else if(rr == 9){
            	canvas5.drawBitmap(bqueen, x5, y5, paint);
            }else if(rr == 10){
            	canvas5.drawBitmap(bking, x5, y5, paint);
            }
           

        }

        }
	 public void onClick(View v) {
 		// TODO Auto-generated method stub
 		
 	}
   


}

