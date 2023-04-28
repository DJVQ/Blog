<!--
createdAt:2023-04-13
createdBy:ycq
-->
### 编译器
##### 功能
RTTI（Run-Time Type Identification)-运行时类型识别

### C++保留字
#### asm
允许在C++程序中嵌入汇编代码

#### const
const修饰的变量必须初始化，一经赋值，不可修改
const修饰函数入参，可防止内部对其进行修改
const修饰函数返回值，可保护指针或者引用不被修改
const修饰函数体，则函数不能修改成员变量，且不能调用任何不用const修饰函数体的成员函数

#### const_cast
形式：const_cast <_type> (expression)
_type必须是指针、引用、成员指针之一
增加/删除const或violate时，使用const_cast删除某个引用或指针的const/violate关键字时，并对其进行了修改，这是一种未定义的行为，是不安全的，反之，若能100%确定不会对其进行修改，则是安全的

#### delete
delete 和 new对应，delete[] 和 new[]对应，delete用于释放new动态申请的内存，若new分配的内存没有被delete释放掉，会发生内存泄漏；对于 new[]，若数组类型是基本数据类型或是无自定义析构函数的类类型，可以使用delete，否则必须使用 delete[]，不然会发生内存泄漏或是程序崩溃

#### dynamic_cast
形式：dynamic_cast <_type> (expression)
在运行时才处理，且运行时会进行类型检查，可保证安全性，但对编译器有要求，需要编译器开启RTTI，且执行效率相对于static_cast要差
不能用于内置的基本数据类型的强制转换
可用于对类层次见进行上下行转换，转换时基类中一定要有虚函数，否则编译不通过
当子类向父类进行转换时，一般不会出现问题；反之需要判断指针指向的对象的实际类型与转换后有的对象类型是否相同，不相同则会失败
转换成功时返回值形类的指针或引用，失败返回NULL

#### enum
```C++
enum 枚举名{ 
     标识符[=整型常数], 
     标识符[=整型常数], 
... 
    标识符[=整型常数]
} 枚举变量;
//example
enum color { red, green, blue } c;
c = blue;
```
#### explicit
用于修饰构造函数，禁止单参数的构造函数被用于隐式类型转换

#### export
作用与extern类似，区别是其声明的是外部模板类对象和模板函数

#### extern
声明变量或函数为外部链接，表明被声明的变量在同一文件的不同位置或者是不同文件中被定义的
C++中，extern还可以用来指定使用C语言进行链接，如：
extern "C" **
extern "C" {**}

#### inline
在函数定义时使用inline对其进行修饰，可将其数据放在栈中，对于一些简单的、开销小的、但需要频繁调用的函数，使用inline可提高程序执行效率，但相对的内存开销变大，需要慎用
inline函数不能包含switch、while等复杂的控制语句，并且其本身不能是递归的
inline函数的定义被建议放在头文件中
类中的成员函数定义时默认时内联的，但若定义在外部则默认不是内联的，需要加上inline修饰才能变为内联

#### mutable
只能作用于类的非静态和非常量数据成员，表示无论如何其是可变的。被const修饰函数体的函数可以修改被mutable修饰的数据变量

#### register
对于被register修饰的变量，编译器会尽量把它存储在CPU内部的寄存器中，而不是内存，提高访问效率。因此，被register修饰的变量必须是寄存器可接受的类型，能否使用有时候取决于机器

#### reinpreter_cast
形式：reinpreter_cast <_type> (expression)
并不会修改运算对象本身，而是对该对象从位模式上进行重新解释
作用是更改对指针指向的内容的解释，很容易带来风险
reinterpret_cast的一个实际用途是在哈希函数中，即通过让两个不同的值几乎不以相同的索引结尾的方式将值映射到索引

#### static_cast
在编译时进行，不能更改const、violate、__unaligned
与dynamic_cast类似，可用于父类与子类之间指针或引用的上下行转换，其中上行转换是安全的，下行转换由于不进行动态类型检查，是不安全的
可用于基本数据类型之间的转换
可转换空指针的类型

#### template
模板，主要用于实现泛型机制

#### typeof
定义一种数据类型的新名字，也就是别名

#### typeid
获取表达式或数据类型的类型信息，与dynamic_cast一样需要编译器开启RTTI
返回类型为type_info，对于type_info，若表达式的类型是类且包含虚函数，那么typeid返回表达式的动态类型
iso C++标准规定typeid必须实现四种操作：==、!=、name()、before()

#### typename
在模板中，用于表示一个参数类型，用于实现泛型机制，与class作用一样，但是使用class有时会造成混淆，所以使用typename专门表示修饰的参数为类型名称而不是别的成员函数或成员变量

#### violate
用于限定一个类型变量可被外部进程改变，可在多线程并发编程中使用

#### virtual
用于保证C++的多态性，父类中被virtual修饰的函数可被子类覆盖
父类中被virtual修饰的函数，且不对其进行定义，直接令其=0，这种函数称为纯虚函数，这样的父类为抽象类，抽象类不能被实例化，继承抽象类的子类必须重新重写抽象类的纯虚函数，否则可将继承到的纯虚函数也定义为纯虚函数，这样子类也变成了抽象类
子类继承抽象父类时使用virtual修饰对应的父类时，可解决多继承是的命名冲突和冗余数据的问题

#### wchar_t
表示宽字符类型，每个wchar_t类型占2个char，可用于表示汉字

### C++基本数据类型
micarosoft C++:
![image](https://learn.microsoft.com/zh-cn/cpp/cpp/media/built-intypesizes.png?view=msvc-170)
不同平台比较：
![image](https://www.runoob.com/wp-content/uploads/2014/09/32-64.jpg)
#### float
![image](https://www.runoob.com/wp-content/uploads/2014/09/v2-749cc641eb4d5dafd085e8c23f8826aa_hd.png)
#### double
![imag](https://www.runoob.com/wp-content/uploads/2014/09/v2-48240f0e1e0dd33ec89100cbe2d30707_hd.png)

### C++常量
#### 整数常量
前缀：0x、0X表示十六进制；0表示八进制；无前缀表示十进制
后缀：u、U表示无符号整数；l、L表示长整数

#### 浮点常量
使不使用小数点表示时，可使用[int][e|E][int]表示

#### 字符常量
括在单引号中，如果以L开头，则表示一个宽字符常量，例如：L'x'，此时必须存储在wchar_t类型的变量中。否则，是一个窄字符常量，例如：'x'，此时存储在char类型的简单变量中
字符常量可以是一个普通的字符（例如 'x'）、一个转义序列（例如 '\t'），或一个通用的字符（例如 '\u02C0'）

#### 字符串常量
string的常量为"[string]"表示，可使用\来换行
```C++
string hw = "hello, \
                   word";
```
#### 定义常量
 #define和const
 #define不能指定类型，const可以

### C++面向对象
#### C++类
##### 访问修饰符和继承
public：类的外部可访问
private：类的外部不可见和不可访问，类的友元函数可访问
protected：类的子类可访问
public继承：父类public，protected，private成员的访问属性在子类中为：public, protected, private
protected继承：父类 public，protected，private成员的访问属性在子类中为：protected, protected, private
private继承：父类public，protected，private成员的访问属性子类中为：private, private, private
继承形式：class [derived_class_name]: [access_specifier] [base_class_name]
derived_class_name为子类名，access_specifier即访问修饰符，有public、protected、private三种，base_class_name为父类名

##### 重载
函数：指参数的个数、类型或者顺序至少有有一个不同，不能仅通过返回类型的不同来重载函数
运算符：形式为[return] operator[symbol] (parameter0,...,parameterN)，其中symbol不可以是.、.*、->*、::、sizeof、?:、#

##### 多态
```C++
Father a ;          // 父类对象  
Son b ;             // 子类对象  

a = b ;             // 可以  
b = a ;             // 不可以  

Father *pa = &b ;   // 可以  
Son *pb = &a ;      // 不可以  

Father &f = b ;     // 可以  
Son &s = a ;        // 不可以   
```
若父类与子类有相同的方法，那么使用子类赋值的父类调用对应的方法还是其本身的方法，除非父类对应方法使用virtual修饰

### C++标准库
#### 文件和流
读取：ifstream
写入：oftream
读/写：fstream

#### 异常处理
![image](https://www.runoob.com/wp-content/uploads/2015/05/exceptions_in_cpp.png)
```C++
#include <iostream>
#include <exception>
using namespace std;
 
struct MyException : public exception
{
  const char * what () const throw ()
  {
    return "C++ Exception";
  }
};
 
int main()
{
  try
  {
    throw MyException();
  }
  catch(MyException& e)
  {
    std::cout << "MyException caught" << std::endl;
    std::cout << e.what() << std::endl;
  }
  catch(std::exception& e)
  {
    //其他的错误
  }
}
```

#### 动态内存
![image](https://img-blog.csdnimg.cn/20201001165330730.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1pKVV9maXNoMTk5Ng==,size_16,color_FFFFFF,t_70)
其中，当函数被调用时，将对应的连续内存(堆栈帧)入栈，当函数返回时，将堆栈帧弹出
堆栈帧的结构：
![image](https://img-blog.csdnimg.cn/20201004125637216.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1pKVV9maXNoMTk5Ng==,size_16,color_FFFFFF,t_70)
C++中使用new来动态申请内存，一个对象若直接创建，再栈中为其分配内存空间，否则在堆中分配内存空间，且再分配的空间上调用其构造函数，这也是new与C中的malloc的主要区别；每次使用new创建新的对象时，最后需要调用delete来清除，delete操作首先调用对象的析构函数，在释放其对应的空间
实际上对于一些简单的、微小的、运行时间短C++程序，不使用delete释放new分配的空间，程序运行结束时，操作系统也会进行清理
new操作实际上使用了operator new和placement new两个方法，operator new 方法实际上是使用malloc来分配空间，placement new则是调用类的构造函数
对应的也有operator delete，其使用free来释放空间，placement delete则调用类的析构函数
此外，每个类可以重载operator new/delete，但需要慎用

#### 命名空间
当中大型程序有多个人开发时，可能会出现变量名重复的情况，可使用命名空间加以区分
```C++
namespace Li{  //小李的变量定义
    FILE* fp = NULL;
}
namespace Han{  //小韩的变量定义
    FILE* fp = NULL;
}

using Li::fp;
fp = fopen("one.txt", "r");  //使用小李定义的变量 fp
Han :: fp = fopen("two.txt", "rb+");  //使用小韩定义的变量 fp
```

#### 模板
类似于Java中的泛型
##### 函数模板
格式：
```C++
template<parameter-list> 
//function-declaration
```
举例：
```C++
//template1.cpp #include <iostream>
template<typename T> 
void swap(T &a, T &b) {
    T tmp{a}; a = b;
    b = tmp;
}
int main(int argc, char* argv[]){
    int a = 2; int b = 3;
    swap(a, b); // 使用函数模板
    std::cout << "a=" << a << ", b=" << b << std::endl;
    double c = 1.1; 
    double d = 2.2; 
    swap(c, d);
    std::cout<<"c="<<c<<", d="<<d<<std::endl;
    return 0;
}
```
函数模板不是函数，只有当使用具体的类型替换模板中的类型的时候，才会生成具体函数，也就是函数模板的实例化。
##### 类模板
格式：
```C++
template <class type>
class class_name{
    ...
}
```
举例：
```C++
#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>
#include <stdexcept>
 
using namespace std;
 
template <class T>
class Stack { 
  private: 
    vector<T> elems;     // 元素 
 
  public: 
    void push(T const&);  // 入栈
    void pop();               // 出栈
    T top() const;            // 返回栈顶元素
    bool empty() const{       // 如果为空则返回真。
        return elems.empty(); 
    } 
}; 
 
template <class T>
void Stack<T>::push (T const& elem) 
{ 
    // 追加传入元素的副本
    elems.push_back(elem);    
} 
 
template <class T>
void Stack<T>::pop () 
{ 
    if (elems.empty()) { 
        throw out_of_range("Stack<>::pop(): empty stack"); 
    }
    // 删除最后一个元素
    elems.pop_back();         
} 
 
template <class T>
T Stack<T>::top () const 
{ 
    if (elems.empty()) { 
        throw out_of_range("Stack<>::top(): empty stack"); 
    }
    // 返回最后一个元素的副本 
    return elems.back();      
} 
 
int main() 
{ 
    try { 
        Stack<int>         intStack;  // int 类型的栈 
        Stack<string> stringStack;    // string 类型的栈 
 
        // 操作 int 类型的栈 
        intStack.push(7); 
        cout << intStack.top() <<endl; 
 
        // 操作 string 类型的栈 
        stringStack.push("hello"); 
        cout << stringStack.top() << std::endl; 
        stringStack.pop(); 
        stringStack.pop(); 
    } 
    catch (exception const& ex) { 
        cerr << "Exception: " << ex.what() <<endl; 
        return -1;
    } 
}
```

#### 预处理
常用的预处理命令：
```C++
#define            //定义一个预处理宏
#undef             //取消宏的定义

#if                //编译预处理中的条件命令, 相当于C语法中的if语句
#ifdef             //判断某个宏是否被定义, 若已定义, 执行随后的语句
#ifndef            //与#ifdef相反, 判断某个宏是否未被定义
#elif              //若#if, #ifdef, #ifndef或前面的#elif条件不满足, 则执行#elif之后的语句, 相当于C语法中的else-if
#else              //与#if, #ifdef, #ifndef对应, 若这些条件不满足, 则执行#else之后的语句, 相当于C语法中的else
#endif             //#if, #ifdef, #ifndef这些条件命令的结束标志.
defined            //与#if, #elif配合使用, 判断某个宏是否被定义

#include           //包含文件命令
#include_next      //与#include相似, 但它有着特殊的用途

#line              //标志该语句所在的行号
#                  //将宏参数替代为以参数值为内容的字符窜常量
##                 //将两个相邻的标记(token)连接为一个单独的标记
#pragma            //说明编译器信息

#warning           //显示编译警告信息
#error             //显示编译错误信息
```
C++ 中预定义的宏：
```C++
__LINE__	//在程序编译时包含当前行号
__FILE__	//在程序编译时包含当前文件名
__DATE__	//包含一个形式为 month/day/year 的字符串，它表示把源文件转换为目标代码的日期。
__TIME__	//包含一个形式为 hour:minute:second 的字符串，它表示程序被编译的时间。
```

#### lambda表达式
![image](https://learn.microsoft.com/zh-cn/cpp/cpp/media/lambdaexpsyntax.png?view=msvc-170)
capture子句（在C++规范中也称为Lambda引导。）
参数列表（可选）。（也称为Lambda声明符）
mutable规范（可选）。
exception-specification（可选）。
trailing-return-type（可选）。
Lambda体
###### 示例
```C++
// higher_order_lambda_expression.cpp
// compile with: /EHsc /W4
#include <iostream>
#include <functional>

int main()
{
    using namespace std;

    // The following code declares a lambda expression that returns
    // another lambda expression that adds two numbers.
    // The returned lambda expression captures parameter x by value.
    auto addtwointegers = [](int x) -> function<int(int)> {
        return [=](int y) { return x + y; };
    };

    // The following code declares a lambda expression that takes another
    // lambda expression as its argument.
    // The lambda expression applies the argument z to the function f
    // and multiplies by 2.
    auto higherorder = [](const function<int(int)>& f, int z) {
        return f(z) * 2;
    };

    // Call the lambda expression that is bound to higherorder.
    auto answer = higherorder(addtwointegers(7), 8);

    // Print the result, which is (7+8)*2.
    cout << answer << endl;
}
```

#### 信号机制
signal&raise：
形式：
```C++
  //...
  typedef	void (*__p_sig_fn_t)(int);
  //...
  __p_sig_fn_t __cdecl signal(int _SigNum,__p_sig_fn_t _Func);
  int __cdecl raise(int _SigNum);
```
signal用于监听某一个信号，即[_SigNum]，并在接收到对应信号后执行对应的操作，即执行[_Func]指向的函数
[_Func]是一个函数指针，signal也是一个函数指针，其指向上一个signal的[_Func]
raise用于发出信号，signal能够监听raise发出的信息的值，并根据质的不同做出相应的操作
```C++
#include <iostream>
#include <signal.h>
#include <windows.h>
using namespace std;

void func1(int){
  cout<<"fun1"<<endl;
}

void func2(int){
  cout<<"fun2"<<endl;
}

int main()
{
  void (*func)(int);
  void (*func3)(int);
  signal(SIGINT, func1);
  func = signal(SIGINT,func2);
  func(SIGINT);
  raise(SIGINT);
  signal(SIGINT, func1);
  func = signal(SIGINT, func2);
  func3 = signal(SIGINT, signal(SIGINT, func1));
  func(SIGINT);
  func3(SIGINT);
  raise(SIGINT);
  return 0;

  /*输出
    fun1
    fun2
    fun1
    fun1
    fun2
  */
}
```
此外，signal的函数执行成功后，若前面还有signal未被激活，则返回上一个signal的[_Func]，反之返回SIG_DFL，SIG_DFL为默认信号处理函数，若接收信号时发生了错误，则返回SIG_ERR
###### signal.h中的相关源码：
```C++
#define NSIG 23

#define SIGINT 2
#define SIGILL 4
#define SIGABRT_COMPAT 6
#define SIGFPE 8
#define SIGSEGV 11
#define SIGTERM 15
#define SIGBREAK 21
#define SIGABRT 22       /* used by abort, replace SIGIOT in the future */
#define SIGABRT2 22

#ifdef _POSIX
#define	SIGHUP	1	/* hangup */
#define	SIGQUIT	3	/* quit */
#define	SIGTRAP	5	/* trace trap (not reset when caught) */
#define SIGIOT  6       /* IOT instruction */
#define	SIGEMT	7	/* EMT instruction */
#define	SIGKILL	9	/* kill (cannot be caught or ignored) */
#define	SIGBUS	10	/* bus error */
#define	SIGSYS	12	/* bad argument to system call */
#define	SIGPIPE	13	/* write on a pipe with no one to read it */
#ifdef __USE_MINGW_ALARM
#define	SIGALRM	14	/* alarm clock */
#endif
#endif

  typedef	void (*__p_sig_fn_t)(int);

#define SIG_DFL (__p_sig_fn_t)0
#define SIG_IGN (__p_sig_fn_t)1
#define SIG_GET (__p_sig_fn_t)2
#define SIG_SGE (__p_sig_fn_t)3
#define SIG_ACK (__p_sig_fn_t)4
#define SIG_ERR (__p_sig_fn_t)-1
```

### C与C++
#### 函数指针与function
函数指针是一种指向函数的指针，与指针函数是两个概念
函数指针的声明写法是```[rerurn_type] (*func_name)(arg_type...)```，其中```[rerurn_type] (* )(arg_type...)```可看函数指针类型，```func_name```可看作函数指针名
```function```是一种模板类，其的声明方法是```function<_Res(_ArgTypes...)> func```，其中```_Res```为函数返回类型，```_ArgTypes```为入参类型，```function```可实现函数指针的功能
由于```function```是一个类，所以其可以重载运算符，如()、=等，相较于函数指针功能更为强大

new和malloc、delete和free
