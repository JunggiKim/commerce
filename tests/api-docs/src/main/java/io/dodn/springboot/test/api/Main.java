package io.dodn.springboot.test.api;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] multitap = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int multitapCount = multitap[0];
        int playCountNumber = multitap[1];

        int[] usePlugNumber = new int[playCountNumber]; // 사용하려는 멀티탭들의 순서가 들어있는 어레이
        int[] useMultitapNumber = new int[multitapCount]; //현재 사용중인 멀티탭


        String[] usePlayNumber = br.readLine().split(" ");

        for (int i =0 ; i < usePlayNumber.length; i++) {
            usePlugNumber[i] = Integer.parseInt(usePlayNumber[i]);
        }



        int result = 0;

        for (int i = 0; i < usePlugNumber.length; i++) {

            if (i == 0) {     //첫번째 값은 당연히 아직 넣어준게아니기 때문에 넣어준다.
                useMultitapNumber[0] = usePlugNumber[i];
                continue;
            }


            boolean sameNumber = false;
            int minNumber = 0;
            int minNumberArrayNumber = 0;

            for (int j = i; j < useMultitapNumber.length; j++) {

                if (useMultitapNumber[j] == 0) {   // 0 인 경우에는 그 멀티탭 칸을 아직 사용중이 아니므로 현재 숫자가 그 위치를 사용한다.
                    useMultitapNumber[j] = usePlugNumber[i];
                    break;
                }

                if (useMultitapNumber[j] == usePlugNumber[i]) {  // 사용중인 숫자와 같은 숫자가 나왔다면 그대로 사용하면된다
                    continue;
                }

                if(minNumber == 0 || minNumber < useMultitapNumber[j]) {
                    minNumberArrayNumber = j;  // 멀티탭이 다 사용중에 다른 숫자가나왔다면 첫번째의 숫자값을 넣어준다
                    minNumber = useMultitapNumber[j];
                    System.out.println("작은 숫자임 = " + minNumber);
                }

            }


        }


    }


}
