import React, { useState, useEffect } from 'react';

function App() {
  const [intervalMinutes, setIntervalMinutes] = useState('');
  const [remainingMinutes, setRemainingMinutes] = useState(null);
  const [currentTime, setCurrentTime] = useState('');
  const [timerRunning, setTimerRunning] = useState(false);

  // Update the current time every second
  useEffect(() => {
    const timeInterval = setInterval(() => {
      const now = new Date();
      setCurrentTime(
        `${String(now.getHours()).padStart(2, '0') % 12}:${String(
          now.getMinutes()
        ).padStart(2, '0')}:${String(now.getSeconds()).padStart(2, '0')}`
      );
    }, 1000);

    return () => clearInterval(timeInterval);
  }, []);

  // Decrease the remaining minutes every minute
  useEffect(() => {
    if (timerRunning && remainingMinutes > 0) {
      const minuteInterval = setInterval(() => {
        setRemainingMinutes((prev) => prev - 1);
      }, 60000);

      return () => clearInterval(minuteInterval);
    }
  }, [timerRunning, remainingMinutes]);

  const handleStart = () => {
    if (remainingMinutes === null && intervalMinutes > 0) {
      setRemainingMinutes(parseInt(intervalMinutes, 10));
    }
    setTimerRunning(true);
  };

  const handleStop = () => {
    setTimerRunning(false);
  };

  return (
    <div
      className="App"
      style={{
        padding: '20px',
        backgroundColor: '#121212',
        color: '#ffffff',
        minHeight: '100vh',
      }}
    >
      <h2>⏰ Time Interval Tracker</h2>
      <div style={{ marginBottom: '20px' }}>
        <label>
          Enter Interval (in minutes):{' '}
          <input
            type="number"
            value={intervalMinutes}
            onChange={(e) => setIntervalMinutes(e.target.value)}
            style={{
              padding: '5px',
              borderRadius: '5px',
              border: '1px solid #ffffff',
              backgroundColor: '#1e1e1e',
              color: '#ffffff',
            }}
          />
        </label>
        <button
          onClick={handleStart}
          style={{
            marginLeft: '10px',
            padding: '10px 20px',
            backgroundColor: '#4CAF50',
            color: '#ffffff',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
          }}
        >
          Start
        </button>
        <button
          onClick={handleStop}
          style={{
            marginLeft: '10px',
            padding: '10px 20px',
            backgroundColor: '#f44336',
            color: '#ffffff',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
          }}
        >
          Stop
        </button>
      </div>
      <div
        style={{
          marginTop: '20px',
          padding: '20px',
          border: '2px solid #ffffff',
          borderRadius: '5px',
          textAlign: 'center',
          fontSize: '24px',
          backgroundColor: '#1e1e1e',
        }}
      >
        {currentTime || 'Current Time will appear here'}
      </div>
      {remainingMinutes !== null && (
        <div
          style={{
            marginTop: '20px',
            padding: '10px',
            border: '2px solid #ffffff',
            borderRadius: '5px',
            textAlign: 'center',
            fontSize: '18px',
            backgroundColor: '#1e1e1e',
          }}
        >
          Remaining Interval: {remainingMinutes} minute(s)
        </div>
      )}
    </div>
  );
}

export default App;