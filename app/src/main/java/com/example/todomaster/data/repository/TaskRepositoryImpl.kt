package com.example.todomaster.data.repository

import com.example.todomaster.data.local.TaskDao
import com.example.todomaster.data.mapper.toTask
import com.example.todomaster.data.mapper.toTaskEntity
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks().map { entities ->
            entities.map { it.toTask() }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)?.toTask()
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task.toTaskEntity())
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task.toTaskEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toTaskEntity())
    }
}