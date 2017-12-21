int birdX = 50, birdY = 50;
int birdW = 20, birdH = 20;
int pipeX = 225;
int groundY = 20;
int random = (int) random(100,400);
double velY = 0;
double gravity = 0.5;
boolean gameState = false;

void setup() {
  size(600, 420);
  
}

void draw() {
  background(0, 255,0);
  fill(255,0,0);
  ellipse(birdX, birdY, 20, 20);
  birdY++;
  fill(0, 0, 255);
  rect(pipeX, random, 60, 190);
  pipeX--;
  if(pipeX == -65) {
    pipeX = 550;
  }
  fill(0, 0, 255);
  rect(pipeX, random, 60, 100);
  pipeX--;
  if(pipeX == -65) {
   pipeX = 550; 
  }
  
  
}

void mousePressed() {
   velY += 1;
   birdY -= velY;
}
boolean intersects(int birdX, int birdY, int paddleX, int paddleY, int paddleLength) {
if (birdY > paddleY - 4 && birdX > paddleX && birdX < paddleX + paddleLength)
return true;
else 
return false;
}