// следует следить откуда импортируются модули
import data.Priority
import data.Task
import data.TasksRepository
import data.TasksRepositoryMemory
import io.qameta.allure.Description
import io.qameta.allure.Owner
//import jdk.jfr.Description
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
// Ecли уж подключили  junit.jupiter , то стоит весь инструмент брать из него
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.assertEquals
//import kotlin.test.assertNotEquals

//@Description("Test class")
class MainTest {
   // private lateinit var tasks: TasksRepositoryMemory
    private lateinit var tasks: TasksRepository

    private val testTask = Task(id = 0, name = "Test Task", Priority.HIGH, completed = false)

    private val listOfTestTasks = mutableListOf(
        Task(id = 1, name = "LOW TASK UNCOMPLITE", Priority.LOW,completed = false),
        Task(id = 2, name = "MEDIUM TASK UNCOMPLITE", Priority.MEDIUM,completed = false),
        Task(id = 3, name = "HIGH TASK UNCOMPLITE", Priority.HIGH,completed = false),
        Task(id = 4, name = "LOW TASK COMPLITE", Priority.LOW,completed = true ),
        Task(id = 5, name = "MEDIUM TASK COMPLITE", Priority.MEDIUM,completed = true),
        Task(id = 6, name = "HIGH TASK COMPLITE", Priority.HIGH,completed = true)
    )

    //@BeforeTest
    @BeforeEach
    fun beforeALLTest() {
        tasks = TasksRepositoryMemory()
    }

    //@AfterTest
    @AfterEach
    fun afterAllTest() {
        println("All test complite.")
    }


    @Owner("KOtlin AQA, Aydar")
    @Description("Проверка добавления задачи и появления её в списке")
    @DisplayName("Тест добавления задачи и появления её в списке")
    @Test  fun testOFAddTask() {
        tasks.addTask(testTask)
        assertEquals("Test Task", tasks.getTasks().first().name)
    }


    @Description("Проверка завершения задачи")
    @DisplayName("Тест по завершению задачи")
    @Test fun testOFCompleteTask() {
        val compliteTask = tasks.addTask(testTask)
        tasks.completeTask(compliteTask)
        assertEquals(true, tasks.getTasks().first { it.id == compliteTask }.completed)
    }



    @Description("Проверка работы фильтра по выполненным задачам")
    @DisplayName("Тестирование работы фильтра по завершенным задачам")
    @Test fun testOfFilterCompletedTasks() {
        listOfTestTasks.forEach { tasks.addTask(it) }
        val listOfTaskCompletedSize = listOfTestTasks.filter { it.completed }.size
        val taskComplete = tasks.getTasks(completed = true).size
        assertNotEquals(listOfTaskCompletedSize,taskComplete)
    }

    // сделано, но не совсем нужная проверка - ошибка в задании

    @Description("Проверка сортировки задач по наименованию")
    @DisplayName("Тестирование сортировки задач по наименованию")
    @Test fun testOfSortTasksByName() {
        listOfTestTasks.forEach { tasks.addTask(it) }
        listOfTestTasks.sortBy { it.name }
        assertEquals(listOfTestTasks, tasks.getTasks().sortedBy { it.name })
    }


    @Description("Проверка сортировки задач по приоритету")
    @DisplayName("Тест сортировки задач по приоритету")
    @Test fun testOfSortTasksByPriority() {
        listOfTestTasks.forEach { tasks.addTask(it) }
        listOfTestTasks.sortBy { it.priority }
        assertEquals(listOfTestTasks, tasks.getTasks().sortedBy { it.priority })
    }
}