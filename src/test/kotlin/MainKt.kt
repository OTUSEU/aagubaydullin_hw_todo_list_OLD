import data.Priority
import data.Task
import data.TasksRepositoryMemory
import io.qameta.allure.Owner
import jdk.jfr.Description
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Description ("Test class")
class MainTest {
    lateinit var tasks: TasksRepositoryMemory

    private val testTask = Task(id = 0, name = "Test Task", Priority.HIGH, completed = false)

    private val listOfTestTasks = mutableListOf(
        Task(id = 1, name = "LOW TASK UNCOMPLITE", Priority.LOW,completed = false),
        Task(id = 2, name = "MEDIUM TASK UNCOMPLITE", Priority.MEDIUM,completed = false),
        Task(id = 3, name = "HIGH TASK UNCOMPLITE", Priority.HIGH,completed = false),
        Task(id = 4, name = "LOW TASK COMPLITE", Priority.LOW,completed = true ),
        Task(id = 5, name = "MEDIUM TASK COMPLITE", Priority.MEDIUM,completed = true),
        Task(id = 6, name = "HIGH TASK COMPLITE", Priority.HIGH,completed = true)
    )

    @BeforeTest
    fun beforeALLTest() {
        tasks = TasksRepositoryMemory()
    }
    @AfterTest
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