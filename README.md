# javaprogramming
자바프로그래밍

JAVA Programming
-Term Protect
- 객체개념, 파일처리기능 포함


-주제-
JAVA를 이용한 1부터 50까지의 수를 빠르게 순서대로 찾는 게임 제작
( 부제 : 손은 눈보다 빠르다 )


주제선정이유 
1) 다른 프로그램보다 게임이라는 소재가 사용자에게 친근하게 다가옴
2) 숫자를 이용한다는 점에서 남녀노소 즐길 수 있고, 집중력, 순발력 향상에 도움이 됨
3) 객체개념과 파일처리기능을 모두 활용할 수 있는 적합한 게임이라 여김

기능
1) 파일처리기능을 이용하여 사용자의 이름을 입력받아 랭킹을 매김
2) 1부터 50까지 난수를 발생시키고, 수를 순서대로 클릭하여 사라지게함
3) 시간표시와 여러번 잘못 클릭하였을 시 잠시 화면을 가리는 등의 벌칙

기능2
1) 사용자의 이름을 입력받아 랭킹을 매김( 파일처리기능 이용 ) 
2) 5x5표에 1부터 50까지의 수를 랜덤으로 발생시킨다  
3) 시간표시와 여러번 잘못 클릭하였을 시 잠시 화면을 가리는 등의 벌칙 
4) 틀린 횟수 표시와 수를 순서대로 클릭 시 사라지게 하는 기능 \

-작동방법-
1_to50game 폴더 이용
(이클립스 이용, score.txt는 프로젝트 폴더 안에 포함)

게임을 처음 실행하였을 때, 이름을 인가 받아 게임을 실행을 한다. 

이때, 게임은 1to50게임으로 1부터 50가지의 숫자를 얼마나 빠르게 클릭하는지의 게임이다.

게임 진행 중 클릭을 하면, 테두리가 파란색으로 표시 되도록 하였고, 

게임 화면 위에는 다음숫자와 시간초가 표시되게 하였다.(맞는 숫자 클릭 시 숫자 사라짐) 

이후 게임이 끝나면 화면에 이름과 시간초가 표시되며, 출력창에 클리어 시간이 출력된다. 

이때 사용자 이름과 점수를 한꺼번에 인가받아 score.txt라는 파일에 이름과 점수를 기록한다.

start를 누르면 게임이 시작되고, reset을 누르면 게임이 리셋이 된다. 

score 버튼을 누르면, score.txt에서 글을 읽어와 출력을 하고, 게임이 끝나면 새로운 기록이 기록되어서, 

다시 score버튼을 누르면 새로운 내용이 추가된 것을 확인할 수 있다. 

(txt파일은 프로젝트가 들어있는 곳에 자동적으로 생성 및 존재할시 그 파일에 기록)
