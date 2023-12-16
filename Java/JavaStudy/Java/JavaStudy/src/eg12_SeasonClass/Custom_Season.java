package eg12_SeasonClass;

// 自定义枚举类
class Custom_Season {
    // 1、声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;

    // 2、私有化类的构造器，并初始化对象属性
    private Custom_Season(String seasonName, String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    // 3、创建当前枚举类的多个对象：public static final类型的
    public static final Custom_Season SPRING = new Custom_Season("春天", "春暖花开");
    public static final Custom_Season SUMMER = new Custom_Season("夏天", "夏日炎炎");
    public static final Custom_Season AUTUMN = new Custom_Season("秋天", "秋高气爽");
    public static final Custom_Season WINTER = new Custom_Season("冬天", "冬日可爱");

    // 4、其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 4、其他诉求2：提供toString方法
    @Override
    public String toString() {
        return "Custom_Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
