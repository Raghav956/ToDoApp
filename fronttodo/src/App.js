import React, { useState } from 'react';
import AddTask from './components/AddTask';
import ViewTasks from './components/ViewTasks';
import './App.css';

function App() {
  const [view, setView] = useState('view');
  const [taskToEdit, setTaskToEdit] = useState(null); 
  const handleSummarize = async () => {
    try {
      const response = await fetch('http://localhost:8080/summarize', {
        method: 'POST',
      });

      if (!response.ok) {
        throw new Error('Failed to summarize tasks');
      }

      const summary = await response.text();
      alert("Summary:\n" + summary); // Show result in an alert
    } catch (error) {
      console.error('Error summarizing tasks:', error);
      alert('Error summarizing tasks');
    }
  };
  return (
    <div style={{ padding: '20px' }}>
      <h1 style={{ fontSize: '3rem', textAlign: 'center', marginBottom: '30px' }}>ToDoApp</h1>
      <button onClick={() => {
        setTaskToEdit(null); 
        setView('add');
      }}>Add Task</button>
      <button onClick={() => setView('view')}
       style={{ marginLeft: '10px' }} 
       >View Tasks</button>

      <div style={{ marginTop: '20px' }}>
        {view === 'add' && (
          <AddTask
            task={taskToEdit} 
            onDone={() => {
              setTaskToEdit(null);
              setView('view'); 
            }}
          />
        )}
        {view === 'view' && (
          <ViewTasks onEditTask={(task) => {
            setTaskToEdit(task);
            setView('add'); 
          }}
          onSummarize={handleSummarize}
          />
        )}
      </div>
    </div>
  );
}

export default App;