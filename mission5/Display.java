import java.util.LinkedList;


public class Display {
    LinkedList<String> clock;

    public Display() {
        clock = displayAllTime();
        
    }

    public LinkedList<String> displayAllTime() {
        LinkedList<String> list = new LinkedList<String>();
        list.addFirst("한");      // .add를 메소드안에서 사용하지 않으면 오류 뜬다. why..?
        list.add("두");
        list.add("세");
        list.add("네");
        list.add("다");
        list.add("섯");
        list.add("\n여");
        list.add("섯");
        list.add("일");
        list.add("곱");
        list.add("여");
        list.add("덟");
        list.add("\n아");
        list.add("홉");
        list.add("열");
        list.add("한");
        list.add("두");
        list.add("시");
        list.add("\n자");
        list.add("이");
        list.add("삼");
        list.add("사");
        list.add("오");
        list.add("십");
        list.add("\n정");
        list.add("일");
        list.add("이");
        list.add("삼");
        list.add("시");
        list.add("육");
        list.add("\n오");
        list.add("오");
        list.add("칠");
        list.add("팔");
        list.add("구");
        list.add("분");

        return list;
    }


//            {"한", "두", "세", "네", "다", "섯",
//            "\n여", "섯", "일", "곱", "여", "덟",
//            "\n아", "홉", "열", "한", "두", "시",
//            "\n자", "이", "삼", "사", "오", "십",
//            "\n정", "일", "이", "삼", "사", "육",
//            "\n오", "오", "칠", "팔", "구", "분"};

}
