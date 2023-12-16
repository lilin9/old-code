template <typename T>
class List {
   private:
    int length = -1;
    int size = 5;
    T* values;

   public:
    List();
    List(int size);
    ~List();
    void add(T val);
    bool isEmpty();
    bool isFull();
    T get(int index);
};