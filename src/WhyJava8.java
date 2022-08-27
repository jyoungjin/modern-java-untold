import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class WhyJava8 {
    private static final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static void main(final String[] args) throws IOException {
        simpleTask();
    }

    private static void simpleTask() {

        /* 옛날 옛적 for loop 사용하기 */
        final StringBuilder stringBuilder1 = new StringBuilder();
        final int size = numbers.size();  // size()매번 호출하면 비효율 적이니까 이렇게... (문제에 집중하기 힘들어요 ㅠ_ㅠ)
        for (int i = 0; i < size; i++) {  // i에 0 넣고 i가 size 보다 작으면... 가만? 우리가 왜 아직도 이런 코드를 짜야? ㅠ_ㅠ
            stringBuilder1.append(numbers.get(i));  // String 빌더에 Integer 추가
            if (i != size - 1) {  // 현재 index값인 i가 List size 빼기 1 하고 같지 않다? 뭐지? 아! List에 저장된 마지막값!
                stringBuilder1.append(" : ");  // " : " 추가
            }
        }

        /* for-each 사용 */
        final StringBuilder stringBuilder = new StringBuilder();
        final String separator = " : ";  // 각 숫자를 연결해줄 " : "
        for (final Integer number : numbers) { // 저장된 숫자를 하나씩 받아서
            stringBuilder.append(number).append(separator); // String빌더에 숫자 + " : " 추가
        }
        final int stringLength = stringBuilder.length();  // 빌더 length를 얻고... 근데 왜???
        if (stringLength > 0) {  // length가 0 보다 크면... 그러니까 왜??? 아!? 뭔가 들어 있으면? isNotEmpty 같은거 없나?
            stringBuilder.delete(stringLength - separator.length(), stringLength);
            // String빌더 length 빼기 " : ".length부터 String빌더 length까지 지우기? 이게 뭐야?
        }
        System.out.println(stringBuilder.toString());

        /* Java 8 버전 */
        final String result = numbers.stream()
                .map(String::valueOf)      // 각 Integer를 String으로 변환
                .collect(joining(" : "));  // 1 : 2 : 3 : ... 10 끝!
        System.out.println(result);
    }
}