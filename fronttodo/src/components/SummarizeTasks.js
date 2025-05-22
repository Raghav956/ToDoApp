import React, { useState, useEffect } from 'react';

const SummarizeTasks = () => {
  const [summary, setSummary] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSummary = async () => {
      try {
        const res = await fetch('http://localhost:8080/todos/summary');
        const text = await res.text();
        setSummary(text);
      } catch (err) {
        setSummary('Failed to fetch summary');
      } finally {
        setLoading(false);
      }
    };
    fetchSummary();
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      <h2>Task Summary</h2>
      {loading ? <p>Loading...</p> : <p>{summary}</p>}
    </div>
  );
};

export default SummarizeTasks;