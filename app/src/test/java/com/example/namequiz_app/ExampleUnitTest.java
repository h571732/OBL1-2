package com.example.namequiz_app;

import org.junit.Test;

import java.util.ArrayList;

import static com.example.namequiz_app.QuizActivity.scorePoints;
import static com.example.namequiz_app.QuizActivity.answ;
import static com.example.namequiz_app.QuizActivity.updateScorePoints;




import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    //TEST for å gi størrelsen på lista
    public void testArrayNull() {
        ArrayList<String> aList = new ArrayList<>();

        int ant = aList.size();

        assertEquals(0, ant);

    }

//Tester metodene med forskjellige verdier
    @Test
    public void testArray3() {
        ArrayList<String> aList = new ArrayList<>();
        aList.add("Animal 1");
        aList.add("Animal 2");
        aList.add("Animal 3");

        int ant = aList.size();

        assertEquals(3, ant);
    }

      @Test
    public void testArray3Remove1() {
        ArrayList<String> aList = new ArrayList<>();
        aList.add("Animal 1");
        aList.add("Animal 2");
        aList.add("Animal 3");
        aList.remove(2);

        int ant = aList.size();

        assertEquals(2, ant);
      }

      //Test for å sjekke at scoren vil oppdateres
      @Test
    public void testUpdateScoreNull(){
        scorePoints = 0;
        answ=true;
        updateScorePoints(answ);

        assertEquals(1, scorePoints);


      }
      //Sjekker ved correct score (skal få +1)
      @Test
      public void testUpdateScore3Correct() {
        scorePoints = 3;
        answ = true;
        updateScorePoints(answ);

        assertEquals(4,scorePoints);

      }
      //Ved feil svar skal ik updatescore økes, men være d samme
      @Test
    public void testUpdateScore3Wrong() {
        scorePoints =3;
        answ = false;
        updateScorePoints(answ);

        assertEquals(3, scorePoints);
      }
}




