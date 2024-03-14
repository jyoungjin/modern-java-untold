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
---

출처 :
https://www.youtube.com/watch?v=mu9XfJofm8U&list=PLRIMoAKN8c6O8_VHOyBOhzBCeN7ShyJ27
