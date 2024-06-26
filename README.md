# modern-java-untold

---

### 01. 왜 배워야 하나요? 뭐가 좋아요?

- [WhyJava8.java](src%2FWhyJava8.java)
- [OopAndFpExamples.java](src%2FOopAndFpExamples.java)
- 간결한 코드로 가독성을 높이고 오류 발생률을 줄일 수 있다.

### 02. `Function`, The Transformer

- [FunctionalInterfaceExamples.java](src%2FFunctionalInterfaceExamples.java)
- Functional Interface : Interface 중, abstract 메서드를 하나만 가지는 인터페이스 -> 메서드 람다를 사용할 수 있음
- Identity Function : 변형 없이 입력 값을 그대로 반환하는 함수
- Function<T, R> : R apply(T t);

### 03. `Consumer`, The Spartan, Give Them Nothing but Take from Them Everything

- [FunctionalInterfaceExamples.java](src%2FFunctionalInterfaceExamples.java)
- Consumer<T> : void accept(T t);

### 04. `Predicate`, The Judge

- [FunctionalInterfaceExamples.java](src%2FFunctionalInterfaceExamples.java)
- Predicate<T> : boolean test(T t);

### 05. `Supplier`, The Master of Lazy

- [FunctionalInterfaceExamples.java](src%2FFunctionalInterfaceExamples.java)
- Supplier<T> : T get();

### 06. 직접 만드는 Functional Interface 어때요? 참 쉽죠?

- [CustomFunctionalInterfaceExamples.java](src%2FCustomFunctionalInterfaceExamples.java)
- @FunctionalInterface : abstract 메서드가 하나가 아니면 컴파일 에러 발생!

### 07. 실전 예제와 Functional Interface 의 제약 사항

- [CustomFunctionalInterfaceExamples.java](src%2FCustomFunctionalInterfaceExamples.java)
- [FunctionalInterfaceRealExamples.java](src%2FFunctionalInterfaceRealExamples.java)
- Target method is generic 에러
- Predicate<T> vs Predicate<? super T>

### 08. Stream API - 01 시작하기전에, 02 Identity Function

- [StreamPrelude.java](src%2FStreamPrelude.java)

### 08. Stream API - 03 Stream API 01 무한 collection, 02 Stream vs 예전방식 

- [StreamExamples1.java](src%2FStreamExamples1.java)
- [StreamExamples2.java](src%2FStreamExamples2.java)
- Stream은 Lazy Iterator다.
- Stream API는 게으르게 동작하기 때문에 효율적이다.

### 08. Stream API - 03 Stream API 03 기초

- [StreamExamples3.java](src%2FStreamExamples3.java)
- Intermediate Operation Method
  - Stream을 리턴하기 때문에 계속 Method Chaining을 통해서 무엇을 해야할지 Stream에게 지시할 수 있다. 
- Terminal Operation Method
- 외부 반복 (for) vs 내부 반복 (stream) 

### 08. Stream API - 03 Stream API 04 좀더 실용적인 예

- [StreamExamples4.java](src%2FStreamExamples4.java)

### 08. Stream API - 03 Stream API 05 Parallel Programming (with ParallelStream)

- [StreamExamples5Parallel.java](src%2FStreamExamples5Parallel.java)

### 08. Stream API - 03 Stream API 06 ParallelStream 성능 테스트
- [StreamExamples5ParallelPerformance.java](src%2FStreamExamples5ParallelPerformance.java)

### 08. Stream API - 03 Stream API 07 ParallelStream 주의 사항
- [StreamExamples5ParallelPerformancePractical.java](src%2FStreamExamples5ParallelPerformancePractical.java)
- 상황에 따라 parallel stream 이 유리할 수도 아닐 수도 있다. 상황에 맞게 사용하자!

### 09. 01 Closure == Close Over (뒤덮다)
- [ClosureExamples.java](src%2FClosureExamples.java)
- First Class Function : Function이 First Class Citizen을 의미한다.
  1. parameter로 넘길 수 있다.
  2. return 값으로 사용할 수 있다.
  3. variable / data structure에 저장할 수 있다.
- java 8 이전에는 final 이어야만 했지만 java 8 이후에는 final or effectively final 이면 된다.
  - effectively final : 선언 후 재정의 하지 않은 변수
- Anonymous Class와 Lambda Expression의 차이
  1. this가 가리키는 scope의 차이 : lambda expression의 경우 object 자체의 scope이 없다.
  2. anonymous의 경우 오버로딩이 불가했지만 lambda의 경우 

### 09. 02 Closure 및 Lambda Expression 내부 구현
- [ClosureExamples2.java](src%2FClosureExamples2.java)

### 10. Higher-Order Function
- [HigherOrderFunctionExamples.java](src%2FHigherOrderFunctionExamples.java)
- 두 가지 조건 중 하나 이상 만족하는 function
  1. (고계함수 or 고차함수) 파라미터로 function을 받는 경우
  2. function을 실행했을때 return 값으로 function이 오는 경우

### 11. Method Reference
- [MethodReferenceExamples.java](src%2FMethodReferenceExamples.java)
- First Class Function 검증
- 생성자를 Method Reference로 사용하는법

---

출처 :
https://www.youtube.com/watch?v=mu9XfJofm8U&list=PLRIMoAKN8c6O8_VHOyBOhzBCeN7ShyJ27
