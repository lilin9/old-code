package eg12_SeasonClass;

interface Info{
    void show();
}

// enum关键字定义枚举类
enum enum_Season implements Info{
    // 1、提供当前枚举类的对象，多个对象之间用 "," 隔开，末尾对象用 ";" 结束
    /*
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冬日可爱");
     */
    SPRING("春天", "春暖花开"){
        @Override
        public void show() {
            System.out.println("春色满园管不住，一支红杏出墙来！");
        }
    },
    SUMMER("夏天", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("仲夏苦夜短，开轩纳微凉！");
        }
    },
    AUTUMN("秋天", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("银烛秋光冷画屏，轻罗小扇扑流萤！");
        }
    },
    WINTER("冬天", "冬日可爱"){
        @Override
        public void show() {
            System.out.println("墙角数枝梅，凌寒独自开！");
        }
    };

    // 2、声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;

    // 2、私有化类的构造器，并初始化对象属性
    private enum_Season(String seasonName, String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }



    // 4、其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

}
