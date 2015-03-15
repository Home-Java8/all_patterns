package com.patterns.system.workerthread.ex05;



/**
* (Параллельное выполнение в java. Шаблон Worker Thread) http://life-prog.ru/view_programs.php?id=49&language=java&page=1
* (Шаблоны Java) http://life-prog.ru/view_zam.php?id=74&cat=4&page=2
*/
//Шаблон Command
//        Поведенческий шаблон Command (Action, Transaction) обеспечивает обработку команды в виде объекта. Применяется, когда необходимо отделить источник запроса от объекта, отвечающего на запрос; позволяет выполнить поддержку таких операций, как отмена, ведение журнала, операций с транзакциями.
//
//Шаблон Singleton
//        Производящий шаблон Singleton обеспечивает наличие в системе только одного экземпляра заданного класса, позволяя другим классам получать к нему доступ. Применяется, если нужен объект, доступ к которому можно осуществить из любой точки приложения, но чтобы он создавался только один раз. То есть к этому объекту должны иметь доступ все элементы приложения, но работать они должны с одним и тем же экземпляром.
//
//Шаблон Worker Thread
//        Обрабатывающий  шаблон Worker Thread (Background Thread, Thread Pool) предназначен для улучшение пропускной способности и минимизация средней задержки при реализации параллельного выполнения.
//        Для того, чтобы реализовать средства поддержки многопоточности корректно, необходимо обладать определенными навыками. Один из способов максимизации эффективности многопоточного приложения состоит в использовании того факта, что не все выполненные в виде потоков задачи приложения имеют одинаковый приоритет. Для некоторых задач на первом месте стоит время выполнения. Другие же просто должны быть выполнены, а когда именно произойдет это выполнение – не столь важно.
//        Разработчик может отделить подобные задачи от приложения и воспользоваться шаблоном Worker Thread. Созданный в соответствии с этим шаблоном обработчик потока будет выбирать из очереди задачи и выполнять их в отдельном потоке. По окончании очередной задачи обработчик выбирает из очереди следующую задачу и все повторяется сначала.
//        Создание многопоточного приложения при использовании шаблона Worker Thread значительно упрощается, поскольку в тех случаях, когда не важно, как скоро будет выполнена задача, разработчику достаточно просто поместить ее в очередь, а все остальное сделает обработчик потока. Программный код такого приложения также упрощается, так как все объекты, работающие с потоками, скрыты внутри обработчика потока и очереди.
//        Шаблон Worker Thread рекомендуется использовать когда:
//        нужно повысить пропускную способность приложения;
//        необходимо обеспечить одновременное выполнение разных фрагментов кода.
//
//Шаблон Observer
//        Поведенческий шаблон Observer предоставляет компоненту возможность гибкой рассылки сообщений зарегистрированным получателям.
//        На некотором объекте сконцентрировано внимание наблюдателей, заинтересованных в получении от него какой-то информации. Потребовав от наблюдающих объектов, чтобы они устанавливали сеансы связи с центральным объектом, можно значительно снизить накладные расходы на коммуникацию, т.к. устанавливать связь будут только объекты, заинтересованные в получении обновленной информации.
//        Гибкость шаблона позволяет применять его для рассылки информации как отдельным, так и всем компонентам системы.
//        В соответствии с этим шаблоном, генераторы сообщений (наблюдаемые компоненты) рассылают сообщения, которые представляют события в системе. Эти события обрабатываются одним или несколькими получателями сообщений (компоненты-наблюдатели). Наблюдаемые компоненты отвечают за доставку событий всем заинтересованным наблюдателям (т.е. тем, которые установили сеансы связи). Интерфейс передачи сообщений позволяет наблюдаемым компонентам детализировать события для наблюдателей.
//        Если наблюдаемый объект многопоточный, он может поддерживать очередь сообщений, приоритеты сообщений, перекрытие сообщений и т.д.
//        Шаблон можно изменить, чтобы наблюдатели самостоятельно получали сообщения: наблюдаемый объект извещает о том, что событие произошло, а заинтересованные наблюдатели вызывают метод наблюдаемого для получения дополнительной информации о событии.



import com.patterns.system.workerthread.ex02.View;
import com.patterns.system.workerthread.ex02.ViewableResult;
//import com.patterns.system.workerthread.ex04.ChangeConsoleCommand;
//import com.patterns.system.workerthread.ex04.GenerateConsoleCommand;
import com.patterns.system.workerthread.ex04.Menu;
import com.patterns.system.workerthread.ex04.ViewConsoleCommand;

/** Вычисление и отображение
* результатов; содержит реализацию
* статического метода main()
* @author xone
* @version 5.0
* @see Main#main
*/
public class Main {

    /** Объект, реализующий интерфейс {@linkplain View};
     * обслуживает коллекцию объектов {@linkplain ex01.Item2d};
     * инициализируется с помощью Factory Method
     */
    private View view = new ViewableResult().getView();

    /** Объект класса {@linkplain Menu};
     * макрокоманда (шаблон Command)
     */
    private Menu menu = new Menu();

    /** Обработка команд пользователя */
    public void run() {
        menu.add(new ViewConsoleCommand(view));
//        menu.add(new GenerateConsoleCommand(view));
//        menu.add(new ChangeConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));
        menu.execute();
    }

    /** Выполняется при запуске программы
     * @param args параметры запуска программы
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}
