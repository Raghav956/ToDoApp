import React, { useEffect, useState } from 'react';

const ViewTasks = ({ onEditTask,onSummarize }) => {
  const [tasks, setTasks] = useState([]);

  const fetchTasks = async () => {
    try {
      const response = await fetch('http://localhost:8080/todos');
      const data = await response.json();
      setTasks(data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  const handleDelete = async (id) => {
    const confirm = window.confirm('Are you sure you want to delete this task?');
    if (!confirm) return;

    try {
      const response = await fetch(`http://localhost:8080/todos/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        alert('Task deleted');
        fetchTasks(); 
      } else {
        alert('Failed to delete task');
      }
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <div style={{ padding: '20px', maxWidth: '600px', margin: 'auto' }}>
    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
      <h2>All Tasks</h2>
      <button onClick={onSummarize}>Summarize</button>
      </div>

      {tasks.length === 0 ? (
        <p>No tasks available.</p>
      ) : (
        <ul>
          {tasks.map((task) => (
            <li key={task.id}>
              <strong>{task.title}</strong>: {task.description}
              <button onClick={() => onEditTask(task)} style={{ marginLeft: '10px' }}>Edit</button>
              <button onClick={() => handleDelete(task.id)} style={{ marginLeft: '10px', color: 'red' }}>Delete</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ViewTasks;