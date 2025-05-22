import React, { useState, useEffect } from 'react';

const AddTask = ({ task, onDone }) => {
  const [formTask, setFormTask] = useState({ title: '', description: '' });

  useEffect(() => {
    if (task) {
      setFormTask(task);
    } else {
      setFormTask({ title: '', description: '' });
    }
  }, [task]);

  const handleChange = (e) => {
    setFormTask({ ...formTask, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const url = task
      ? `http://localhost:8080/todos/${task.id}` 
      : `http://localhost:8080/todos`; 

    const method = task ? 'PUT' : 'POST';

    const response = await fetch(url, {
      method: method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formTask),
    });

    if (response.ok) {
      alert(task ? 'Task updated!' : 'Task added!');
      onDone(); 
    } else {
      alert('Failed to save task');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: 'auto' }}>
      <h2>{task ? 'Edit Task' : 'Add Task'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label><br />
          <input name="title" value={formTask.title} onChange={handleChange} required />
        </div>
        <div style={{ marginTop: '10px' }}>
          <label>Description:</label><br />
          <textarea name="description" value={formTask.description} onChange={handleChange} required />
        </div>
        <button style={{ marginTop: '15px' }} type="submit">
          {task ? 'Update Task' : 'Add Task'}
        </button>
      </form>
    </div>
  );
};

export default AddTask;