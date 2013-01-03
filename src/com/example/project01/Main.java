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
	
    private SurfaceHolder holder; // ���ǽ�Ȧ��

    private Paint paint; // ����Ʈ
    private Canvas canvas; // ĵ����

    private Thread thread; // ������
    private boolean isRun; // ������ ���� ���θ� ��Ÿ���� �÷���
        
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

        // ���ǽ��並 �����ϰ� �����ϴ� ���ǽ�Ȧ�� ��ü�� ��´�.
        holder = getHolder();
        holder.addCallback(this);
        
        // ����Ʈ ��ü�� ��´�.
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
        // ���� ���
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
        
        private int birdWidth1; // ������ �ʺ�
        private int birdHeight1; // ������ ����
        
        private int birdWidth2; // ������ �ʺ�
        private int birdHeight2; // ������ ����
        
        private int birdWidth3; // ������ �ʺ�
        private int birdHeight3; // ������ ����
        
        private int birdWidth4; // ������ �ʺ�
        private int birdHeight4; // ������ ����
        
        private int birdWidth5; // ������ �ʺ�
        private int birdHeight5; // ������ ����

        int x = 10; // �������� ���� ����� X ��ǥ
        int y = 100; // �������� ���� ����� Y
        
        int x2 = 80; // �������� ���� ����� X ��ǥ
        int y2 = 200; // �������� ���� ����� Y
        
        int x3 = 150; // �������� ���� ����� X ��ǥ
        int y3 = 300; // �������� ���� ����� Y
        
        int x4 = 330; // �������� ���� ����� X ��ǥ
        int y4 = 550; // �������� ���� ����� Y
        
        int x5 = 220; // �������� ���� ����� X ��ǥ
        int y5 = 400; // �������� ���� ����� Y

        int maxX; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
        
        int maxX2; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY2; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
        
        int maxX3; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY3; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
        
        int maxX4; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY4; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
        
        int maxX5; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
        int maxY5; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ

        int speed = 50; // ������ �̵� �ʺ�
        int speed2 = 50; // ������ �̵� �ʺ�
        int speed3 = 50; // ������ �̵� �ʺ�

        boolean isHit = false; // ������ ��ġ ����
        boolean isHit2 = false; // ������ ��ġ ����
        boolean isHit3 = false; // ������ ��ġ ����
        boolean isHit4 = false; // ������ ��ġ ����
        boolean isHit5 = false; // ������ ��ġ ����

        // �� ���� �����ܰ� �������� �ʺ�� ���̸� ���Ѵ�. �׸��� ȭ�� ũ�⸦ ���Ѵ�.
        MyIcon() {
            // �⺻ �ȵ���̵� ���
        	
        	bird1 = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        	bird2 = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);
        	bird3 = BitmapFactory.decodeResource(getResources(), R.drawable.bird3);
        	bqueen = BitmapFactory.decodeResource(getResources(), R.drawable.bqueen);
        	bking = BitmapFactory.decodeResource(getResources(), R.drawable.bking);
            
            // ��ġ�� ����� �ȵ���̵� ���
            birdc1 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick1);
            birdc2 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick2);
            birdc3 = BitmapFactory.decodeResource(getResources(), R.drawable.error);
            bqueenc1 = BitmapFactory.decodeResource(getResources(), R.drawable.birdclick3);
            bkingc1 = BitmapFactory.decodeResource(getResources(), R.drawable.error);
            
            birdWidth1 = bird1.getWidth(); // ������ �ʺ�
            birdHeight1 = bird1.getHeight(); // ������ ����
            
            birdWidth2 = bird2.getWidth(); // ������ �ʺ�
            birdHeight2 = bird2.getHeight(); // ������ ����
            
            birdWidth3 = bird3.getWidth(); // ������ �ʺ�
            birdHeight3 = bird3.getHeight(); // ������ ����
            
            birdWidth4 = bqueen.getWidth(); // ������ �ʺ�
            birdHeight4 = bqueen.getHeight(); // ������ ����
            
            birdWidth5 = bking.getWidth(); // ������ �ʺ�
            birdHeight5 = bking.getHeight(); // ������ ����
            
            maxX = getWidth() - birdWidth1; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY = getHeight() - birdHeight1; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
            
            maxX2 = getWidth() - birdWidth2; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY2 = getHeight() - birdHeight2; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
            
            maxX3 = getWidth() - birdWidth3; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY3 = getHeight() - birdHeight3; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
            
            maxX4 = getWidth() - birdWidth4; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY4 = getHeight() - birdHeight4; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
            
            maxX5 = getWidth() - birdWidth5; // �������� �̵��� �� �ִ� �ִ� X ��ǥ
            maxY5 = getHeight() - birdHeight5; // �������� �̵��� �� �ִ� �ִ� Y ��ǥ
            
        }

        // ������ �̵� �ʺ�(���ǵ�)�� ������ �� �ִ� ������
        MyIcon(int speed) {
            this();
            this.speed = speed;
        }

        // ��ġ�� ���� �������� ���� ����� isHit ������ true�� �����Ѵ�.
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

        // �������� ������ �������� �̵���Ų��. �̶� ȭ���� �Ѿ ���� ȭ�� ������ �̵���Ų��.
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

            // X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x < 0) {
                x = 0;
            }

            // X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x > 360) {
                x = 360;
            }

            // Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y < 120) {
                y = 120;
            }

            // Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
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

            // X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x2 < 0) {
            	x2 = 0;
            }

            // X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x2 > 360) {
            	x2 = 360;
            }

            // Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y2 < 120) {
            	y2 = 120;
            }

            // Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
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

            // X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x3 < 0) {
            	x3 = 0;
            }

            // X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x3 > 360) {
            	x3 = 360;
            }

            // Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y3 < 120) {
            	y3 = 120;
            }

            // Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
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

            // X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x4 < 0) {
            	x4 = 0;
            }

            // X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x4 > 360) {
            	x4 = 360;
            }

            // Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y4 < 120) {
            	y4 = 120;
            }

            // Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
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

            // X ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (x5 < 0) {
            	x5 = 0;
            }

            // X ��ǥ�� ������ ȭ���� ���� ���ٸ�
            if (x5 > 360) {
            	x5 = 360;
            }

            // Y ��ǥ�� ���� ȭ���� ���� ���ٸ�
            if (y5 < 120) {
            	y5 = 120;
            }

            // Y ��ǥ�� �Ʒ��� ȭ���� ���� ���ٸ�
            if (y5 > 680) {
            	y5 = 680;
            }
        }
        
        // �������� �ڵ����� �̵���Ų��.
        public void moveAuto(Canvas canvas) {
            // �����̴ٰ� ����ڿ� ���� ��ġ�Ǿ��ٸ� icon2 �̹����� �����ְ�
            // �׷��� ������ icon �̹����� �����ش�.
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

