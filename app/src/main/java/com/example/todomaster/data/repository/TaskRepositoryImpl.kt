package com.example.todomaster.data.repository

import com.example.todomaster.data.local.TaskDao
import com.example.todomaster.data.model.TaskEntity
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toTaskEntity())
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toTaskEntity())
    }

    private fun TaskEntity.toTask(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }

    private fun Task.toTaskEntity(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }
}
